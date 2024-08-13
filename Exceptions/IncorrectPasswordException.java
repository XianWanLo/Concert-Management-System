package Exceptions;

/**
 * IncorrectPasswordException class handles unmatched customer password
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException(){
        super();
    }

    public IncorrectPasswordException(String message){
        super(message);
    } 
}
