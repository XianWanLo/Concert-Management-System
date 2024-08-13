package Concerts;
import java.util.Scanner;
import Users.*;
import Utils.*;

/**
 * Venue class manage the venue related operation
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */

public class Venue{

    /* data fields */ 
    private int vipRowCount;
    private int seatingRowCount;
    private int standingRowCount;
    private int fullRowCount;
    private int leftColumn;
    private int midColumn;
    private int rightColumn;
    private int fullColumn;    
    private int totalSeats;
    private int seatsBooked;
    private int seatsLeft;
    private String[][] seatLayout;

    /**
     * This is a parameterised constructor that creates a venue object
     * @param zoneRowCount The number of rows for VIP, Seating and Standing zone in the venue
     * @param zoneSeatCount The number of seats per row in the venue
     */
    public Venue(int[]zoneRowCount, int[]zoneSeatCount){
        
        this.vipRowCount = zoneRowCount[0];
        this.seatingRowCount = zoneRowCount[1];
        this.standingRowCount = zoneRowCount[2];
        this.leftColumn = zoneSeatCount[0];
        this.midColumn = zoneSeatCount[1];
        this.rightColumn = zoneSeatCount[2];
        this.fullRowCount = vipRowCount + seatingRowCount + standingRowCount;
        this.fullColumn = 2 + leftColumn + 1 + midColumn + 1 + rightColumn + 2; 
        this.totalSeats = fullRowCount *(leftColumn + midColumn + rightColumn);
        this.seatsBooked = 0;
        this.seatsLeft = totalSeats;
        this.seatLayout = new String[fullRowCount][fullColumn];
        this.initialLayout();    
    }

    /**
     * Initialise the seat layout 
     */
    private void initialLayout(){

        int zoneRowCount = 1;
        int pointer = 0;
        // For each row in the seat map
        for (int i=0; i < fullRowCount; i++){
            
            pointer = 0;

            if( i==vipRowCount | i==vipRowCount + seatingRowCount){
                zoneRowCount = 1;
            }
            // VIP HEADING
            if (i < vipRowCount){
                seatLayout[i][pointer++] = "V" + (zoneRowCount++);
                seatLayout[i][pointer++] = " ";
            }
            // SEATING HEADING
            if ((i >= vipRowCount) && (i < vipRowCount + seatingRowCount)){
                seatLayout[i][pointer++] = "S" + (zoneRowCount++);
                seatLayout[i][pointer++] = " ";
            }
            // STANDING HEADING
            if ((i >= vipRowCount + seatingRowCount) && (i < fullRowCount)){
                seatLayout[i][pointer++] = "T" + (zoneRowCount++);
                seatLayout[i][pointer++] = " ";
            }
            // Left Zone
            int seatCount = 1;
            for (int a=0; a < (leftColumn + midColumn + rightColumn ); a++){               
                
                seatLayout[i][pointer++] = "[" + (seatCount++) + "]";

                if(a == leftColumn-1 || a == leftColumn  + midColumn - 1 ){
                    seatLayout[i][pointer++] = " ";
                }
            }
            seatLayout[i][pointer++] = " ";
            seatLayout[i][pointer] = seatLayout[i][0];
        }
    }
    
    /**
     * Print current seat layout 
     */
    public void printLayout(){

        // for each row in the seat map
        for (int i=0; i < fullRowCount; i++){
            
            // empty row between zones
            if(i==vipRowCount | i == vipRowCount + seatingRowCount){
                System.out.println();
            }
            // print out each seat in the row
            for (int j=0; j < fullColumn; j++){
                System.out.print(seatLayout[i][j]);
            }
            // next row
            System.out.println();
        }
    }

    /**
     * Update seat status according to customer booking info 
     * @param rowNumber The selected row number 
     * @param seatNumber The selected seat number
     * @param zoneType Type of seating zone
     */
    public void updateBookedSeat(int rowNumber, int seatNumber, String zoneType){
        
        int columnType = getColumnType(seatNumber);

        int pointerY = generatePointerY(zoneType, rowNumber);
        int pointerX = generatePointerX(columnType, seatNumber);

        seatLayout[pointerY][pointerX] = "[X]";
        seatsBooked += 1;
        seatsLeft -= 1;
    }


    /**
     * Generate the row position (Y-axis) of the selected seat 
     * @param zoneType Type of seating zone
     * @param rowNumber The selected row number 
     */
    private int generatePointerY(String zoneType, int rowNumber){
        
        int pointerY = 0;
        switch(zoneType){
            case "VIP":
                pointerY = rowNumber - 1;
                break;
            case "SEATING":
                pointerY = vipRowCount + rowNumber - 1;
                break;
            case "STANDING":
                pointerY = vipRowCount + seatingRowCount + rowNumber - 1;
                break;
        }
        return pointerY;
    }

    /**
     * Generate the seat position(X-axis) of the selected seat 
     * @param columnType Type of selected seating section
     * @param seatNumber The selected seat number
     */
    private int generatePointerX(int columnType, int seatNumber){
        
        int pointerX = 0;
        switch(columnType){
            case 0:
                pointerX = 1 + seatNumber;
                break;
            case 1:
                pointerX = 2 + seatNumber;
                break;
            case 2:
                pointerX = 3 + seatNumber;
                break;
        }
        return pointerX;
    }

    /**
     * Generate the seat section of the selected seat 
     * @param seatNumber The selected seat number
     */
    public int getColumnType(int seatNumber){
        
        int columnType = 0;

        if(seatNumber <= leftColumn){
            columnType = 0;     
        }else if(seatNumber > leftColumn && seatNumber <= (leftColumn + midColumn)){
            columnType = 1;        
        }else{
            columnType = 2;
        }
        return columnType;
    }

    
    /**
     * Returns the total number of seats in the venue
     * @return total number of seats
     */
    public int getTotalSeats(){
        return totalSeats;
    }

    /**
     * Returns the number of seats booked in the venue
     * @return number of booked seats
     */
    public int getSeatsBooked(){
        return seatsBooked;
    }
    
    /**
     * Returns the number of seats left in the venue
     * @return number of seats left
     */
    public int getSeatsLeft(){
        return seatsLeft;
    }

}
