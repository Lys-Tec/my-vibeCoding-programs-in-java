
/**
 * 
 * A code implementation of a program that is able to calculate
 * the average number of three 
 * different numbers/integers 
 * @author Houghton.
 * @author Chibozyi.
 * With the help of @author Agness Machasa 
 * 
 */

/* importation of the java utility scanner
to Get input of the user through the keyboard */
import java.util.Scanner;

//class
public class AverageCounter{

    //Declaring the scanner class
    Scanner input = new Scanner(System.in);

    //main method
    public static void main(String [] args){

        //Printing method for the welcome text
        System.out.print("WELCOME");

        //Prompts user to enter the first score
        System.out.println("Enter first Score");
       double score1; 
              score1 = input.nextDouble();

        //Prompts user to enter the Second score
        System.out.println("Enter Second score");
        double score2;
               score2 = input.nextDouble();
        
        //Prompts user to enter the third score
        System.out.println("Enter Third score");
        double score3;
               score3 = input.nextDouble();

                //Declaring the average
               double average(score1 + score2 + score3)/3;

System.out.println("The Average is:" + average);

        


    }

}


