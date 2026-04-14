import java.util.Scanner;


public class ValuesReturning{

    public static void main (String [] args){

        Scanner values_Return = new Scanner(System.in);

        System.out.println("Returning Numbers and giving the answer of the sum");

        System.out.println("Enter first Number");
        double num1 = values_Return.nextDouble();

        System.out.println("Enter Second Number");
        double num2 = values_Return.nextDouble();
            
            
        
        System.out.println("THE RESULT IS " + sum(num1, num2));
        values_Return.close();
    }
    public static double sum(double num1, double num2){
        return num1 * num2;
    }
}
