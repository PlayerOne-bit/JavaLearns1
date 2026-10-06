package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import system.EconomyTicket;
import system.FirstClassTicket;
import system.Flight;
import system.Ticket;

public class SeatController implements Initializable {

    @FXML private GridPane seatGrid;
    @FXML private Label errorLabel;
    @FXML private Label statusLabel;

    private int totalPassengers = 1;
    private List<String> selectedSeats;
    private Map<String, Button> seatButtons;
    private List<String> bookedSeats;

    private static final int ROWS = 20;
    private static final String[] COLUMNS = {"A", "B", "C", "D", "E", "F"};

    private Flight flight; // Added flight reference

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedSeats = new ArrayList<>();
        seatButtons = new HashMap<>();
        bookedSeats = new ArrayList<>();

        if (seatGrid != null) {
            generateSeatLayout();
            simulateBookedSeats();
            updateSelectionStatus();
        }
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setPassengerCount(int count) {
        this.totalPassengers = count > 0 ? count : 1;
        updateSelectionStatus();
    }

    private void generateSeatLayout() {
        seatGrid.getChildren().clear();

        // Column headers
        seatGrid.add(new Label(""), 0, 0); // corner
        for (int i = 0; i < COLUMNS.length; i++) {
            if (i == 3) {
                Label aisle = new Label("");
                aisle.setMinWidth(15);
                seatGrid.add(aisle, i + 1, 0);
            }
            seatGrid.add(createColumnLabel(COLUMNS[i]), i < 3 ? i + 1 : i + 2, 0);
        }

        // Rows
        for (int row = 0; row < ROWS; row++) {
            boolean isFirstClass = row < 3; // first 3 rows = first class
            for (int col = 0; col < COLUMNS.length; col++) {
                if (col == 3) {
                    Label aisle = new Label("");
                    aisle.setMinWidth(15);
                    seatGrid.add(aisle, col + 1, row + 1);
                }
                String seatId = COLUMNS[col] + (row + 1);
                seatGrid.add(createSeatButton(seatId, isFirstClass), col < 3 ? col + 1 : col + 2, row + 1);
            }
        }
    }

    private Label createColumnLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: white;");
        label.setMinWidth(32);
        label.setAlignment(Pos.CENTER);
        return label;
    }

    private Button createSeatButton(String seatId, boolean isFirstClass) {
        Button button = new Button(seatId);
        button.setMinSize(32, 32);
        button.setMaxSize(32, 32);
        button.setStyle(getSeatStyle(isFirstClass, false, false));

        button.setOnAction(e -> handleSeatSelection(seatId, button, isFirstClass));

        seatButtons.put(seatId, button);
        return button;
    }

    private String getSeatStyle(boolean isFirstClass, boolean selected, boolean booked) {
        if (booked) {
            return "-fx-background-color: #FF6B6B; -fx-border-color: #555; -fx-border-width: 1px; -fx-opacity: 0.7; -fx-font-size: 9px; -fx-font-weight: bold;";
        }
        if (selected) {
            return "-fx-background-color: #4A90E2; -fx-border-color: #555; -fx-border-width: 2px; -fx-font-size: 9px; -fx-font-weight: bold; -fx-text-fill: white;";
        }
        if (isFirstClass) {
            return "-fx-background-color: #FFD700; -fx-border-color: #555; -fx-border-width: 1px; -fx-font-size: 9px; -fx-font-weight: bold;";
        } else {
            return "-fx-background-color: #90EE90; -fx-border-color: #555; -fx-border-width: 1px; -fx-font-size: 9px; -fx-font-weight: bold;";
        }
    }

    private void handleSeatSelection(String seatId, Button button, boolean isFirstClass) {
        if (bookedSeats.contains(seatId)) {
            showError("This seat is already booked!");
            return;
        }

        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId);
            button.setStyle(getSeatStyle(isFirstClass, false, false));
        } else {
            if (selectedSeats.size() >= totalPassengers) {
                showError("You can only select " + totalPassengers + " seat(s)!");
                return;
            }
            selectedSeats.add(seatId);
            button.setStyle(getSeatStyle(isFirstClass, true, false));
        }

        updateSelectionStatus();
        clearError();
    }

    private void simulateBookedSeats() {
        bookedSeats.clear();
        bookedSeats.add("A5"); bookedSeats.add("B5"); bookedSeats.add("C8");
        bookedSeats.add("D12"); bookedSeats.add("E15"); bookedSeats.add("F3");

        for (String seatId : bookedSeats) {
            Button btn = seatButtons.get(seatId);
            if (btn != null) {
                btn.setStyle("-fx-background-color: #FF6B6B; -fx-border-color: #555; -fx-border-width: 1px; -fx-font-size: 9px; -fx-font-weight: bold; -fx-opacity: 0.7;");
                btn.setDisable(true);
            }
        }
    }

    private void updateSelectionStatus() {
        if (statusLabel != null) {
            statusLabel.setText("Selected: " + selectedSeats.size() + " of " + totalPassengers);
            if (selectedSeats.size() == totalPassengers) {
                statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #00FF00;");
            } else {
                statusLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white;");
            }
        }
    }

    private void showError(String message) {
        if (errorLabel != null) {
            errorLabel.setText(message);
            errorLabel.setStyle("-fx-text-fill: #FF3333; -fx-font-size: 13px; -fx-font-weight: bold;");
        }
    }

    private void clearError() {
        if (errorLabel != null) {
            errorLabel.setText("");
        }
    }

    public void next(ActionEvent e) {
        if (selectedSeats.size() < totalPassengers) {
            showError("Please select " + totalPassengers + " seat(s) before proceeding!");
            return;
        }

        int firstClassCount = (int) selectedSeats.stream().filter(s -> s.endsWith("1") || s.endsWith("2") || s.endsWith("3")).count();
        int economyCount = totalPassengers - firstClassCount;

        Ticket firstClassTicket = null;
        Ticket economyTicket = null;

        if (firstClassCount > 0) {
            firstClassTicket = new FirstClassTicket(flight, firstClassCount);
        }
        if (economyCount > 0) {
            economyTicket = new EconomyTicket(flight, economyCount);
        }

        double totalPrice = 0;
        if (firstClassTicket != null) totalPrice += firstClassTicket.calculatePrice();
        if (economyTicket != null) totalPrice += economyTicket.calculatePrice();

        System.out.println("Total Price: " + totalPrice);

        try {
            Pages.change(e, Pages.SEAT, Pages.PRICE); // adjusted call
        } catch (Exception ex) {
            showError("Navigation error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void cancel(ActionEvent e) {
        Pages.back(e);
    }

    public List<String> getSelectedSeats() {
        return new ArrayList<>(selectedSeats);
    }
}
