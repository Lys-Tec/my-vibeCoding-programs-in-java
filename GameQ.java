import java.util.Scanner;



public class GameQ{


    public static void main (String [] args){
        Scanner scanner =  new Scanner(System.in);

        System.out.println("You're playing a game ");
        System.out.println("Enter q or Q to exit ");


        String response = scanner.nextLine();

     
        
        if(response.equalsIgnoreCase("Q")){
               System.out.println("You are a Quiter");


            
        }else{
            System.out.println("You Are Still Playing The Game ");
        }
        

    }

}