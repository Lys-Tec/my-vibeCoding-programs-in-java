import java.util.Scanner;

public class AddOrSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n  PERFORM ADDITION || SUBTRACTION");
        System.out.println("\tCHOOSE AN OPERATION\n");
        System.out.println("\t  1. Addition(+)");
        System.out.println("\t 2. Subtraction(-)");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                performOperation(scanner, "+");
                break;
            case "2":
                performOperation(scanner, "-");
                break;
            default:
                System.out.println("Something went wrong");
        }
        scanner.close();
    }

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static void performOperation(Scanner scanner, String operation) {
        double num1 = getNumberInput(scanner, "\tEnter first number: ");
        double num2 = getNumberInput(scanner, "\tEnter second number: ");
        double result = 0.0;
        switch (operation) {
            case "+":
                result = add(num1, num2);
                break;
            case "-":
                result = subtract(num1, num2);
                break;
        }
        System.out.println("\tResult: " + result);
    }

    public static double getNumberInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}