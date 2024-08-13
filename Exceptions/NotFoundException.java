package Exceptions;

/**
 * NotFoundException class handles the situation of customer not found 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class NotFoundException extends Exception{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    } 
}
