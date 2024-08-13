package Exceptions;

/**
 * InvalidLineException class handles missing data points in the input file
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class InvalidLineException extends Exception{

    public InvalidLineException(){
        super();
    }

    public InvalidLineException(String message){
        super(message);
    } 
}
