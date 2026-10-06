package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import system.Flight;
import system.ReservationManager;
import system.User;

public class BookController implements Initializable {
    @FXML private TextField name, email_address, passport_number, contact_number;
    @FXML private DatePicker date_of_birth;
    @FXML private Label error, passenger;
    @FXML private Button submitButton, nextButton;
    
    private ReservationManager reservationManager;
    private String flightNumber;
    private String origin;
    private String destination;
    private String departureDateTime;
    private int totalPassengers;
    private int currentPassengerIndex = 0;
    private List<PassengerInfo> passengerList;
    private String travelItinerary;
    private String returnDate;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        reservationManager = new ReservationManager();
        passengerList = new ArrayList<>();
        
        // Pre-fill email with user's email
        if (email_address != null && User.getEmailAddress() != null) {
            email_address.setText(User.getEmailAddress());
        }
        
        // Initially disable NEXT button
        if (nextButton != null) {
            nextButton.setDisable(true);
        }
    }

    public void setScheduledFlightDetails(String flightNumber, String origin, String destination,
                                         String departureDateTime, int passengers, 
                                         String itinerary, String returnDate) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        this.totalPassengers = passengers;
        this.travelItinerary = itinerary;
        this.returnDate = returnDate;
        this.currentPassengerIndex = 0;
        this.passengerList.clear();
        
        updatePassengerLabel();
        enableInputFields();
        
        // Disable NEXT button initially
        if (nextButton != null) {
            nextButton.setDisable(true);
        }
        
        if (error != null) {
            error.setText("Please enter details for passenger 1 of " + totalPassengers);
            error.setStyle("-fx-text-fill: #1E3C72;");
        }
    }

    public void setFlightDetails(Flight flight, int passengers, String itinerary, String returnDate) {
        this.flightNumber = flight.getFlightNumber();
        this.origin = flight.getOrigin();
        this.destination = flight.getDestination();
        this.departureDateTime = flight.getDepartureDate();
        this.totalPassengers = passengers;
        this.travelItinerary = itinerary;
        this.returnDate = returnDate;
        this.currentPassengerIndex = 0;
        this.passengerList.clear();
        
        updatePassengerLabel();
        enableInputFields();
        
        // Disable NEXT button initially
        if (nextButton != null) {
            nextButton.setDisable(true);
        }
        
        if (error != null) {
            error.setText("Please enter details for passenger 1 of " + totalPassengers);
            error.setStyle("-fx-text-fill: #1E3C72;");
        }
    }

    private void updatePassengerLabel() {
        if (passenger != null) {
            passenger.setText((currentPassengerIndex + 1) + " of " + totalPassengers + " Passenger");
        }
    }

    @FXML
    public void submit(ActionEvent e) {
        if (!validateFields()) {
            return;
        }

        String fullName = name.getText().trim();
        String emailAddr = email_address.getText().trim();
        String passportNum = passport_number.getText().trim();
        String contactNum = contact_number.getText().trim();
        String dob = date_of_birth.getValue() != null ? date_of_birth.getValue().toString() : "";

        PassengerInfo passengerInfo = new PassengerInfo(
            fullName, emailAddr, passportNum, contactNum, dob
        );
        passengerList.add(passengerInfo);

        currentPassengerIndex++;

        if (currentPassengerIndex < totalPassengers) {
            // More passengers to add
            disableInputFields();
            updatePassengerLabel();
            error.setText("Passenger " + currentPassengerIndex + " added! Click SUBMIT again for passenger " + (currentPassengerIndex + 1));
            error.setStyle("-fx-text-fill: green;");
            
            // Re-enable fields for next passenger after a short delay
            javafx.application.Platform.runLater(() -> {
                clearFields();
                enableInputFields();
                error.setText("Enter details for passenger " + (currentPassengerIndex + 1) + " of " + totalPassengers);
                error.setStyle("-fx-text-fill: #1E3C72;");
            });
            
        } else {
            // All passengers added
            disableInputFields();
            updatePassengerLabel();
            error.setText("All " + totalPassengers + " passenger(s) booked! Click NEXT to continue.");
            error.setStyle("-fx-text-fill: green;");
            
            // Enable NEXT button
            if (nextButton != null) {
                nextButton.setDisable(false);
            }
            
            // Disable SUBMIT button
            if (submitButton != null) {
                submitButton.setDisable(true);
            }
        }
    }


    @FXML
    public void next(ActionEvent e) {
        if (passengerList.size() < totalPassengers) {
            error.setText("Please add all " + totalPassengers + " passenger(s) before proceeding!");
            error.setStyle("-fx-text-fill: red;");
            return;
        }

        // Prepare passenger names
        List<String> passengerNames = new ArrayList<>();
        for (PassengerInfo info : passengerList) {
            passengerNames.add(info.fullName);
        }

        boolean success;

        try {
            // Check if flight exists in DB
            List<Flight> flights = reservationManager.searchFlights(origin, destination, departureDateTime);
            Flight flight = flights.isEmpty() ? null : flights.get(0);

            if (flight == null) {
                // No passengers yet, bypass seat availability check
                System.out.println("Flight not found in DB yet, skipping seat check.");
                success = true;
            } else {
                // Book via ReservationManager normally
                success = reservationManager.bookFlight(
                    flightNumber,
                    passengerNames,
                    origin,
                    destination,
                    departureDateTime,
                    returnDate != null ? returnDate : departureDateTime,
                    travelItinerary,
                    generateSeatId()
                );

                // Update schedule seats
                if (success) {
                    system.Schedule.updateAvailableSeats(flightNumber, -totalPassengers);
                }
            }

            if (success) {
                // Navigate to seat selection
                Pages.change(e,Pages.BOOK_FLIGHT ,Pages.SEAT);
            } else {
                error.setText("Booking failed! Please try again.");
                error.setStyle("-fx-text-fill: red;");
            }

        } catch (Exception ex) {
            error.setText("Navigation error: " + ex.getMessage());
            error.setStyle("-fx-text-fill: red;");
            ex.printStackTrace();
        }
    }

    
    public void nextButton(ActionEvent e) {
        try {
            Pages.change(e, Pages.SEAT);
        } catch (Exception ex) {
            error.setText("Navigation error: " + ex.getMessage());
            error.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void cancel(ActionEvent e) {
        try {
            Pages.change(e, Pages.FLIGHT);
        } catch (Exception ex) {
            error.setText("Navigation error: " + ex.getMessage());
            error.setStyle("-fx-text-fill: red;");
        }
    }

    private boolean validateFields() {
        if (name == null || name.getText().trim().isEmpty()) {
            error.setText("Please enter full name");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (email_address == null || email_address.getText().trim().isEmpty()) {
            error.setText("Please enter email address");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (!isValidEmail(email_address.getText().trim())) {
            error.setText("Please enter a valid email address");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (contact_number == null || contact_number.getText().trim().isEmpty()) {
            error.setText("Please enter contact number");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (passport_number == null || passport_number.getText().trim().isEmpty()) {
            error.setText("Please enter passport number");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        if (date_of_birth == null || date_of_birth.getValue() == null) {
            error.setText("Please select date of birth");
            error.setStyle("-fx-text-fill: red;");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private void clearFields() {
        if (name != null) name.clear();
        if (contact_number != null) contact_number.clear();
        if (passport_number != null) passport_number.clear();
        if (date_of_birth != null) date_of_birth.setValue(null);
        // Keep email address pre-filled
    }
    
    private void disableInputFields() {
        if (name != null) name.setDisable(true);
        if (email_address != null) email_address.setDisable(true);
        if (contact_number != null) contact_number.setDisable(true);
        if (passport_number != null) passport_number.setDisable(true);
        if (date_of_birth != null) date_of_birth.setDisable(true);
    }
    
    private void enableInputFields() {
        if (name != null) name.setDisable(false);
        if (email_address != null) email_address.setDisable(false);
        if (contact_number != null) contact_number.setDisable(false);
        if (passport_number != null) passport_number.setDisable(false);
        if (date_of_birth != null) date_of_birth.setDisable(false);
    }

    private String generateSeatId() {
        char row = (char) ('A' + (int) (Math.random() * 26));
        int number = (int) (Math.random() * 30) + 1;
        return row + String.valueOf(number);
    }

    // Inner class to store passenger information
    private static class PassengerInfo {
        String fullName;
        String emailAddress;
        String passportNumber;
        String contactNumber;
        String dateOfBirth;

        public PassengerInfo(String fullName, String emailAddress, String passportNumber,
                           String contactNumber, String dateOfBirth) {
            this.fullName = fullName;
            this.emailAddress = emailAddress;
            this.passportNumber = passportNumber;
            this.contactNumber = contactNumber;
            this.dateOfBirth = dateOfBirth;
        }
    }
}