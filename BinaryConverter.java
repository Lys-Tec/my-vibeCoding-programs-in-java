
import java.util.Scanner;
import java.util.InputMismatchException;

public class BinaryConverter{

    public static void main(String args []){

        Scanner input = new Scanner(System.in);

while(true){
    
try{   
    
     System.out.println("PLEASE ENTER THE FIRST POSITIVE INTEGER: ");
        int number1 = input.nextInt();
         if (number1 <= 0) {
                System.out.println("Error: Only positive integers are allowed.\n ");
                return;
            }

        System.out.println("PLEASE ENTER THE SECOND POSITIVE INTEGER: ");
        int number2 = input.nextInt();
         if (number2 <= 0) {
                System.out.println("Error: Only positive integers are allowed.\n ");
                return;
            }

        System.out.println("PLEASE ENTER THE THIRD POSITIVE INTEGER: ");
        int number3 = input.nextInt();
         if (number3 <= 0) {
                System.out.println("Error: Only positive integers are allowed.\n ");
                return;
            }

            System.out.println("The Binary number for " + number1 + " is: " + Integer.toBinaryString(number1));
            System.out.println("The Binary number for " + number2 + " is: " + Integer.toBinaryString(number2));
            System.out.println("The Binary number for " + number3 + " is: " + Integer.toBinaryString(number3));

    } catch( InputMismatchException e){
            System.out.println("Please Enter Correct Digits(Only Positive intergers)");
            System.out.println(" ");
       }
       input.nextLine();
        
            }//end while
        }//end main
}//end class