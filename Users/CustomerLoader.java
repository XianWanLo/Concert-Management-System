package Users;
import java.util.*;
import java.io.*;
import Exceptions.*;
import Utils.*;

/**
 * CustomerLoader class for managing customer file
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class CustomerLoader implements FileLoader<Customer>{

    /**
     * Read customer file 
     * @param customerFileName Customer file name 
     * @return customer list
     * @throws FileNotFoundException, IOException
     */
    public List <Customer> readFile (String customerFileName) throws FileNotFoundException, IOException{
        
        try{
            Scanner inputStream = null;
            List <Customer> customerList = new ArrayList<>();

            // create a new scanner to read input file
            inputStream = new Scanner(new FileInputStream(customerFileName));

            while (inputStream.hasNextLine()) {
                
                String customerLine = inputStream.nextLine();
                String[] customerInfo = customerLine.split(","); 
                
                try{
                    if(customerInfo.length < 3){
                        throw new InvalidLineException();
                    }
                    // might throw NumberFormatException nfe
                    int customerID = Integer.parseInt(customerInfo[0]);
                    String customerName = customerInfo[1];
                    String customerPassword = customerInfo[2];
                    
                    Customer customer = new Customer(customerID, customerName, customerPassword);    
                    customerList.add(customer);
                    
                }catch(InvalidLineException ile){
                    System.out.println("Invalid Customer Files. Skipping this line.");
                    continue;

                }catch(NumberFormatException nfe){
                    System.out.println("Customer Id is in incorrect format. Skipping this line.");
                    continue;
                }
            }   
            return customerList;

        }catch(FileNotFoundException ffe){
            throw new FileNotFoundException(customerFileName + " (No such file or directory)");
        }    
    }

    /**
     * Save customer file 
     * @param customerFileName Customer file name 
     * @param newCustomer New registered customer
     * @throws FileNotFoundException, IOException
     */
    public void saveFile(String customerFileName, List<Customer> customerList) throws FileNotFoundException, IOException{
        
        PrintWriter writer = null;
        try{
            for(Customer customer: customerList){
                writer = new PrintWriter (new FileOutputStream (customerFileName, true));
                String customerLine = customer.getCustomerID() + "," + customer.getCustomerName() + "," + customer.getCustomerPassword();
                writer.println(customerLine);
            }
            writer.flush();
        }finally{
            if(writer != null)
                writer.close();       
        }
    }


    
}
