import java.util.Scanner;
//import java.util.InputMismatchException;
/**
 * Age checking program,
 * to caheck for user signing up
 * reject to sign up if under 18
 * allows for those above 18
 * @author CQX"
 * 
 */
public class ageCheck{
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
    /**********************************************************/

        System.out.print("Please enter Your age To sign up: ");
        int age = scanner.nextInt();

        if(ageCheck(age)){
            System.out.println("Go ahead and sign Up!");
        } else {
            System.out.println("You Can Not Sign Up Because You're Under 18!");
        }
       scanner.close();
          }
                static boolean ageCheck(int age){
            if(age >= 18){
                  return true;
            } else {
               
                return false;
          
         }
  
    }
}       
/************************************************************/

    