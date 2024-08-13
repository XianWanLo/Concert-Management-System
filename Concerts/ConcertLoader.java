package Concerts;
import java.util.*;
import java.io.*;
import Exceptions.*;
import Utils.*;

/**
 * ConcertLoader class for managing concert file
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class ConcertLoader implements FileLoader<Concert>{

    /**
     * Read concert file 
     * @param concertFileName Concert file name 
     * @return concert list
     * @throws FileNotFoundException, IOException
     */
    public List <Concert> readFile (String concertFileName) throws FileNotFoundException, IOException{

        try{
            Scanner inputStream = null;
            List <Concert> concertList = new ArrayList<>();

            // create a new scanner to read input file
            inputStream = new Scanner(new FileInputStream(concertFileName));

            while (inputStream.hasNextLine()) {  
                String concertLine = inputStream.nextLine();
                String[] concertInfo = concertLine.split(","); 
                try{
                    if(concertInfo.length!= 8){
                        throw new InvalidLineException();
                    } 
                    // might throw NumberFormatException nfe
                    int concertID = Integer.parseInt(concertInfo[0]);
                    String concertDate = concertInfo[1];
                    String concertTiming = concertInfo[2];
                    String artistName = concertInfo[3];
                    String venueName = concertInfo[4];

                    float[] standingPrice = new float [3];
                    String[] standingPriceString = concertInfo[5].split(":");
                    for(int i=0; i<standingPrice.length; i++){
                        standingPrice[i] = Float.parseFloat(standingPriceString[i+1]);
                    }
                    float[] seatingPrice = new float [3];
                    String[] seatingPriceString = concertInfo[6].split(":");
                    for(int i=0; i<seatingPrice.length; i++){
                        seatingPrice[i] = Float.parseFloat(seatingPriceString[i+1]);
                    }
                    float[] vipPrice = new float [3];
                    String[] vipPriceString = concertInfo[7].split(":");
                    for(int i=0; i<vipPrice.length; i++){
                        vipPrice[i] = Float.parseFloat(vipPriceString[i+1]);
                    }
                    Venue venue = readVenueFile("assets/venue_default.txt");
                    Concert concert = new Concert(concertID, concertDate, concertTiming, artistName, venueName, standingPrice, seatingPrice, vipPrice, venue);    
                    concertList.add(concert);
                    
                }catch(InvalidLineException ile){
                    System.out.println("Invalid Concert Files. Skipping this line.");
                    continue;
                }catch(NumberFormatException nfe){
                    System.out.println("Concert Id is in incorrect format. Skipping this line.");
                    continue;
                }
            }
            return concertList;

        }catch(FileNotFoundException ffe){
            throw new FileNotFoundException(concertFileName + " (No such file or directory)");
        }catch(IOException ioe){
            throw new IOException(concertFileName + " (No such file or directory)");
        }
    }

    /**
     * Save concert file 
     * @param concertFileName Concert file name 
     * @param concertList List of concerts
     * @throws FileNotFoundException, IOException
     */
    public void saveFile(String concertFileName, List<Concert> concertList) throws FileNotFoundException, IOException{
        
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(concertFileName);
            for (Concert concert: concertList){

                String concertLine = concert.getID() + "," + concert.getDate() + "," + concert.getTiming() + "," + concert.getArtistName() + "," + concert.getVenueName()+ "," ;
                concertLine += "STANDING" + ":" + Math.round(concert.getStandingPrice()[0]) + ":" + Math.round(concert.getStandingPrice()[1]) + ":" + Math.round(concert.getStandingPrice()[2])+ "," ;
                concertLine += "SEATING" + ":" + Math.round(concert.getSeatingPrice()[0]) + ":" + Math.round(concert.getSeatingPrice()[1]) + ":" + Math.round(concert.getSeatingPrice()[2])+ "," ;
                concertLine += "VIP" + ":" + Math.round(concert.getVipPrice()[0]) + ":" + Math.round(concert.getVipPrice()[1]) + ":" + Math.round(concert.getVipPrice()[2]);
                writer.println(concertLine);
            }
            writer.flush();
        }finally{
            if(writer != null)
                writer.close();       
        }
    }

    /**
     * Read venue file 
     * @param venueFileName venue file name 
     * @return venue 
     * @throws FileNotFoundException, IOException
     */
    public Venue readVenueFile (String venueFileName) throws FileNotFoundException, IOException{

        try{
            Scanner inputStream = null;
            inputStream = new Scanner(new FileInputStream(venueFileName));
            Venue venue = processVenueInfo(inputStream);     
            return venue;

        }catch(FileNotFoundException ffe){
            throw new FileNotFoundException(venueFileName + " (No such file or directory)");
        }
    }

    /**
     * Process venue file into venue parameters 
     * @param inputStream Venue file stream 
     * @return venue 
     */
    private Venue processVenueInfo(Scanner inputStream){
        
        int seatingRowCount = 0;
        int standingRowCount = 0;
        int[] zoneRowCount = new int[3];
        int[] zoneSeatCount = new int[3];
        boolean firstLine = true;

        while (inputStream.hasNextLine()) {     
            String venueLine = inputStream.nextLine();
            if (venueLine.isEmpty()){
                continue;
            }
            char initial = venueLine.charAt(0);      
            try{
                switch(initial){
                    case 'V': 
                        zoneRowCount[0] += 1;
                        break;
                    case 'S': 
                        zoneRowCount[1] += 1;
                        break;            
                    case 'T': 
                        zoneRowCount[2] += 1;
                        break;
                    default:
                        throw new InvalidFormatException();
                }
            }catch(InvalidFormatException ife){
                System.out.println("Invalid Zone Type. Skipping this line.");
                continue;
            }       

            if(firstLine){
                zoneSeatCount = seatCounting(venueLine);
                firstLine = false;
            }  
        }
        Venue venue = new Venue(zoneRowCount, zoneSeatCount);
        return venue;
    }

    /**
     * Count the seats per row in the venue 
     * @param venueLine each line in the venue file
     * @return seat count 
     */
    private int[] seatCounting(String venueLine){
        
        String[] rowDivision =  venueLine.split(" ");
        int[] zoneSeatCount = new int[3];
        int zone = 0;

        for(int i=1; i<4; i++){
            String[] seats = rowDivision[i].split("\\]\\[|\\[|\\]");

            for(int j=0;j<seats.length; j++){
                try{
                    Integer.parseInt(seats[j]);
                    zoneSeatCount[zone] += 1;

                }catch(NumberFormatException e){
                    continue;
                }
            }
            zone += 1;
        }
        return zoneSeatCount;
    }

}
