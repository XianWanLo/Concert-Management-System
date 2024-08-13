package Utils;
import java.util.Scanner;

/**
 * Constant class manages all constant variable throughout the classes
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class Constants{

    /**
     * Provides a constant scanner object that can be used to take input
     */
    public static final Scanner KEYBOARD = new Scanner (System.in);

    /**
     * Constants for users 
     */
    public final static String ADMIN_MODE = "--admin";
    public final static String CUSTOMER_MODE = "--customer";
    
    /**
     * Constants for Customer 
     */
    public final static int CUSTOMER_TICKET_COST = 1;
    public final static int CUSTOMER_VIEW_LAYOUT = 2;
    public final static int CUSTOMER_BOOK_SEATS = 3;
    public final static int CUSTOMER_BOOKING_DETAIL = 4;
    public final static int CUSTOMER_EXIT = 5;

    /**
     * Constants for Admin
     */
    public final static int ADMIN_CONCERTS_DETAIL = 1;
    public final static int ADMIN_UPDATE_PRICE = 2;
    public final static int ADMIN_BOOKING_DETAIL = 3;
    public final static int ADMIN_VIEW_PAYMENT = 4;
    public final static int ADMIN_EXIT = 5;

}
