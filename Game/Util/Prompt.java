package Game.Util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Prompt {

    private static final Scanner in = new Scanner(System.in); // static scanner - used for input
    public final Double TAX = 0.13; // Tax Number

    /**
     * A method to return an integer
     * 
     * @param prompt programmer provides a prompt for the user
     * @return an integer value
     */
    public static int getInt(String prompt) {

        int answer; // users answer
        String input; // users input

        while (true) {
            System.out.println(prompt); // print out the prompt

            // If the answer is an integer
            if (Prompt.in.hasNextInt()) {
                answer = Prompt.in.nextInt();
                in.nextLine();
                return answer;

            } else { // when the answer is not an integer
                input = Prompt.in.nextLine();
                System.out.println(input + " is not an integer. Please try again");
            }

        }

    }


    /**
     * A method to return an integer
     * 
     * @param prompt programmer provides a prompt for the user
     * @return an integer value
     */
    public static double getDouble(String prompt) {

        double answer; // users answer
        String input; // users input

        while (true) {
            System.out.println(prompt); // print out the prompt

            // If the answer is an integer
            if (Prompt.in.hasNextDouble()) {
                answer = Prompt.in.nextDouble();
                in.nextLine();
                return answer;

            } else { // when the answer is not an integer
                input = Prompt.in.nextLine();
                System.out.println(input + " is not a double. Please try again");
            }

        }

    }

    public static String getString(String prompt) {

        System.out.println(prompt);
        String st = Prompt.in.nextLine();
        return st;
    }

    public static File getFile(String prompt) {
        while (true) {
            System.out.println(prompt);
            String fileName = in.nextLine().trim();
            File inputFile = new File(fileName);
            if (!inputFile.exists()) {
                System.out.println("Error: " + fileName + " does not exist.");
            } else if (inputFile.isDirectory()) {
                System.out.println("Error: " + fileName + " is a directory.");
            } else if (!inputFile.canRead()) {
                System.out.println("Error: " + fileName + " is not readable.");
            } else {
                return inputFile;
            }
        }
    }

    public static Scanner getInputScanner(String prompt) {
        try {
            return new Scanner(Prompt.getFile(prompt));
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            System.out.println("in" + System.getProperty("user.dir"));
            System.exit(1);
        }
        return null;
    }

    public static PrintWriter getPrintWriter() {
        // System.out.println(prompt);
        String fileName = "output.txt";
        File outputFile = new File(fileName);

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
