public class MultiplicationTable {
    public static void main(String[] args) {
        int size = 20; // Change this to create a table of different size

        // Print header
        System.out.print("      |");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%5d", i);
        }
        System.out.println();
        for (int i = 0; i <= size * 5 + 5; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Print table
        for (int i = 1; i <= size; i++) {
            System.out.printf("%5d |", i);

            for (int j = 1; j <= size; j++) {
                System.out.printf("%5d", i * j);
                
            }
            
            System.out.println();
            
        }
        for (int i = 0; i <= size * 5 + 5; i++) {
    System.out.print("-");
}
    System.out.println();

    }
}