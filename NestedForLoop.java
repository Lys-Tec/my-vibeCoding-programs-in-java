import java.util.Scanner;

    public class NestedForLoop{
        public static void main(String [] args){
            Scanner input = new Scanner(System.in);

            int rows;
            int columns;
            String symbol = "";

            System.out.print("Enter the Amount of rows: ");
            rows = input.nextInt();

            System.out.print("Enter the Amount of columns: ");
            columns = input.nextInt();

            System.out.print("Enter the Symbol to utilize: ");
            symbol = input.next();
            

                            for(int i = 1; i <= rows; i++){

                                System.out.println();
                           
                                    for(int j = 1; j <= columns; j++){
                                        System.out.print(symbol);
                                    }
                                     
                            }

                        }
                    }