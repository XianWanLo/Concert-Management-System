package Users;
import Users.*;
import java.io.*;
import java.util.*;
import Bookings.*;
import Utils.*;

/**
 * CustomerDetails class implements concert related functions 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class CustomerDetails{

    /*list of customer to be managed*/
    private List<Customer> customerList;

    /**
     * This is a parameterised constructor that creates a CustomerDetails object
     * @param customerList The list of customers
     */
    public CustomerDetails( List<Customer> customerList){
        this.customerList = customerList;
    }

    /**
     * Returns the list of customers
     * @return customer list
     */
    public List<Customer> getCustomerList(){
        return customerList;
    }
   
}
