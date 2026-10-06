package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import system.Ticket;

import java.util.List;

public class PriceController {

    @FXML
    private VBox summaryContainer;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label errorLabel;

    /**
     * Call this method from SeatController after selecting seats and calculating price.
     */
    public void setBookingSummary(List<String> seats, List<Ticket> tickets) {
        summaryContainer.getChildren().clear();
        double totalPrice = 0;

        // Display each selected seat
        for (String seat : seats) {
            Label seatLabel = new Label("Seat: " + seat);
            seatLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
            summaryContainer.getChildren().add(seatLabel);
        }

        // Calculate total price
        for (Ticket ticket : tickets) {
            totalPrice += ticket.calculatePrice();
        }

        totalPriceLabel.setText(String.format("Total Price: ₱%.2f", totalPrice));
    }

    @FXML
    private void confirmBooking(ActionEvent e) {
        Pages.change(e,Pages.MAIN_PAGE);
    }

    @FXML
    private void cancel(ActionEvent e) {
        // Go back or close window
        Pages.back(e);
    }
}
