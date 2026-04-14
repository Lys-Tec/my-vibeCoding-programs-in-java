
/**
 * 
 * A code implementation of a program that is able to calculate
 * the largest number of three 
 * different numbers/integers 
 * @author Houghton.
 * @author Chibozyi.
 * With the help of @author Agness Machasa 
 * 
 */
//////////////////////////////////////////////////////////////////////////

/*Importation opf the java utility scanner that is able to detect input from the keyboard*/
import java.util.Scanner;

/*importing the InputMisMatchException */

import java.util.InputMismatchException;


//class header
public class TheBiggest{
    

    //main method 
    public static void main(String[] args){

        //Declaring the scanner class to the main method
        Scanner find_max = new Scanner(System.in);

        //WELCOME TEXT
        System.out.println("HELLO I CAN FIND THE LARGEST NUMBER AMONG FIVE NUMBERS FOR YOU");
try{
        //Prompts user to enter number
        System.out.println("Enter first Number of your choice:");
        int num1 = find_max.nextInt();


        //Prompts user to enter number
        System.out.println("Enter Second Number of your choice");
        int num2 = find_max.nextInt();
        
        
        //Prompts user to enter number
        System.out.println("Enter the third please, Don't get tired.");
        int num3 = find_max.nextInt();
    
        //Prompts user to enter number
        System.out.println("Enter fourth number Number of your choice");
        int num4 = find_max.nextInt();
        
        //Prompts user to enter number
        System.out.println("Enter the last one,... Any  Number of your choice");
        int num5 = find_max.nextInt();

        //finding the maximum number
        
        int max = num1;

        if(num2 > num1)
        max = num2;


        if(num3 > num1)
        max = num3;


        if(num4 > num1)
        max = num4;

        
        if(num5 > num1)
        max = num5;

                System.out.println("THE LARGEST NUMBER AMONG THESE NUMBERS YOU CHOSE IS: " + max);
                }catch (InputMismatchException e){
                    System.out.println("Wrong Input! Please enter integers only.");
                    

            }
        }      
            
    }
        
        
        

    