public class printMenu{
    public static void main(String [] args) {
    System.out.println("\nYou may choose an operation:\n");
    System.out.println("|-----------------------|");
    String[] options = {
        "1. Add (+)",
        "2. Subtract (-)",
        "3. Multiply (*)",
        "4. Divide (/)",
        "5. Modulus (%)",
        "6. Show History",
        "7. Clear History",
        "8. Memory Functions",
        "9. Exit"
    };
    for (String option : options) {
        System.out.printf("| %-20s |\n", option);
    }
    System.out.println("|-----------------------|");
}
}