import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SimpleCalculator {

    private static ArrayList<String> history = new ArrayList<>();
    private static double memory = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ----------------------------");
        System.out.println(" |++++ SIMPLE CALCULATOR ++++|");
        System.out.println(" ----------------------------");

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Add (+)");
            System.out.println("2. Subtract (-)");
            System.out.println("3. Multiply (*)");
            System.out.println("4. Divide (/)");
            System.out.println("5. Modulus (%)");
            System.out.println("6. Show History");
            System.out.println("7. Clear History");
            System.out.println("8. Memory Functions");
            System.out.println("9. Exit");
            System.out.print("Enter choice (1-9): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": performOperation(scanner, "+"); break;
                case "2": performOperation(scanner, "-"); break;
                case "3": performOperation(scanner, "*"); break;
                case "4": performOperation(scanner, "/"); break;
                case "5": performOperation(scanner, "%"); break;
                case "6": displayHistory(); break;
                case "7": clearHistory(); break;
                case "8": memoryFunctions(scanner); break;
                case "9":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ===== BASIC OPERATIONS =====
    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a / b;
    }

    public static double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a % b;
    }

    // ===== INPUT VALIDATION =====
    public static double getNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number! Try again.");
            }
        }
    }

    // ===== HISTORY =====
    public static void updateHistory(double a, double b, String op, double result) {
        history.add(String.format("%.2f %s %.2f = %.2f", a, op, b, result));
    }

    public static void displayHistory() {
        if (history.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("\n=== Calculation History ===");
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }
    }

    public static void clearHistory() {
        history.clear();
        System.out.println("History cleared.");
    }

    // ===== MEMORY FUNCTIONS =====
    public static void memoryFunctions(Scanner scanner) {
        System.out.println("\nMemory Functions:");
        System.out.println("1. M+ (Add)");
        System.out.println("2. M- (Subtract)");
        System.out.println("3. MR (Recall)");
        System.out.println("4. MC (Clear)");
        System.out.print("Choose: ");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                memory += getNumber(scanner, "Add value: ");
                System.out.println("Memory = " + memory);
                break;
            case "2":
                memory -= getNumber(scanner, "Subtract value: ");
                System.out.println("Memory = " + memory);
                break;
            case "3":
                System.out.println("Memory value: " + memory);
                break;
            case "4":
                memory = 0;
                System.out.println("Memory cleared.");
                break;
            default:
                System.out.println("Invalid memory option!");
        }
    }

    // ===== PERFORM A CALCULATION =====
    public static void performOperation(Scanner scanner, String op) {
        double a = getNumber(scanner, "Enter first number: ");
        double b = getNumber(scanner, "Enter second number: ");
        double result = 0;

        switch (op) {
            case "+": result = add(a, b); break;
            case "-": result = subtract(a, b); break;
            case "*": result = multiply(a, b); break;
            case "/": result = divide(a, b); break;
            case "%": result = modulus(a, b); break;
        }

        if (!Double.isNaN(result)) {
            System.out.println("Result: " + result);
            updateHistory(a, b, op, result);
        }
    }
}
