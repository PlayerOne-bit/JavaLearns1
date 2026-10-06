package system;

public class Passenger {
    private int accountId;
    private String flightId;
    private String bookingNumber;
    private String fullName;
    private String contactInformation;
    private String origin;
    private String destination;
    private String bookDate;
    private String arrivalDate;
    private String departureDate;
    private String travelItinerary;
    private String currentStatus;
    private String seatId;
    private String flightNumber;

    public Passenger(int accountId, String flightId, String bookingNumber, String fullName,
                    String contactInformation, String origin, String destination, String bookDate,
                    String arrivalDate, String departureDate, String travelItinerary,
                    String currentStatus, String seatId, String flightNumber) {
        this.accountId = accountId;
        this.flightId = flightId;
        this.bookingNumber = bookingNumber;
        this.fullName = fullName;
        this.contactInformation = contactInformation;
        this.origin = origin;
        this.destination = destination;
        this.bookDate = bookDate;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.travelItinerary = travelItinerary;
        this.currentStatus = currentStatus;
        this.seatId = seatId;
        this.flightNumber = flightNumber;
    }

    public int getAccountId() { return accountId; }
    public String getFlightId() { return flightId; }
    public String getBookingNumber() { return bookingNumber; }
    public String getFullName() { return fullName; }
    public String getContactInformation() { return contactInformation; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getBookDate() { return bookDate; }
    public String getArrivalDate() { return arrivalDate; }
    public String getDepartureDate() { return departureDate; }
    public String getTravelItinerary() { return travelItinerary; }
    public String getCurrentStatus() { return currentStatus; }
    public String getSeatId() { return seatId; }
    public String getFlightNumber() { return flightNumber; }

    public void setCurrentStatus(String status) { this.currentStatus = status; }
    public void setSeatId(String seatId) { this.seatId = seatId; }

    @Override
    public String toString() {
        return String.format("Booking: %s | Passenger: %s | Flight: %s | %s -> %s | Status: %s",
                bookingNumber, fullName, flightNumber, origin, destination, currentStatus);
    }
}