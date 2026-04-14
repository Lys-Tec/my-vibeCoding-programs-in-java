//A simple program to demonstrate the use of Scanner
//import Scanner
import java.util.Scanner;

public class myCalculator{
     public static void main(String[] args) {
     
     //Scanner object

     Scanner input = new Scanner(System.in);
    
     //Prompting the user to enter 20 numbers and taking them as input 
     System.out.println("Enter 1st Number: ");
     int Num1 = input.nextInt();

     System.out.println("Enter 2nd Number: ");
     int Num2 = input.nextInt();
     
     System.out.println("Enter 3rd Number: ");
     int Num3 = input.nextInt();

     System.out.println("Enter 4th Number: ");
     int Num4 = input.nextInt();

     System.out.println("Enter 5th Number: ");
     int Num5 = input.nextInt();

     System.out.println("Enter 6th Number: ");
     int Num6 = input.nextInt();

     System.out.println("Enter 7th Number: ");
     int Num7 = input.nextInt();

     System.out.println("Enter 8th Number: ");
     int Num8 = input.nextInt();

     System.out.println("Enter 9th Number: ");
     int Num9 = input.nextInt();

     System.out.println("Enter 10th Number: ");
     int Num10 = input.nextInt();

     System.out.println("Enter 11th Number: ");
     int Num11 = input.nextInt();

     System.out.println("Enter 12th Number: ");
     int Num12 = input.nextInt();

     System.out.println("Enter 13th Number: ");
     int Num13 = input.nextInt();

     System.out.println("Enter 14th Number: ");
     int Num14 = input.nextInt();

     System.out.println("Enter 15th Number: ");
     int Num15 = input.nextInt();

     System.out.println("Enter 16th Number: ");
     int Num16 = input.nextInt();

     System.out.println("Enter 17th Number: ");
     int Num17 = input.nextInt();

     System.out.println("Enter 18th Number: ");
     int Num18 = input.nextInt();

     System.out.println("Enter 19th Number: ");
     int Num19 = input.nextInt();

     System.out.println("Enter 20th Number: ");
     int Num20 = input.nextInt();

     System.out.println("Select an operator(+,*, or -)\n");
     String operator = input.nextLine();

     int Sum = Num1+Num2+Num3+Num4+Num5+Num6+Num7+Num8+Num9+Num10+Num11+Num12+Num13+Num14+Num15+Num16+Num17+Num18+Num19+Num20;
     int Difference = Num1+Num2+Num3+Num4+Num5+Num6+Num7+Num8+Num9+Num10+Num11+Num12+Num13+Num14+Num15+Num16+Num17+Num18+Num19+Num20;
     int Product = Num1+Num2+Num3+Num4+Num5+Num6+Num7+Num8+Num9+Num10+Num11+Num12+Num13+Num14+Num15+Num16+Num17+Num18+Num19+Num20;


     //if statments to display results various operations based on the users selection
     if (operator== "+") {
            System.out.println("The Sum is " + Sum);
}

     else if(operator== "-") {
          System.out.println("The Difference is " + Difference);
}

 else if(operator== "*") {
         System.out.println("The Product is " + Product);
}

else {
       System.out.println("Enter a valid operator.");
}

}//end main
}//end Class