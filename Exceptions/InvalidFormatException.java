package Exceptions;
import java.io.*;

/**
 * InvalidFormatException class handles incorrect format of data points
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class InvalidFormatException extends Exception{

    public InvalidFormatException(){
        super();
    }

    public InvalidFormatException(String message){
        super(message);
    } 
}
