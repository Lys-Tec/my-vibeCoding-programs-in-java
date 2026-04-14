import java.util.Scanner;
/**
 * A while loop for input validation
 * where a number entered should be within the specified range
 * @author H
 */
public class While{
    public static void main (String [] args){
        Scanner keyboard = new Scanner(System.in);
        int number;
        System.out.print("Enter a number in the " +
                 "range of 1 through 100: ");
        number = keyboard.nextInt();

while (number < 1 || number > 100)
{
  System.out.println("That number is invalid.");
  System.out.print("Enter a number in the " +
                   "range of 1 through 100: ");
  number = keyboard.nextInt();
  keyboard.close();
}

    }
}