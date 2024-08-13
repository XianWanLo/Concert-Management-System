package Users;
import java.util.*;
import Utils.*;
import Concerts.*;
import Bookings.*;
import Utils.*;

/**
 * Admin class handles all the admin responsibility for managing concerts.
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class Admin extends User{

    /**
     * This method implements the main admin operations. This method overrides the mainOperation method from the User class.
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user 
     */
    @Override
    public void mainOperation(ConcertDetails concertDetails, BookingDetails bookingDetails){

        boolean isExit = false;
        int option = 0;  
        while (!isExit){       
            printSelectionMenu();
            option = Integer.parseInt(Constants.KEYBOARD.nextLine());   
            // Process user input
            switch(option){
                // View all the concert details
                case Constants.ADMIN_CONCERTS_DETAIL:   
                    concertDetails.getConcertsInfo();           
                    break;
                // update the ticket costs
                case Constants.ADMIN_UPDATE_PRICE:
                    updateTicketCost(concertDetails);
                    break;
                // view booking details
                case Constants.ADMIN_BOOKING_DETAIL:
                    displayConcertBookings(concertDetails, bookingDetails);
                    break;
                // view total payment received for a concert
                case Constants.ADMIN_VIEW_PAYMENT:
                    viewPayment(concertDetails, bookingDetails);
                    break;
                // Exit
                case Constants.ADMIN_EXIT:
                    isExit = true;
                    System.out.println("Exiting admin mode");
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    /**
     * This method shows the promt selection menu for the admin. This method overrides the printSelectionMenu method from the User class.
     */
    @Override
    public void printSelectionMenu(){
        System.out.println("Select an option to get started!");
        System.out.println("Press 1 to view all the concert details");
        System.out.println("Press 2 to update the ticket costs");
        System.out.println("Press 3 to view booking details");
        System.out.println("Press 4 to view total payment received for a concert");
        System.out.println("Press 5 to exit");
        System.out.print("> ");
    }

    /**
     * Update ticket cost for the concert
     * @param concertDetails Concert Details to be shown to the user
     */
    public void updateTicketCost(ConcertDetails concertDetails){

        int concertID = promptConcertID(concertDetails);
        getTicketCost(concertDetails, concertID);

        System.out.println("Enter the zone : VIP, SEATING, STANDING: ");
        String zoneType = Constants.KEYBOARD.nextLine();

        float[] newZonePrice = new float [3];

        System.out.print("Left zone price: ");
        newZonePrice[0] = Float.parseFloat(Constants.KEYBOARD.nextLine());
        System.out.print("Centre zone price: ");
        newZonePrice[1] = Float.parseFloat(Constants.KEYBOARD.nextLine());
        System.out.print("Right zone price: ");
        newZonePrice[2] = Float.parseFloat(Constants.KEYBOARD.nextLine());

        int ID = concertID - 1;
        switch(zoneType.toUpperCase()){
            case "VIP":
                concertDetails.getConcertList().get(ID).setVipPrice(newZonePrice);
                break;
            case "SEATING":
                concertDetails.getConcertList().get(ID).setSeatingPrice(newZonePrice);
                break;
            case "STANDING":
                concertDetails.getConcertList().get(ID).setStandingPrice(newZonePrice);
                break;
        }
    }

    /**
     * Display bookings for selected concert 
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user
     */
    public void displayConcertBookings(ConcertDetails concertDetails, BookingDetails bookingDetails){

        int concertID = promptConcertID(concertDetails);

        List<Booking> concertBookingList = new ArrayList<>();

        for(Booking booking: bookingDetails.getBookingList()){ 
            if (concertID!= booking.getBookingConcertID()){
                continue;      
            }else{
                concertBookingList.add(booking);
            }
        }
        if(concertBookingList.isEmpty()){
            System.out.println("No Bookings found for this concert");
        }else{
            BookingDetails concertBookingDetails = new BookingDetails(concertBookingList);
            concertBookingDetails.displayBooking(concertDetails);
        }
       System.out.println();
    }

    /**
     * View the total payment for selected concert
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user
     */
    public void viewPayment(ConcertDetails concertDetails, BookingDetails bookingDetails){

        int concertID = promptConcertID(concertDetails);    
        float totalPayment = 0;

        for(Booking booking: bookingDetails.getBookingList()){   
            if (concertID!= booking.getBookingConcertID()){
                continue;      
            }else{
                totalPayment += booking.calculateTotalPrice();
            }
        }
        System.out.printf("Total Price for this concert is AUD %.1f", totalPayment);
    }

}
