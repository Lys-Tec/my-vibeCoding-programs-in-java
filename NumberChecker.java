

import java.util.Scanner;
import java.util.InputMismatchException;
public class NumberChecker{

    public static void main(String [] args){
while(true){
    Scanner scanner = new Scanner(System.in);
        

             try{
        

             System.out.println("\n\tEnter A Number: ");
             double number = scanner.nextDouble();
        
        if(number < 0){
            System.out.println("Number " +number+" Is Negative!");

        }
        else if(number == 0){
            System.out.println("Number " +number+" is equal to Zero(0)!");

        }
        else{
            System.out.println("Number " +number+" Is Positive!");
        }

    }
            catch (InputMismatchException e) {
            System.out.println("Please Enter Correct Digits (Only Numbers)");
           
        }
         scanner.nextLine(); // clears invalid input
        }
    }   
}