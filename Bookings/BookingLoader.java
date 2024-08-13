package Bookings;
import java.util.*;
import java.io.*;
import Exceptions.*;
import Utils.*;

/**
 * BookingLoader class for managing booking file
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class BookingLoader implements FileLoader<Booking>{

    /**
     * Read booking file 
     * @param bookingFileName Booking file name 
     * @return booking list
     * @throws FileNotFoundException, IOException
     */
    public List <Booking> readFile (String bookingFileName)throws FileNotFoundException, IOException{

        try{
            Scanner inputStream = null;
            List <Booking> bookingList = new ArrayList<>();

            // create a new scanner to read input file
            inputStream = new Scanner(new FileInputStream(bookingFileName));

            while (inputStream.hasNextLine()) {
                String bookingLine = inputStream.nextLine();
                String[] bookingInfo = bookingLine.split(","); 
                try{           
                    if(bookingInfo.length < 10){
                        throw new InvalidLineException();
                    }
                    // might throw NumberFormatException nfe
                    int bookingID = Integer.parseInt(bookingInfo[0]);
                    int bookingCustomerID = Integer.parseInt(bookingInfo[1]);
                    String bookingCustomerName = bookingInfo[2];
                    int bookingConcertID = Integer.parseInt(bookingInfo[3]);
                    int totalTickets = Integer.parseInt(bookingInfo[4]);

                    if(totalTickets==0){
                        throw new InvalidFormatException("Incorrect Number of Tickets. Skipping this line.");
                    }
                    List <Ticket> ticketList = new ArrayList<>();
                    for(int i=5; i<bookingInfo.length; i+=5){
                        int ticketID = Integer.parseInt(bookingInfo[i]);
                        int rowNumber = Integer.parseInt(bookingInfo[i+1]);
                        int seatNumber = Integer.parseInt(bookingInfo[i+2]);
                        String zoneType = bookingInfo[i+3];
                        float price = Float.parseFloat(bookingInfo[i+4]);
                        Ticket concerTicket = new Ticket(ticketID, rowNumber, seatNumber, zoneType, price);
                        ticketList.add(concerTicket);
                    }
                    Booking concertBooking = new Booking(bookingID, bookingCustomerID, bookingCustomerName, bookingConcertID, totalTickets, ticketList); 
                    bookingList.add(concertBooking);
                    
                }catch(InvalidLineException ile){
                    System.out.println("Invalid booking Files. Skipping this line.");
                    continue;
                }catch(NumberFormatException nfe){
                    System.out.println("Booking Id is in incorrect format. Skipping this line.");
                    continue;
                }catch(InvalidFormatException ife){
                    System.out.println(ife.getMessage());
                    continue;
                }
            }
            return bookingList;

        }catch(FileNotFoundException ffe){
            throw new FileNotFoundException(bookingFileName + " (No such file or directory)");
        }
    }
    
    /**
     * Save booking file 
     * @param bookingFileName Booking file name 
     * @param bookingList List of Bookings
     * @throws FileNotFoundException, IOException
     */
    public void saveFile(String bookingFileName, List<Booking> bookingList) throws FileNotFoundException, IOException{
        
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(bookingFileName);
            for (Booking booking: bookingList){  
                String bookingLine = booking.getBookingID() + "," + booking.getBookingCustomerID() + "," + booking.getBookingCustomerName() + "," + booking.getBookingConcertID() + "," + booking.getTotalTickets();         
                for(Ticket ticket: booking.getTicketList()){     
                    bookingLine += "," + ticket.getTicketID() + "," + ticket.getRowNumber() + "," + ticket.getSeatNumber() + "," + ticket.getZoneType() + "," + Math.round(ticket.getPrice());
                }  
                writer.println(bookingLine);
            }
            writer.flush();
        }finally{
            if(writer != null)
                writer.close();       
        }
    }


}
