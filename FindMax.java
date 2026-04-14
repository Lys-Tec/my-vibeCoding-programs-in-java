


import java.util.InputMismatchException;
import java.util.Scanner;

public class FindMax {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
while(true){

    try{

         System.out.println("Enter the first integer:");
        int num1 = scanner.nextInt();

        System.out.println("Enter the second integer:");
        int num2 = scanner.nextInt();

        System.out.println("Enter the third integer:");
        int num3 = scanner.nextInt();

        int max = findMax(num1, num2, num3);

        System.out.println("The maximum of " + num1 + ", " + num2 + ", and " + num3 + " is " + max);
        
        break;
        
    }
    catch(InputMismatchException e){
            System.out.println("Wrong Input, Somethin' went wrong!!!");
            
           scanner.nextLine();
            
        }
            

    }
    scanner.close();

}

       
    public static int findMax(int num1, int num2, int num3) {
        return Math.max(Math.max(num1, num2), num3);
    }
}
