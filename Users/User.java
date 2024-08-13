package Users;
import Utils.*;
import Bookings.*;
import Concerts.*;

/**
 * User class manages all users accessing the ticket management system
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public abstract class User{

    /**
     * Abstract method for user main operations
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user
     */
    protected abstract void mainOperation(ConcertDetails concertDetails, BookingDetails bookingDetails);

    /**
     * Abstract method to show selection menu for user
     */
    protected abstract void printSelectionMenu();

    /**
     * Print the ticket cost of selected conert
     * @param concertDetails ConcertDetails object containing all the concerts
     * @param concertID Selected concert id
     */
    protected void getTicketCost(ConcertDetails concertDetails, int concertID){
        
        int ID = concertID -1;
        Concert concert = concertDetails.getConcertList().get(ID);
        
        System.out.printf("---------- %8s ----------%n", "STANDING");
        System.out.printf("%-14s%.1f\n", "Left Seats:", concert.getStandingPrice()[0]);
        System.out.printf("%-14s%.1f\n", "Center Seats:", concert.getStandingPrice()[1]);
        System.out.printf("%-14s%.1f\n", "Right Seats:", concert.getStandingPrice()[2]);
        System.out.println("------------------------------");
        
        System.out.printf("---------- %8s ----------%n", "SEATING");
        System.out.printf("%-14s%.1f\n", "Left Seats:", concert.getSeatingPrice()[0]);
        System.out.printf("%-14s%.1f\n", "Center Seats:", concert.getSeatingPrice()[1]);
        System.out.printf("%-14s%.1f\n", "Right Seats:", concert.getSeatingPrice()[2]);
        System.out.println("------------------------------");

        System.out.printf("---------- %8s ----------%n", "VIP");
        System.out.printf("%-14s%.1f\n", "Left Seats:", concert.getVipPrice()[0]);
        System.out.printf("%-14s%.1f\n", "Center Seats:", concert.getVipPrice()[1]);
        System.out.printf("%-14s%.1f\n", "Right Seats:", concert.getVipPrice()[2]);
        System.out.println("------------------------------");
    }
    
    /**
     * Gets the concert id that needs to be updated/viewed.
     * @param concertDetails ConcertDetails object containing all the concerts
     * @return concert id selected
     */
    protected int promptConcertID(ConcertDetails concertDetails) {
        
        System.out.println("Select a concert or 0 to exit");
        concertDetails.getConcertsInfo();
        System.out.print("> ");

        return Integer.parseInt(Constants.KEYBOARD.nextLine());   
    }

}
