package system;
public abstract class Ticket {
    protected Flight flight;
    protected int passengers;

    public Ticket(Flight flight, int passengers) {
        this.flight = flight;
        this.passengers = passengers;
    }

    public abstract double calculatePrice();
}
