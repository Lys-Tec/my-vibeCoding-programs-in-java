
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
import java.util.InputMismatchException;

//class
public class AverageCounter{

    

    //main method
    public static void main(String [] args){
try{
        //Declaring the scanner class
    Scanner in = new Scanner(System.in);

        //Printing method for the welcome text
        System.out.print("*HEYYYYYY*\n");

        //Prompts user to enter the first score
        System.out.println("Enter first Score");
       double score1 = in.nextDouble();

        //Prompts user to enter the Second score
        System.out.println("Enter Second score");
        double score2 = in.nextDouble();
        
        //Prompts user to enter the third score
        System.out.println("Enter Third score");
        double score3 = in.nextDouble();

                //Declaring the average
               double average = (score1 + score2 + score3)/3;

System.out.println("The Average is:" + average);
}
catch(InputMismatchException e){
    System.out.println("ERROR");
    
}
    }


    
}


