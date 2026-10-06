package system;

public class FirstClassTicket extends Ticket {
    public FirstClassTicket(Flight flight, int passengers) {
        super(flight, passengers);
    }

    @Override
    public double calculatePrice() {
        return 10000 * passengers; 
    }
}
