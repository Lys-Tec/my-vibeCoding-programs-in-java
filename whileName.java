import java.util.Scanner;

public class whileName{
    public static void main(String [] args){

        Scanner scanner = new Scanner(System.in);
        String name = " ";
        System.out.println("Please Enter Your Name");
            name = scanner.nextLine();
        while(name.isEmpty()){
          System.out.println("Please Enter Your Name");  
    
            
        }
        System.out.println("Hello! " + name);
    }
}
