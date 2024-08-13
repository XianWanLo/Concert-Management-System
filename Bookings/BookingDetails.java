package Bookings;
import java.util.*;
import Concerts.*;

/**
 * BookingDetails class implements booking related functions 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class BookingDetails{

    /*list of bookings to be managed*/
    private List<Booking> bookingList;

    /**
     * This is a parameterised constructor that creates a bookingDetails object
     * @param bookingList The list of bookings
     */
    public BookingDetails(List<Booking> bookingList){
        this.bookingList = bookingList;
    }

    /**
     * Display booking and ticket detail for users
     * @param concertDetails the details of all concerts
     */
    public void displayBooking(ConcertDetails concertDetails){
        
        System.out.println("Bookings");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-10s%-15s%-15s%-10s%n", "Id","Concert Date", "Artist Name", "Timing", "Venue Name", "Seats Booked", "Total Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        for(Booking booking: bookingList){
            int ID = booking.getBookingConcertID() - 1; 
            Concert concert = concertDetails.getConcertList().get(ID);
            System.out.printf("%-5d%-15s%-15s%-10s%-15s%-15d%-10.1f%n", booking.getBookingID(), concert.getDate(), concert.getArtistName(), concert.getTiming(), concert.getVenueName(), booking.getTotalTickets(), booking.calculateTotalPrice());  
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.println();

        System.out.println("Ticket Info");
        for(Booking booking: bookingList){
            System.out.printf("############### Booking Id: %d ####################%n", booking.getBookingID());
            System.out.printf("%-5s%-15s%-15s%-10s%-10s%n", "Id", "Aisle Number", "Seat Number", "Seat Type", "Price" );  
            System.out.println("##################################################");     
            for(int k=0; k < booking.getTicketList().size(); k++){  
                Ticket ticket = booking.getTicketList().get(k);
                System.out.printf("%-5s%-15s%-15s%-10s%-10.1f%n", ticket.getTicketID(), ticket.getRowNumber(), ticket.getSeatNumber(), ticket.getZoneType(), ticket.getPrice());
            }
            System.out.println("##################################################");
            System.out.println();
        }
    }

    /**
     * Book seats according to customer input 
     * @param concertDetails The details of all concerts
     * @param customerID Id of the customer who places the booking
     * @param customerName Name of the customer who places the booking
     * @param aisleNumber Selected aisle number
     * @param seatNumber Selected seat number
     * @param totalTickets The number of tickets booked
     */
    public void bookConcertSeats(ConcertDetails concertDetails, int customerID, String customerName, int concertID, String aisleNumber, int seatNumber, int totalTickets){
        
        char zoneInitial = aisleNumber.charAt(0);
        int rowNumber = aisleNumber.charAt(1) - '0';
        String zoneType = getZoneFromInitial(zoneInitial);

        List<Ticket> ticketList = new ArrayList<>();
        int ticketID = 1;
        for(int i=0; i<totalTickets; i++){
            concertDetails.getConcertList().get(concertID - 1).getVenue().updateBookedSeat(rowNumber, seatNumber, zoneType);      
            float price = concertDetails.getConcertList().get(concertID - 1).findPrice(zoneType, seatNumber);
            Ticket ticket = new Ticket(ticketID, rowNumber, seatNumber, zoneType, price);
            ticketList.add(ticket);
            ticketID += 1; 
            seatNumber += 1;
        }
        int bookingID = findMaxBookingID(concertID, customerID) + 1;
        Booking newBooking = new Booking(bookingID, customerID, customerName, concertID, totalTickets, ticketList);
        bookingList.add(newBooking);
    }

    /**
     * Find the concert zone type according to given zone initial
     * @param zoneInitial Zone type initial
     */
     private String getZoneFromInitial(char zoneInitial){
        
        String zoneType = null; 
        switch(zoneInitial){
            case 'V':
                zoneType = "VIP";
                break;
            case 'S':
                zoneType = "SEATING";
                break;
            case 'T':
                zoneType = "STANDING";
                break;
        }
        return zoneType;
    }

    /**
     * Find the maximum booking id number of selected customer for selected concert
     * @param concertID Id of the selected concert
     * @param customerID Id of the selected customer
     */
    private int findMaxBookingID(int concertID, int customerID){
        
        int maxBookingID = 0;
        for(Booking booking: bookingList){        
            if (customerID != booking.getBookingCustomerID() || concertID!= booking.getBookingConcertID()){
                continue;      
            }
            if (booking.getBookingID()>maxBookingID){
                maxBookingID = booking.getBookingID();
            }
        }
        return maxBookingID;
    }

    /**
     * Returns the list of bookings
     * @return list of bookings 
     */
    public List<Booking> getBookingList(){
        return bookingList;
    }

}
