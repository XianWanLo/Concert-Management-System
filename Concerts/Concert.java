package Concerts;

/**
 * Concert class manages the variables of a concert
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class Concert {

    /* data fields */
    private int concertID;
    private String date;  
    private String timing;                
    private String artistName;  
    private String venueName;  
    private float[] standingPrice;
    private float[] seatingPrice;
    private float[] vipPrice;
    private Venue concertVenue;

    /**
     * This is a parameterised constructor that creates a concert object
     * @param concertID Id of the concert
     * @param date Date of the concert in format yyyy-MM-dd
     * @param timing Start and End Time of the concert in format HHmm-HHmm
     * @param artistName Artist name
     * @param venueName Venue Name
     * @param standingPrice The prices of Left, Center and Right sections in standing zone 
     * @param seatingPrice The prices of Left, Center and Right sections in seating zone 
     * @param vipPrice The prices of Left, Center and Right sections in VIP zone 
     * @param concertVenue The venue object
     */
    public Concert(int concertID, String date, String timing, String artistName, String venueName, float[] standingPrice, float[] seatingPrice, float[] vipPrice, Venue concertVenue){

        this.concertID = concertID;
        this.date = date;
        this.timing = timing;
        this.artistName = artistName;
        this.venueName = venueName;
        this.standingPrice = standingPrice;
        this.seatingPrice = seatingPrice;
        this.vipPrice = vipPrice;
        this.concertVenue = concertVenue;
    }

    /**
     * Find the price of a certain seat in the concert given its zone and seat number
     * @param zoneType Type of the seating zone 
     * @param seatNumber The seat number 
     * @return the price of selected seat
     */
    public float findPrice(String zoneType, int seatNumber){

        float price = 0;
        int columnType = concertVenue.getColumnType(seatNumber); 

        switch(zoneType){
            case "VIP":
                price = this.vipPrice[columnType];
                break;

            case "SEATING":
                price = this.seatingPrice[columnType];
                break;

            case "STANDING":
                price = this.standingPrice[columnType];
                break;
        }
        return price;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public int getID(){
        return concertID;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public String getDate(){
        return date;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public String getTiming(){
        return timing;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public String getArtistName(){
        return artistName;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public String getVenueName(){
        return venueName;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public float[] getStandingPrice(){
        return standingPrice;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public float[] getSeatingPrice(){
        return seatingPrice;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public float[] getVipPrice(){
        return vipPrice;
    }

    /**
     * Returns the id of the concert booked in the booking
     * @return concert id
     */
    public Venue getVenue(){
        return concertVenue;
    }

    /**
     * Set the venue of the concert
     * @param venue Venue
     */
    public void setVenue(Venue venue){
        this.concertVenue = venue;
    }

    /**
     * Set the prices of Left, Center and Right sections in VIP zone
     * @param newVipPrice New prices of Left, Center and Right sections in VIP zone
     */
    public void setVipPrice(float[] newVipPrice){
        this.vipPrice = newVipPrice;
    }

    /**
     * Set the prices of Left, Center and Right sections in Seating zone
     * @param newVipPrice New prices of Left, Center and Right sections in Seating zone
     */
    public void setSeatingPrice(float[] newSeatingPrice){
        this.seatingPrice = newSeatingPrice;
    }

    /**
     * Set the prices of Left, Center and Right sections in Standing zone
     * @param newVipPrice New prices of Left, Center and Right sections in Standing zone
     */
    public void setStandingPrice(float[] newStandingPrice){
        this.standingPrice = newStandingPrice;
    }
    
}
