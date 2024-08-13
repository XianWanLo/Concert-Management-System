import java.io.*;
import java.util.*;
import Exceptions.*;
import Users.*;
import Utils.*;
import Concerts.*;
import Bookings.*;

/**
 * Engine class for running the entire project
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class TicketManagementEngine {

    /* data fields */ 
    private static int argsPointer;

    /**
     * This is a constructor that creates a TicketManagementEngine object
     */
    public TicketManagementEngine(){
    }

    /**
     * main method
     * @param args command line arguments passed to the program. 
     */
    public static void main(String[] args) {
        
        try{
            TicketManagementEngine tme = new TicketManagementEngine();
            CustomerLoader customerLoader = new CustomerLoader();
            ConcertLoader concertLoader = new ConcertLoader();
            BookingLoader bookingLoader = new BookingLoader();
            boolean oldUser = false;

            if(args[1].endsWith("csv")){
                argsPointer = 1;
            }else{
                argsPointer = 3;
                oldUser = true;
            }
            String customerFileName = args[argsPointer++];
            CustomerDetails customerDetails = tme.preLoadCustomerDetails(customerFileName, customerLoader);
            
            String concertFileName = args[argsPointer++];
            ConcertDetails concertDetails = tme.preLoadConcertDetails(concertFileName, concertLoader);
            
            String bookingFileName = args[argsPointer++];
            BookingDetails bookingDetails = tme.preLoadBookingDetails(bookingFileName, bookingLoader);
            tme.updateVenueAndBooking(concertDetails, bookingDetails, args, concertLoader);
            
            // run the user menu 
            String userMode = args[0];
            switch(userMode){

                case Constants.ADMIN_MODE: 
                    Admin admin = new Admin();    
                    System.out.println("Welcome to Ticket Management System Admin Mode.");
                    tme.displayMessage();
                    admin.mainOperation(concertDetails, bookingDetails);
                    break;
                
                case Constants.CUSTOMER_MODE:
                    Customer customer = tme.checkCustomerStatus(args, customerDetails, oldUser);
                    System.out.println("Welcome " + customer.getCustomerName() + " to Ticket Management System");
                    tme.displayMessage();
                    customer.mainOperation(concertDetails, bookingDetails);
                    break;
                
                default:
                    System.out.println("Invalid user mode. Terminating program now.");
                    break;
            }
            if(!oldUser){
                customerLoader.saveFile(customerFileName, customerDetails.getCustomerList());
            }
            concertLoader.saveFile(concertFileName, concertDetails.getConcertList());
            bookingLoader.saveFile(bookingFileName, bookingDetails.getBookingList());

        }catch (NotFoundException nfe){
            System.out.println(nfe.getMessage());
        
        }catch (IncorrectPasswordException ipe){
            System.out.println(ipe.getMessage());
            
        }catch (FileNotFoundException ffe) {
            System.out.println(ffe.getMessage());
        
        }catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
    
    /**
     * Load customer file data into customer details object 
     * @param customerFileName Customer file name
     * @param cl Customer file loader
     * @return customer details object
     * @throws FileNotFoundException IOException
     */
    public CustomerDetails preLoadCustomerDetails(String customerFileName, CustomerLoader cl)throws FileNotFoundException, IOException{
        
        List <Customer> customerList = cl.readFile(customerFileName);
        CustomerDetails customerDetails = new CustomerDetails(customerList);
        return customerDetails;
    }

    /**
     * Load concert file data into concert details object 
     * @param concertFileName Concert file name
     * @param cl Concert file loader
     * @return concert details object
     * @throws FileNotFoundException IOException
     */
    public ConcertDetails preLoadConcertDetails(String concertFileName, ConcertLoader cl)throws FileNotFoundException, IOException{
        
        List <Concert> concertList = cl.readFile(concertFileName);
        ConcertDetails concertDetails = new ConcertDetails(concertList);
        return concertDetails;
    }

    /**
     * Load booking file data into booking details object 
     * @param bookingFileName Booking file name
     * @param bl Booking file loader
     * @return booking details object
     * @throws FileNotFoundException IOException
     */
    public BookingDetails preLoadBookingDetails(String bookingFileName, BookingLoader bl)throws FileNotFoundException, IOException{

        List <Booking> bookingList = bl.readFile(bookingFileName);
        BookingDetails bookingDetails = new BookingDetails(bookingList);
        return bookingDetails;
    }

    /**
     * Update venue and booking info in the current concert list
     * @param concertDetails Concert details object
     * @param bookingDetails Booking details object
     * @param args input files 
     * @param cl Concert file loader
     * @throws FileNotFoundException IOException
     */
    public void updateVenueAndBooking(ConcertDetails concertDetails, BookingDetails bookingDetails, String[] args, ConcertLoader cl)throws FileNotFoundException, IOException{
        
        String venueFile = null;
        List <String> venueFileNames = new ArrayList<>();
        while(argsPointer < args.length){
            venueFile = args[argsPointer++];
            venueFileNames.add(venueFile);
        }
        concertDetails.loadVenue(venueFileNames, cl);
        concertDetails.updateBooking(bookingDetails);
    }

    /**
     * Check the status of current customer
     * @param args input command lines
     * @param customerDetails Customer details object
     * @param oldUser indicator of old user
     * @return customer
     * @throws FileNotFoundException could not find the file 
     * @throws IOException could not find the file
     * @throws NotFoundException customer doesn't exist
     * @throws IncorrectPasswordException incorrect customer password
     */
    public Customer checkCustomerStatus(String[] args, CustomerDetails customerDetails, boolean oldUser)throws FileNotFoundException,IOException, NotFoundException, IncorrectPasswordException{

        Customer customer = null;
        String customerName = null;
        String customerPassword = null;
        int customerID = 0;

        // New customer prompt
        if (!oldUser){
            
            System.out.print("Enter your name: ");
            customerName = Constants.KEYBOARD.nextLine();
            System.out.print("Enter your password: ");
            customerPassword = Constants.KEYBOARD.nextLine();

            customerID = customerDetails.getCustomerList().size() + 1;

            customer = new Customer(customerID, customerName, customerPassword);
            customerDetails.getCustomerList().add(customer);  

            return customer;
        
        // check existing customer 
        } else{

            customerID = Integer.parseInt(args[1]);
            customerPassword = args[2];
            
            // might throw ArrayIndexOutOfBoundsException
            try{
                customer = customerDetails.getCustomerList().get(customerID-1);

            }catch (IndexOutOfBoundsException nfe){
                throw new NotFoundException("Customer does not exist. Terminating Program");
            }
            
            // might throw IncorrectPasswordException
            if(customerPassword.equals(customer.getCustomerPassword())){
                return customer;
            }else{
                throw new IncorrectPasswordException("Incorrect Password. Terminating Program");
            }
        }
    }

    /**
     * Display welcome message
     */
    public void displayMessage(){


        System.out.print("\n" +
                " ________  ___ _____ \n" +
                "|_   _|  \\/  |/  ___|\n" +
                "  | | | .  . |\\ `--. \n" +
                "  | | | |\\/| | `--. \\\n" +
                "  | | | |  | |/\\__/ /\n" +
                "  \\_/ \\_|  |_/\\____/ \n" +
                "                    \n" +
                "                    \n");
    }

}
