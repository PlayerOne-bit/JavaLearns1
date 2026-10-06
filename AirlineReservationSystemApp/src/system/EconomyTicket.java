package system;

public class EconomyTicket extends Ticket {
    public EconomyTicket(Flight flight, int passengers) {
        super(flight, passengers);
    }

    @Override
    public double calculatePrice() {
        return 3000 * passengers; 
    }
}
