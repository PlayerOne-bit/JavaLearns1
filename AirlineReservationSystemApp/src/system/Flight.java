package system;

public class Flight {
    private int accountId;
    private String flightId;
    private int seatAvailable;
    private String seatId;
    private String departureDate;
    private String origin;
    private String destination;
    private String flightNumber;

    public Flight(int accountId, String flightId, int seatAvailable, String seatId,
                  String departureDate, String origin, String destination, String flightNumber) {
        this.accountId = accountId;
        this.flightId = flightId;
        this.seatAvailable = seatAvailable;
        this.seatId = seatId;
        this.departureDate = departureDate;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
    }

    public Flight(String flightNumber, String origin, String destination, 
                  String departureDate, int totalSeats) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.seatAvailable = totalSeats;
    }

    public int getAccountId() { return accountId; }
    public String getFlightId() { return flightId; }
    public int getSeatAvailable() { return seatAvailable; }
    public String getSeatId() { return seatId; }
    public String getDepartureDate() { return departureDate; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getFlightNumber() { return flightNumber; }

    public void setSeatAvailable(int seatAvailable) {
        if (seatAvailable >= 0) {
            this.seatAvailable = seatAvailable;
        }
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public boolean bookSeats(int numberOfSeats) {
        if (numberOfSeats > 0 && numberOfSeats <= seatAvailable) {
            seatAvailable -= numberOfSeats;
            return true;
        }
        return false;
    }

    public void releaseSeats(int numberOfSeats) {
        if (numberOfSeats > 0) {
            seatAvailable += numberOfSeats;
        }
    }

    @Override
    public String toString() {
        return String.format("Flight %s: %s -> %s | Date: %s | Available Seats: %d",
                flightNumber, origin, destination, departureDate, seatAvailable);
    }
}