import java.util.Scanner;
import java.util.InputMismatchException;
/**
 * A Program to calculate the:
 *         1: Circumference of a circle,
 *         2: Area of a circle,
 *         3: Volume of the circle.
 *              Once Given the Radius 
 * @author Chibozyi
 * 
 * 
 */


    public class Circle{
        public static void main(String[] args){

            Scanner scanner = new Scanner(System.in);
         
           while(true){

                try{
            double radius;
            System.out.println("       Enter The radius, it must be a digit of course:");
            radius = scanner.nextDouble();

            

            double circumference;
                   circumference = 2 * Math.PI * radius;
            System.out.printf("\tThe Circumference For this Circle is: % ,.3fcm\n ", circumference );


            
            double area;
                   area = Math.PI * Math.pow(radius, 2);
            System.out.printf("\tThe Area for this Circle Is:          % ,.3fcm^2\n ", area );



            double volume;
                   volume = (4.0 / 3.0) * Math.PI* Math.pow(radius, 3);
            System.out.printf("\tThe Volume for this Circle Is:        % ,.3fcm^3\n ", volume);      


         }
                catch(InputMismatchException e){
                System.out.println("Like i Said... Enter a Fucking Digit IDIOT!");
                scanner.next();
                }
          
scanner.close();

break;
           
           }
            


                   
                   
            
        }
    }