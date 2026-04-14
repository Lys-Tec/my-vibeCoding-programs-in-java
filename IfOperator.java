
import java.util.Scanner;


public class IfOperator{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Enter first number to represent x!");
        int x = input.nextInt();
        System.out.println("Enter second number to represent y!");
        int y = input.nextInt();

      
        if (x > y){
            System.out.println("X is greater than Y");
        }
        if (x < y){
            System.out.println("X is less than Y");
        }

    
if(x == y)

{
    System.out.println("X is equal to Y");

}
else if(x != y)
{
    System.out.println("Hence X is not equal to Y");
    
                 }
           }
    }