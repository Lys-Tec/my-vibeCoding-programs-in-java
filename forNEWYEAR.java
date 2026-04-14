import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Program to CountDown to New Year
 * @author H
 * 
 */
public class forNEWYEAR {
    public static void main(String[] args) throws InterruptedException {

        // for loop  = execute some code a CERTAIN amount of times

        Scanner scanner = new Scanner(System.in);
        int start =0;

        while(true){
try{
        System.out.print("How many seconds to countdown from? (Enter -1 to Exit): ");
               start = scanner.nextInt();
         
        
if(start == -1){
    System.out.println("GOODBYE!");
    scanner.close();
    return;
}
if(start <=0 || start > 10){
    System.out.println("Please Enter a positive number <= 10:");
    continue;
}
break;

 } catch(InputMismatchException e){
System.out.println("Something went Wrong! Please enter a valid number(e.g an Integer)");
scanner.next();//to clear invalid input
 }
        }// countdown
        if (start > 0){
        for(int i = start; i >= 0 && i <= 10; i--){
            
            System.out.println(i);

            Thread.sleep(1000);
        }
        
        System.out.println("HAPPY NEW YEAR!!!!!!!!!!!");
        }
                          scanner.close();
      }
    }
