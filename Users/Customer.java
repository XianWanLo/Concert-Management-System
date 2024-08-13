package Users;
import java.util.*;
import Concerts.*;
import Utils.*;
import Bookings.*;

/**
 * Customer class implements functions for customers in the ticket management system 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class Customer extends User{

    /* data fields */
    private int customerID;
    private String customerName;
    private String customerPswd;

    /**
     * This is a parameterised constructor that creates a customer object
     * @param customerID Customer's id
     * @param customerName Customer's name
     * @param customerPswd Customer's password
     */
    public Customer(int customerID, String customerName, String customerPswd){  
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerPswd = customerPswd;
    }

    /**
     * This method implements the main customer operations. This method overrides the mainOperation method from the User class.
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user 
     */
    @Override
    public void mainOperation(ConcertDetails concertDetails,BookingDetails bookingDetails){

        boolean isExit = false;

        //System.out.println(concertDetails.getConcertList().get(1).getSeatingPrice()[0]);
        
        while (!isExit){

            int concertID = promptConcertID(concertDetails);

            if(concertID==0){
                isExit = true;
                System.out.println("Exiting customer mode");
            }else{
                customerMenuLoop(concertDetails, bookingDetails, concertID);
            }
        }
    }

    /**
     * Prompt the customer options for selected concert
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user 
     * @param concertID Selected concert Id
     */
    public void customerMenuLoop(ConcertDetails concertDetails, BookingDetails bookingDetails, int concertID){
        
        boolean isExit = false;
        int option = 0;      

        while (!isExit){

            // Take in user input
            printSelectionMenu();   
            option = Integer.parseInt(Constants.KEYBOARD.nextLine());   

            // Process user input
            switch(option){

                // Ticket cost
                case Constants.CUSTOMER_TICKET_COST:   
                    getTicketCost(concertDetails, concertID);              
                    break;

                // View seat layout
                case Constants.CUSTOMER_VIEW_LAYOUT:
                    concertDetails.getSeatLayout(concertID);
                    break;

                // Book seats
                case Constants.CUSTOMER_BOOK_SEATS:
                    bookSeats(concertDetails, bookingDetails, concertID);
                    break;

                // View booking details
                case Constants.CUSTOMER_BOOKING_DETAIL:
                    displayCustomerBooking(concertDetails, bookingDetails, concertID);
                    break;

                // Exit
                case Constants.CUSTOMER_EXIT:
                    isExit = true;
                    System.out.println("Exiting this concert");
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;
            }
        }
    }

    /**
     * This method shows the promt selection menu for the customer. This method overrides the printSelectionMenu method from the User class.
     */
    @Override
    public void printSelectionMenu(){

        System.out.println("Select an option to get started!");
        System.out.println("Press 1 to look at the ticket costs");
        System.out.println("Press 2 to view seats layout");
        System.out.println("Press 3 to book seats");
        System.out.println("Press 4 to view booking details");
        System.out.println("Press 5 to exit");
        System.out.print("> ");
    }

    /**
     * Book seats for selected concert
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user
     * @param concertID Id of the selected concert 
     */
    public void bookSeats(ConcertDetails concertDetails, BookingDetails bookingDetails, int concertID){

        concertDetails.getSeatLayout(concertID);

        System.out.print("Enter the aisle number: ");
        String aisleNumber = Constants.KEYBOARD.nextLine();
        System.out.print("Enter the seat number: ");
        int seatNumber = Integer.parseInt(Constants.KEYBOARD.nextLine());
        System.out.print("Enter the number of seats to be booked: ");
        int totalTickets = Integer.parseInt(Constants.KEYBOARD.nextLine());

        bookingDetails.bookConcertSeats(concertDetails, customerID, customerName, concertID, aisleNumber, seatNumber, totalTickets);
    }

    /**
     * Display bookings for selected customer in selected concert 
     * @param concertDetails Concert Details to be shown to the user
     * @param bookingDetails Booking Details to be shown to the user
     * @param concertID Id of the selected concert 
     */
    public void displayCustomerBooking(ConcertDetails concertDetails, BookingDetails bookingDetails, int concertID){
        
        List<Booking> customerBookingList = new ArrayList<>();

        for(Booking booking: bookingDetails.getBookingList()){       
            if (customerID != booking.getBookingCustomerID() || concertID!= booking.getBookingConcertID()){
                continue;      
            }else{
                customerBookingList.add(booking);
            }
        }
        
        if(customerBookingList.isEmpty()){
            System.out.println("No Bookings found for this concert");
        }else{
            BookingDetails customerBookingDetails = new BookingDetails(customerBookingList);
            customerBookingDetails.displayBooking(concertDetails);
        }

        System.out.println();
    }


    public int getCustomerID(){
        return customerID;
    }

    public String getCustomerName(){
        return customerName;
    }

    public String getCustomerPassword(){
        return customerPswd;
    }
}
