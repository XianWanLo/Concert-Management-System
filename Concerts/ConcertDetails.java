package Concerts;
import Users.*;
import java.io.*;
import java.util.*;
import Bookings.*;
import Utils.*;

/**
 * ConcertDetails class implements concert related functions 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class ConcertDetails{

    /*list of concerts to be managed*/
    private List<Concert> concertList;
   
    /**
     * This is a parameterised constructor that creates a ConcertDetails object
     * @param concertList The list of concerts
     */
    public ConcertDetails( List<Concert> concertList){
        this.concertList = concertList;
    }

    /**
     * Load the corresponding venues for each concert in the concert list
     * @param venueFileNames The venue files that is parsed from args
     * @param fh The file handler 
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadVenue (List<String> venueFileNames, ConcertLoader cl) throws FileNotFoundException, IOException{

        for(Concert concert: concertList){
            // load venue file 
            String venueName = concert.getVenueName();
            String venueFile = "assets/venue_" + venueName.toLowerCase() +".txt";
            Venue venue = null;
            
            if(venueFileNames.contains(venueFile)){
                venue = cl.readVenueFile(venueFile);
                concert.setVenue(venue);
            }else{
                continue;
            }
        }
    }
    
    /**
     * Update the previous booking for each concert in the concert list
     * @param bookingDetails The details of all previous bookings
     */
    public void updateBooking(BookingDetails bookingDetails){

        for (Booking booking: bookingDetails.getBookingList()){

            int ID = booking.getBookingConcertID() - 1;
            List<Ticket> ticketList = booking.getTicketList();
            
            for(Ticket ticket: ticketList){     
                int rowNumber = ticket.getRowNumber();
                int seatNumber = ticket.getSeatNumber();
                String zoneType = ticket.getZoneType();      
                concertList.get(ID).getVenue().updateBookedSeat(rowNumber, seatNumber, zoneType);
            }
        }
    }

    /**
     * Print the details of all concerts
     */
    public void getConcertsInfo() {

        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-30s%-15s%-15s%-15s\n","#","Date", "Artist Name", "Timing", "Venue Name", "Total Seats", "Seats Booked", "Seats Left"); 
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        for(Concert concert: concertList){
			
            System.out.printf("%-5d%-15s%-15s%-15s%-30s%-15d%-15d%-15d\n", concert.getID(), concert.getDate(), concert.getArtistName(), concert.getTiming(), concert.getVenueName(), concert.getVenue().getTotalSeats(), concert.getVenue().getSeatsBooked(), concert.getVenue().getSeatsLeft());  
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
     }
    
    /**
     * Prints the seat layout for selected concert
     */
    public void getSeatLayout(int concertID){
        int ID = concertID - 1;
        concertList.get(ID).getVenue().printLayout();
    }

    /**
     * Returns the list of concert
     * @return concert list
     */
    public List<Concert> getConcertList(){
        return concertList;
    }

}
