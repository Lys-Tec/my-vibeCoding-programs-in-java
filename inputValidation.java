


import java.util.Scanner;

public class inputValidation{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number in the " +
                 "range of 1 through 100: ");
int num = scanner.nextInt();
// Validate the input.
if (num < 1 || num > 100)
{
  System.out.print("That number is invalid!");
  
 
  
}else{
    System.out.println("That Number is valid");
    
}
    

        }    

    }
