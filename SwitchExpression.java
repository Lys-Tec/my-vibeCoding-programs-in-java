/**
 * 
 * A program to demonstrate the switch expression
 * @author Chibozyi
 * */

//class main

public class SwitchExpression{
    //Main method
    public static void main (String [] args){
        /* The Swutch loop To comopare between different cases and figure out whuch one is true*/
                            switch(4){
                            //case 1
                            case 1: System.out.println("BasketBall");
                            break;
                            //case 2
                            case 2: System.out.println("Hockey");
                            break;
                            //case 3
                            case 3: System.out.println("VolleyBall");
                            break;
                            //case 4  
                            case 4: System.out.println("Soccer");
                            break;

                            case 5: System.out.println("Golf");
                            break;//breaks loop if statement is not true
  
        
            //The default method to print nothing when none of the cases is true        
            default:
            System.out.println();   


                                      }//end swicth
                                            }//end main
                             }//terminate class main