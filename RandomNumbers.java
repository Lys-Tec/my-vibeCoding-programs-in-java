
/**importing utility classes for the required classes
 * @author  H.C
 */
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class RandomNumbers {
    public static void main(String[] args) throws IOException {

        // Create a Scanner object for keyboard input.
        Scanner scanner = new Scanner(System.in);

        // Create a Random object to generate random numbers.
        Random rand_om = new Random();

        // Create a PrintWriter object to open the file
        PrintWriter my_numbers = new PrintWriter("Houghton'sRandomNumbers.txt");

        // Get the number of random numbers to write.
        System.out.print("How many random numbers should I write? ");
        int maxNumbers = scanner.nextInt();

        // Write the random numbers to the file.
        for (int i = 0; i < maxNumbers; i ++) {

            // Generate a random integer (0 – 999 for example)
            int number = rand_om.nextInt(1000);

            // Write the random integer to the file.
            my_numbers.println(number);
        }

        // Close the file.
        my_numbers.close();

        System.out.println("Done writing random numbers");
    }
}


