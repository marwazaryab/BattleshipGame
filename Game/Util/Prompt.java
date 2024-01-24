package Game.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @since 1/24/2024
 * 
 *        Prompt Class
 *        A class used to get a printwriter
 */
public class Prompt {

    private static final Scanner in = new Scanner(System.in); // static scanner - used for input

    /**
     * A method to create a new printwriter
     * @return printwriter 
     */
    public static PrintWriter getPrintWriter() {
        String fileName = "output.txt"; // file name - makes a new file called output.txt
        File outputFile = new File(fileName);

        // Try catch to make a new file
        try {
            return new PrintWriter(outputFile);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("in" + System.getProperty("user.dir"));
            System.exit(1);
        }
        return null;
    }

}
