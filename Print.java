
public class Print {
    public static void main(String[] args) {
        String[] options = {
            "Add (+)",
            "Subtract (-)",
            "Multiply (*)",
            "Divide (/)",
            "Modulus (%)",
            "Show History",
            "Clear History",
            "Memory Functions",
            "Exit"
        };

        System.out.println("\nYou may choose an operation:");
        System.out.println("---------------------------------------------------------------------------------");
        for (int i = 0; i < options.length; i += 3) {
            if (i + 2 < options.length) {
                System.out.printf("%-25s | %-25s | %-25s\n", (i + 1) + ". " + options[i], (i + 2) + ". " + options[i + 1], (i + 3) + ". " + options[i + 2]);
            } else if (i + 1 < options.length) {
                System.out.printf("%-25s | %-25s\n", (i + 1) + ". " + options[i], (i + 2) + ". " + options[i + 1]);
            } else {
                System.out.printf("%-25s\n", (i + 1) + ". " + options[i]);
            }
        }
        System.out.println("---------------------------------------------------------------------------------");
    }
}
