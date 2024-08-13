package Utils;
import java.io.*;
import java.util.*;
import Users.*;
import Concerts.*;
import Exceptions.*;
import Bookings.*;

/**
 * FileLoader class implements file loading 
 * @author : Xian Wan Lo, xlo@student.unimelb.edu.au, 1489247
 */
public interface FileLoader<T>{

    /**
     * Read input file and generate corresponding Object list
     * @return Lists of object 
     * @throws FileNotFoundException, IOException
     */
    public List<T> readFile (String fileName) throws FileNotFoundException, IOException;

    /**
     * Save  Object list into corresponding file 
     * @param fileName File name
     * @param dataList Object list
     * @throws FileNotFoundException, IOException
     */
    public void saveFile(String fileName, List<T> dataList) throws FileNotFoundException, IOException;


}
