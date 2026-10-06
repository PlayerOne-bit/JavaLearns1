package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.Schedule;
import system.Schedule.ScheduledFlight;

public class FlightController implements Initializable {
    @FXML private Label error;
    @FXML private ChoiceBox<String> departureCity, arrivalCity, travelItinerary;
    @FXML private ChoiceBox<Integer> passengers;
    @FXML private TableView<ScheduledFlight> flightTable;
    @FXML private TableColumn<ScheduledFlight, String> flightNumberCol, originCol, destinationCol, departureTimeCol, arrivalTimeCol;
    @FXML private TableColumn<ScheduledFlight, Integer> seatsCol;
    @FXML private DatePicker departureDate, returnTime;

    private ScheduledFlight selectedFlight;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        String[] city = {
            "Manila(MNL)", 
            "Cebu(CEB)", 
            "Singapore(SIN)", 
            "Tokyo(NRT), Japan", 
            "Dubai(DXB), UAE", 
            "Sydney(SYD), Australia", 
            "Seoul(ICN), South Korea"
        };
        
        departureCity.getItems().addAll(city);
        arrivalCity.getItems().addAll(city);
        travelItinerary.getItems().addAll("One-way trip", "Round-trip");
        passengers.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9);
        
        passengers.setValue(1);
        travelItinerary.setValue("One-way trip");
        
        // Disable return date for one-way trips
        returnTime.setDisable(true);
        
        // Add listener to enable/disable return date based on itinerary selection
        travelItinerary.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) -> {
            if (newVal != null) {
                if (newVal.equals("One-way trip")) {
                    returnTime.setDisable(true);
                    returnTime.setValue(null);
                } else {
                    returnTime.setDisable(false);
                }
            }
        });
        
        if (flightNumberCol != null) {
            flightNumberCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleStringProperty(data.getValue().getFlightNumber()));
        }
        if (originCol != null) {
            originCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleStringProperty(data.getValue().getOrigin()));
        }
        if (destinationCol != null) {
            destinationCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleStringProperty(data.getValue().getDestination()));
        }
        if (departureTimeCol != null) {
            departureTimeCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleStringProperty(data.getValue().getDepartureTime()));
        }
        if (arrivalTimeCol != null) {
            arrivalTimeCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleStringProperty(data.getValue().getArrivalTime()));
        }
        if (seatsCol != null) {
            seatsCol.setCellValueFactory(data -> 
                new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAvailableSeats()).asObject());
        }

        // Add selection listener
        flightTable.getSelectionModel().selectedItemProperty().addListener((_, _, newSelection) -> {
            if (newSelection != null) {
                selectedFlight = newSelection;
            }
        });
    }

    @FXML
    public void searchFlight() {
        String departure_city = departureCity.getValue();
        String arrival_city = arrivalCity.getValue();

        if (departure_city == null || arrival_city == null) {
            error.setText("Please select both departure and arrival cities");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        if (departure_city.equals(arrival_city)) {
            error.setText("Departure and arrival cities cannot be the same");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        // Search using Schedule class
        List<ScheduledFlight> flights = Schedule.searchFlights(departure_city, arrival_city);
        
        if (flights.isEmpty()) {
            error.setText("No flights available for this route");
            error.setStyle("-fx-text-fill: orange;");
            flightTable.setItems(FXCollections.observableArrayList());
        } else {
            error.setText("Found " + flights.size() + " flight(s) - Select one and click 'BOOK SELECTED'");
            error.setStyle("-fx-text-fill: green;");
            ObservableList<ScheduledFlight> flightData = FXCollections.observableArrayList(flights);
            flightTable.setItems(flightData);
        }
    }

    @FXML
    public void bookSelectedFlight(ActionEvent e) {
        if (selectedFlight == null) {
            error.setText("Please select a flight from the table");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        Integer passengerCount = passengers.getValue();
        if (passengerCount == null || passengerCount < 1) {
            error.setText("Please select number of passengers");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        if (selectedFlight.getAvailableSeats() < passengerCount) {
            error.setText("Not enough seats available. Only " + selectedFlight.getAvailableSeats() + " seat(s) left");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Pages.BOOK_FLIGHT));
            Parent root = loader.load();
            
            BookController bookController = loader.getController();
            String departureDateTime = departureDate.getValue() != null ? 
                departureDate.getValue().toString() + " " + selectedFlight.getDepartureTime() : 
                selectedFlight.getDepartureTime();
            String returnDateStr = returnTime.getValue() != null ? returnTime.getValue().toString() : null;
            String itinerary = travelItinerary.getValue() != null ? travelItinerary.getValue() : "One-way trip";
            
            // Pass scheduled flight info to booking controller
            bookController.setScheduledFlightDetails(
                selectedFlight.getFlightNumber(),
                selectedFlight.getOrigin(),
                selectedFlight.getDestination(),
                departureDateTime,
                passengerCount,
                itinerary,
                returnDateStr
            );
            
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            
        } catch (Exception ex) {
            error.setText("Error loading booking page: " + ex.getMessage());
            error.setStyle("-fx-text-fill: red;");
            ex.printStackTrace();
        }
    }

    @FXML
    public void clearForm() {
        departureCity.setValue(null);
        arrivalCity.setValue(null);
        travelItinerary.setValue("One-way trip");
        passengers.setValue(1);
        departureDate.setValue(null);
        returnTime.setValue(null);
        flightTable.setItems(FXCollections.observableArrayList());
        selectedFlight = null;
        error.setText("");
    }

    @FXML
    public void mainpage(ActionEvent e) throws Exception {
        Pages.change(e, Pages.MAIN_PAGE);
    }

    @FXML
    public void schedule(ActionEvent e) throws Exception {
        Pages.change(e, Pages.SCHEDULE);
    }

    @FXML
    public void settings(ActionEvent e) throws Exception {
        Pages.change(e, Pages.SETTINGS);
    }
}