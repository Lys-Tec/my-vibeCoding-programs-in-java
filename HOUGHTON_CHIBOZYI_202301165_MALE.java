          
 /**----------------------------------------------------------------------------------------------------------------------- */         
          /**
 * @author NAME:             HOUGHTON CHIBOZYI
 *         STUDENT NUMBER:   202301165
 * 
 * @
 *         COURSE CODE:      ICT202
 *         COURSE NAME:      DATA STRUCTURES AND ALGORITHMS
 *         LECTURER:         KALUBA ZILANI (MR)
 *
 *         PROGRAM DESCRIPTION:
 *         --------------------
 *  # This program performs basic Matrix Operations using Arrays as the main data structure. 
 *  # The user is provided with a menu to select different operations such as:
 *      1. Matrix Addition
 *      2. Matrix Subtraction
 *      3. Matrix Multiplication
 *      4. Display Matrix
 *      5. Exit Program
 *
 *  >The program also includes input validation
 *             
 *   
 *//**_________________________________________________________________________________________________________________________*/
                
                //importing required classses
             import java.util.Scanner;
            import java.util.InputMismatchException;

//header
public class HOUGHTON_CHIBOZYI_202301165_MALE{

    // Scanner object used for user input
    static Scanner enter = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

         // Variable to store userchoice

        // Infinite loop to keep displaying the menu until user exits
        while (true) {

            // Display menu options
            System.out.println("\nMATRIX OPERATIONS ");

            System.out.println("1. Addition");

            System.out.println("2. Subtraction");

         System.out.println("3. Multiplication");

             System.out.println("4. Display Matrix");
                System.out.println("5. Exit");

            System.out.println("Select Operation: ");
            choice = enter.nextInt();

            try {
                // Read user choice
                choice = enter.nextInt();
            } 
            catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Error: Please enter an integerr between 1 to 5.");
                
                enter.next(); // clear invalid input
                continue;
               }

                         // Perform operation based on user choice
                           switch (choice) {

                       case 1:
                    addition();
                    break;

                    case 2:
                    subtraction();
                    break;

                case 3:
                    multiplication();
                    break;

                       case 4:
                    displayOnly();
                    break;

                 case 5:
                    // Exit program
                    System.out.println("Exiting program ...");
                    System.out.println("");
                    System.out.println("\tGOODBYE!!!!");
                    System.out.println("");
                    return;

                default:
                    // Invalid menu choice
                    System.out.println("Invalid choice. Kidnly Select betetween Options 1 - 5__NOT MORE!!!");
                                                           }
                                         }
                               }

    // METHOD TO GET VALID MATRIX SIZE
    // Ensures that the user enters a number between 2 and 5
    public static int getSize(String message) {

        int size;

        while (true) {
            try {
                System.out.print(message);
                size = enter.nextInt();

                // Validate size range
                if (size >= 2 || size <= 5) {
                    return size;
                } 
                else {
                    System.out.println("*WRONG INPUT*");
                System.out.println("Size must be From 2x2 up to 5x5.");
                }

                } 
                catch (InputMismatchException e) {
                // Handle invalid numeric input
                System.out.println("Invalid input...You Are Required To Enter Numbers Only!!!.");

                enter.next();
                  }
                  }
                   }

    // METHOD TO INPUT MATRIX VALUES
    // Creates and fills a matrix with user input values
                  public static int[][] inputMatrix(int rows, int columns, String name) {

        int[][] matrix = new int[rows][columns];

        System.out.println("\nEnter values for Matrix " + name);

        // Loop through rows
        for (int i = 0; i < rows; i++) {
            // Loop through columns
                        for (int j = 0; j < columns; j++) {

                        while (true) {
                        try {
                        System.out.print("Element [" + i + "][" + j + "]: ");
                        matrix[i][j] = enter.nextInt();
                        break;
                    } 
                    catch (InputMismatchException e) {
                        // Handle invalid integer input
                        
                        
                           System.out.println("Invalid input. Enter an integer.");
                        enter.next();
                    }
                        }
              }
                  }

        return matrix;
       }

    // METHOD TO DISPLAY A MATRIX
    // Prints matrix elements in row and column format
    public static void displayMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            System.out.print("| ");

            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
     }

            System.out.println("|");
        }
        }

    // MATRIX ADDITION METHOD
    // Adds two matrices of the same dimensions
    public static void addition() {

        int rows = getSize("Enter rows for matrices: ");
        int cols = getSize("Enter columns for matrices: ");

        // Input matrices
        int[][] A = inputMatrix(rows, cols, "A");
        int[][] B = inputMatrix(rows, cols, "B");

        int[][] result = new int[rows][cols];

        // Perform addition
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[i][j] = A[i][j] + B[i][j];
            }
        }

        // Display result
        System.out.println("\nResult (A + B):");
        displayMatrix(result);
    }

    // MATRIX SUBTRACTION METHOD
    // Subtracts matrix B from matrix A
    public static void subtraction() {

        int rows = getSize("Enter rows for matrices: ");
        int cols = getSize("Enter columns for matrices: ");

        int[][] A = inputMatrix(rows, cols, "A");
        int[][] B = inputMatrix(rows, cols, "B");

        int[][] result = new int[rows][cols];

        // Perform subtraction
        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                result[i][j] = A[i][j] - B[i][j];
            }
        }

        System.out.println("\nResult (A - B):");
        displayMatrix(result);
    }

    // MATRIX MULTIPLICATION METHOD
    // Multiplies matrix A and matrix B using the matrix multiplication rule
    public static void multiplication() {

        int rowsA = getSize("Enter rows for Matrix A: ");
        int colsA = getSize("Enter columns for Matrix A: ");

        int rowsB = getSize("Enter rows for Matrix B: ");
        int colsB = getSize("Enter columns for Matrix B: ");

        // Check matrix multiplication rule
        // Columns of A must equal rows of B
        if (colsA != rowsB) {
            System.out.println("Error: Columns of Matrix A must equal rows of Matrix B.");
            return;
        }

        int[][] A = inputMatrix(rowsA, colsA, "A");
        int[][] B = inputMatrix(rowsB, colsB, "B");

        int[][] result = new int[rowsA][colsB];

        // Perform matrix multiplication
        for (int i = 0; i < rowsA; i++) {

            for (int j = 0; j < colsB; j++) {

                for (int k = 0; k < colsA; k++) {

                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        System.out.println("\nResult (A × B):");
        displayMatrix(result);
    }

    // DISPLAY MATRIX OPTION
    // Allows the user to input and display a matrix only
    public static void displayOnly() {

        int rows = getSize("Enter rows: ");
        int cols = getSize("Enter columns: ");

        int[][] matrix = inputMatrix(rows, cols, "Matrix");

        System.out.println("\nMatrix:");
        displayMatrix(matrix);
    }//end class
}//end main