package Bookings;
import java.util.*;

/**
 * Booking class for managing individual booking
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class Booking{

    /* data fields */
    private int bookingID;
    private int bookingCustomerID;
    private String bookingCustomerName;
    private int bookingConcertID;
    private int totalTickets;
    private List<Ticket> ticketList;

    /**
     * This is a parameterised constructor that creates a booking object
     * @param bookingID Id of the booking 
     * @param bookingCustomerID Id of customer who placed the booking 
     * @param bookingCustomerName Name of customer who placed the booking 
     * @param bookingConcertID Id of the concert booked in the booking 
     * @param totalTickets Number of the tickets booked in the booking 
     * @param ticketList List of the tickets booked in the booking
     */
    public Booking(int bookingID, int bookingCustomerID, String bookingCustomerName, int bookingConcertID, int totalTickets, List<Ticket> ticketList){
        this.bookingID = bookingID;
        this.bookingCustomerID = bookingCustomerID;
        this.bookingCustomerName = bookingCustomerName;
        this.bookingConcertID = bookingConcertID;
        this.totalTickets = totalTickets;
        this.ticketList = ticketList;
    }

    /**
     * Returns the total price spent on the booking 
     * @return total price
     */
    public float calculateTotalPrice(){

        float totalPrice = 0;
        for(int i=0; i < totalTickets; i++){
            totalPrice += ticketList.get(i).getPrice();
        }

        return totalPrice;
    }

    /**
     * Returns the booking id
     * @return booking id 
     */
    public int getBookingID(){
        return bookingID;
    }

    /**
     * Returns the id of the customer who placed the booking 
     * @return customer id 
     */
    public int getBookingCustomerID(){
        return bookingCustomerID;
    }

    /**
     * Returns the name of the customer who placed the booking
     * @return customer name
     */
    public String getBookingCustomerName(){
        return bookingCustomerName;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public int getBookingConcertID(){
        return bookingConcertID;
    }

    /**
     * Returns the number of tickets booked in the booking 
     * @return number of ticket
     */
    public int getTotalTickets(){
        return totalTickets;
    }

    /**
     * Returns the list of tickets booked in the booking 
     * @return ticket list 
     */
    public List<Ticket> getTicketList(){
        return ticketList;
    }

}
