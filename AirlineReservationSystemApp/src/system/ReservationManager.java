package system;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationManager {
    
    public boolean bookFlight(String flightNumber, String passengerName, String origin,
                             String destination, String departureDate, String arrivalDate,
                             String travelItinerary, String seatId) {
        List<String> passengers = new ArrayList<>();
        passengers.add(passengerName);
        return bookFlight(flightNumber, passengers, origin, destination, 
                         departureDate, arrivalDate, travelItinerary, seatId);
    }

    public boolean bookFlight(String flightNumber, List<String> passengerNames, String origin,
                             String destination, String departureDate, String arrivalDate,
                             String travelItinerary, String seatId) {
        try (Connection connect = Database.connectSQL()) {
            PreparedStatement checkSeats = connect.prepareStatement(
                "SELECT seat_available FROM flights WHERE flight_number = ? AND origin = ? AND destination = ?");
            checkSeats.setString(1, flightNumber);
            checkSeats.setString(2, origin);
            checkSeats.setString(3, destination);
            ResultSet rs = checkSeats.executeQuery();
            
            if (!rs.next() || rs.getInt("seat_available") < passengerNames.size()) {
                System.out.println("Not enough seats available!");
                return false;
            }

            String flightId = getOrCreateFlight(connect, flightNumber, origin, destination, 
                                               departureDate, rs.getInt("seat_available"));

            for (String passengerName : passengerNames) {
                String bookingNumber = generateBookingNumber();
                
                PreparedStatement ps = connect.prepareStatement(
                    "INSERT INTO passenger (account_id, flight_id, booking_number, full_name, " +
                    "contact_information, origin, destination, book_date, arrival_date, " +
                    "departure_date, travel_itinerary, current_status, seat_id, flight_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                
                ps.setString(1, User.getId());
                ps.setString(2, flightId);
                ps.setString(3, bookingNumber);
                ps.setString(4, passengerName);
                ps.setString(5, User.getEmailAddress());
                ps.setString(6, origin);
                ps.setString(7, destination);
                ps.setString(8, LocalDate.now().toString());
                ps.setString(9, arrivalDate);
                ps.setString(10, departureDate);
                ps.setString(11, travelItinerary);
                ps.setString(12, "CONFIRMED");
                ps.setString(13, seatId);
                ps.setString(14, flightNumber);
                
                ps.executeUpdate();
            }

            updateSeatAvailability(connect, flightNumber, origin, destination, 
                                  -passengerNames.size());

            System.out.println("Booking successful for " + passengerNames.size() + " passenger(s)!");
            return true;

        } catch (Exception e) {
            System.out.println("Booking error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Passenger> getUserBookings() {
        List<Passenger> bookings = new ArrayList<>();
        
        try (Connection connect = Database.connectSQL()) {
            PreparedStatement ps = connect.prepareStatement(
                "SELECT * FROM passenger WHERE account_id = ? ORDER BY book_date DESC");
            ps.setString(1, User.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Passenger passenger = new Passenger(
                    rs.getInt("account_id"),
                    rs.getString("flight_id"),
                    rs.getString("booking_number"),
                    rs.getString("full_name"),
                    rs.getString("contact_information"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("book_date"),
                    rs.getString("arrival_date"),
                    rs.getString("departure_date"),
                    rs.getString("travel_itinerary"),
                    rs.getString("current_status"),
                    rs.getString("seat_id"),
                    rs.getString("flight_number")
                );
                bookings.add(passenger);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving bookings: " + e.getMessage());
        }

        return bookings;
    }

    public Passenger getBookingByNumber(String bookingNumber) {
        try (Connection connect = Database.connectSQL()) {
            PreparedStatement ps = connect.prepareStatement(
                "SELECT * FROM passenger WHERE booking_number = ? AND account_id = ?");
            ps.setString(1, bookingNumber);
            ps.setString(2, User.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Passenger(
                    rs.getInt("account_id"),
                    rs.getString("flight_id"),
                    rs.getString("booking_number"),
                    rs.getString("full_name"),
                    rs.getString("contact_information"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("book_date"),
                    rs.getString("arrival_date"),
                    rs.getString("departure_date"),
                    rs.getString("travel_itinerary"),
                    rs.getString("current_status"),
                    rs.getString("seat_id"),
                    rs.getString("flight_number")
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<Flight> searchFlights(String origin, String destination, String departureDate) {
        List<Flight> flights = new ArrayList<>();
        
        try (Connection connect = Database.connectSQL()) {
            String query = "SELECT * FROM flights WHERE origin = ? AND destination = ?";
            if (departureDate != null && !departureDate.isEmpty()) {
                query += " AND departure_date = ?";
            }
            query += " AND seat_available > 0";
            
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, origin);
            ps.setString(2, destination);
            if (departureDate != null && !departureDate.isEmpty()) {
                ps.setString(3, departureDate);
            }
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight(
                    rs.getInt("account_id"),
                    rs.getString("flight_id"),
                    rs.getInt("seat_available"),
                    rs.getString("seat_id"),
                    rs.getString("departure_date"),
                    rs.getString("origin"),
                    rs.getString("destination"),
                    rs.getString("flight_number")
                );
                flights.add(flight);
            }
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }
        
        return flights;
    }

    public boolean updateBookingStatus(String bookingNumber, String newStatus) {
        try (Connection connect = Database.connectSQL()) {
            PreparedStatement ps = connect.prepareStatement(
                "UPDATE passenger SET current_status = ? WHERE booking_number = ? AND account_id = ?");
            ps.setString(1, newStatus);
            ps.setString(2, bookingNumber);
            ps.setString(3, User.getId());
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking status updated to: " + newStatus);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Update error: " + e.getMessage());
        }
        return false;
    }

    public boolean cancelBooking(String bookingNumber) {
        try (Connection connect = Database.connectSQL()) {
            Passenger booking = getBookingByNumber(bookingNumber);
            if (booking == null) {
                System.out.println("Booking not found!");
                return false;
            }

            PreparedStatement ps = connect.prepareStatement(
                "UPDATE passenger SET current_status = 'CANCELLED' WHERE booking_number = ? AND account_id = ?");
            ps.setString(1, bookingNumber);
            ps.setString(2, User.getId());
            
            int rowsAffected = ps.executeUpdate();
            
            if (rowsAffected > 0) {
                updateSeatAvailability(connect, booking.getFlightNumber(), 
                                     booking.getOrigin(), booking.getDestination(), 1);
                System.out.println("Booking cancelled successfully!");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Cancellation error: " + e.getMessage());
        }
        return false;
    }

    private String generateBookingNumber() {
        return "BK" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private String getOrCreateFlight(Connection connect, String flightNumber, String origin,
                                    String destination, String departureDate, int totalSeats) 
                                    throws SQLException {
        PreparedStatement check = connect.prepareStatement(
            "SELECT flight_id FROM flights WHERE flight_number = ? AND origin = ? AND destination = ?");
        check.setString(1, flightNumber);
        check.setString(2, origin);
        check.setString(3, destination);
        ResultSet rs = check.executeQuery();
        
        if (rs.next()) {
            return rs.getString("flight_id");
        }

        String flightId = "FL" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        PreparedStatement insert = connect.prepareStatement(
            "INSERT INTO flights (account_id, flight_id, seat_available, departure_date, " +
            "origin, destination, flight_number) VALUES (?, ?, ?, ?, ?, ?, ?)");
        insert.setString(1, User.getId());
        insert.setString(2, flightId);
        insert.setInt(3, totalSeats);
        insert.setString(4, departureDate);
        insert.setString(5, origin);
        insert.setString(6, destination);
        insert.setString(7, flightNumber);
        insert.executeUpdate();

        return flightId;
    }

    private void updateSeatAvailability(Connection connect, String flightNumber, String origin,
                                       String destination, int change) throws SQLException {
        PreparedStatement ps = connect.prepareStatement(
            "UPDATE flights SET seat_available = seat_available + ? " +
            "WHERE flight_number = ? AND origin = ? AND destination = ?");
        ps.setInt(1, change);
        ps.setString(2, flightNumber);
        ps.setString(3, origin);
        ps.setString(4, destination);
        ps.executeUpdate();
    }
}