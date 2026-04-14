import java.util.Scanner;

public class TruthTables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean repeat = true;

        while (repeat) {
            System.out.println("___________________________________");
            System.out.println("Logical operations on truth tables:");
            System.out.println("___________________________________");
            System.out.println("1. OR");
            System.out.println("2. AND");
            System.out.println("3. NOT");
            System.out.println("4. EXCLUSIVE-OR");
            System.out.println("5. EXIT");

            System.out.print("Enter Operator choice (1-5):\n");

            String choice = scanner.nextLine();// Consume newline left-over

            if(choice.equals("5")){
                  System.out.println("COME BACK AGAIN");
                  break;
            }

            switch (choice) {
                case "1":
                    printORTruthTable();
                    break;
                case "2":
                    printANDTruthTable();
                    break;
                case "3":
                    printNOTTruthTable();
                    break;
                case "4":
                    printXORTruthTable();
                    break;
                case "5":
                    choice = "5";
                System.out.println();
                break;

                default:
                    System.out.println("Invalid choice. Please enter 1-5.");
            }

            System.out.print("Do you wish to choose another operator? (yes/no):\n ");
            String response = scanner.nextLine();
            repeat = response.equalsIgnoreCase("yes");
        }
    }

    private static void printORTruthTable() {
        System.out.println("\nOR operator (||)");
        System.out.println("A\tB\tA || B");
        System.out.println("false\tfalse\t" + (false || false));
        System.out.println("false\ttrue\t" + (false || true));
        System.out.println("true\tfalse\t" + (true || false));
        System.out.println("true\ttrue\t" + (true || true));
    }

    private static void printANDTruthTable() {
        System.out.println("\nAND operator (&&)");
        System.out.println("A\tB\tA && B");
        System.out.println("false\tfalse\t" + (false && false));
        System.out.println("false\ttrue\t" + (false && true));
        System.out.println("true\tfalse\t" + (true && false));
        System.out.println("true\ttrue\t" + (true && true));
    }

    private static void printNOTTruthTable() {
        System.out.println("\nNOT operator (!)");
        System.out.println("A\t!A");
        System.out.println("true\t" + (!true));
        System.out.println("false\t" + (!false));
    }

    private static void printXORTruthTable() {
        System.out.println("\nEXCLUSIVE-OR operator (^)");
        System.out.println("A\tB\tA ^ B");
        System.out.println("false\tfalse\t" + (false ^ false));
        System.out.println("false\ttrue\t" + (false ^ true));
        System.out.println("true\tfalse\t" + (true ^ false));
        System.out.println("true\ttrue\t" + (true ^ true));
    }
}
