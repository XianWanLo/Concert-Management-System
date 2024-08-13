package Bookings;

/**
 * Ticket class for managing individual ticket
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class Ticket {

    /* data fields */
    private int ticketID;
    private int rowNumber;
    private int seatNumber;
    private String zoneType;
    private float price;
    
    /**
     * This is a parameterised constructor that creates a ticket object
     * @param ticketID Id of the ticket 
     * @param rowNumber The selected row number 
     * @param seatNumber The selected seat number
     * @param zoneType Type of seating zone
     * @param price Price of the selected seat zone
     */
    public Ticket(int ticketID, int rowNumber, int seatNumber, String zoneType,float price){
        this.ticketID = ticketID;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.zoneType = zoneType;
        this.price = price;
    }

    /**
     * This is a copy constructor that creates a ticket object
     * @param ticket Ticket placed by customer 
     */
    public Ticket(Ticket ticket){
        this.ticketID = ticket.ticketID;
        this.rowNumber = ticket.rowNumber;
        this.seatNumber = ticket.seatNumber;
        this.zoneType = ticket.zoneType;
        this.price = ticket.price;
    }

    /**
     * Returns the id of the ticket
     * @return ticket id 
     */
    public int getTicketID(){
        return ticketID;
    }

    /**
     * Returns the row number 
     * @return row number
     */
    public int getRowNumber(){
        return rowNumber;
    }

    /**
     * Returns the seat number
     * @return seat number
     */
    public int getSeatNumber(){
        return seatNumber;
    }

    /**
     * Returns the type of seating zone
     * @return zone type
     */
    public String getZoneType(){
        return zoneType;
    }

    /**
     * Returns the price of the selected seat
     * @return price 
     */
    public float getPrice(){
        return price;
    }

}
