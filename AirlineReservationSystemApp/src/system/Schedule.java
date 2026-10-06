package system;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    
    public static class ScheduledFlight {
        private String flightNumber;
        private String origin;
        private String destination;
        private String departureTime;
        private String arrivalTime;
        private int availableSeats;
        
        public ScheduledFlight(String flightNumber, String origin, String destination, 
                             String departureTime, String arrivalTime, int availableSeats) {
            this.flightNumber = flightNumber;
            this.origin = origin;
            this.destination = destination;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
            this.availableSeats = availableSeats;
        }
        
        public String getFlightNumber() { return flightNumber; }
        public String getOrigin() { return origin; }
        public String getDestination() { return destination; }
        public String getDepartureTime() { return departureTime; }
        public String getArrivalTime() { return arrivalTime; }
        public int getAvailableSeats() { return availableSeats; }
        
        public void setAvailableSeats(int seats) { this.availableSeats = seats; }
    }
    
    private static List<ScheduledFlight> flightSchedule = new ArrayList<>();
    
    static {
        // Initialize flight schedule - INTERNATIONAL FLIGHTS
        flightSchedule.add(new ScheduledFlight("IS 201", "Manila(MNL)", "Singapore(SIN)", "08:00 AM", "12:30 PM", 150));
        flightSchedule.add(new ScheduledFlight("IS 202", "Manila(MNL)", "Tokyo(NRT), Japan", "09:00 AM", "01:40 PM", 180));
        flightSchedule.add(new ScheduledFlight("IS 205", "Manila(MNL)", "Dubai(DXB), UAE", "09:30 AM", "05:00 PM", 200));
        flightSchedule.add(new ScheduledFlight("IS 206", "Manila(MNL)", "Sydney(SYD), Australia", "10:15 AM", "06:45 PM", 160));
        flightSchedule.add(new ScheduledFlight("IS 211", "Cebu(CEB)", "Seoul(ICN), South Korea", "11:00 AM", "03:30 PM", 140));
        flightSchedule.add(new ScheduledFlight("IS 212", "Singapore(SIN)", "Manila(MNL)", "12:45 PM", "05:00 PM", 150));
        flightSchedule.add(new ScheduledFlight("IS 215", "Tokyo(NRT), Japan", "Manila(MNL)", "04:15 PM", "07:45 PM", 180));
        flightSchedule.add(new ScheduledFlight("IS 216", "Dubai(DXB), UAE", "Manila(MNL)", "04:30 PM", "12:15 AM", 200));
        flightSchedule.add(new ScheduledFlight("IS 221", "Sydney(SYD), Australia", "Manila(MNL)", "10:00 PM", "06:00 AM", 160));
        flightSchedule.add(new ScheduledFlight("IS 222", "Seoul(ICN), South Korea", "Cebu(CEB)", "11:00 PM", "03:30 AM", 140));
    }
    
    public static List<ScheduledFlight> getAllFlights() {
        return new ArrayList<>(flightSchedule);
    }
    
    public static List<ScheduledFlight> searchFlights(String origin, String destination) {
        List<ScheduledFlight> results = new ArrayList<>();
        
        for (ScheduledFlight flight : flightSchedule) {
            if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)) {
                results.add(flight);
            }
        }
        
        return results;
    }
    
    public static ScheduledFlight getFlightByNumber(String flightNumber) {
        for (ScheduledFlight flight : flightSchedule) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
    
    public static boolean hasAvailableSeats(String flightNumber, int requiredSeats) {
        ScheduledFlight flight = getFlightByNumber(flightNumber);
        return flight != null && flight.getAvailableSeats() >= requiredSeats;
    }
    
    public static void updateAvailableSeats(String flightNumber, int change) {
        ScheduledFlight flight = getFlightByNumber(flightNumber);
        if (flight != null) {
            flight.setAvailableSeats(flight.getAvailableSeats() + change);
        }
    }
}