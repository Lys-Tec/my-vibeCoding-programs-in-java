/**
 * Towers of Hanoi using Stacks and Recursion
 * 
 * @author:NAME:             HOUGHTON CHIBOZYI
 *         STUDENT NUMBER:   202301165
 * 
 * @
 *         COURSE CODE:      ICT202
 *         COURSE NAME:      DATA STRUCTURES AND ALGORITHMS
 *         LECTURER:         KALUBA ZILANI (MR)
 * 
 */

import java.util.Scanner;
import java.util.Stack;

public class CHIBOZYI_HOUGHTON_202301165_ {

    // Recursive method
    public static void solve(int n, Stack<Integer> source, Stack<Integer> auxiliary, Stack<Integer> destination, char s, char a, char d) {

        if (n == 1) {
            int disk = source.pop();
            destination.push(disk);
            System.out.println("Move disk " + disk + " from " + s + " to " + d);
            return;
        }

        // Step 1
        solve(n - 1, source, destination, auxiliary, s, d, a);

        // Step 2
        int disk = source.pop();
        destination.push(disk);
        System.out.println("Move disk " + disk + " from " + s + " to " + d);

        // Step 3
        solve(n - 1, auxiliary, source, destination, a, s, d);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Stack<Integer> source = new Stack<>();
        Stack<Integer> auxiliary = new Stack<>();
        Stack<Integer> destination = new Stack<>();

        System.out.print("Enter number of disks: ");
        int n = input.nextInt();
        

        // Load disks into source stack
        for (int i = n; i >= 1; i--) {
            source.push(i);
        }

        System.out.println("\nSequence of Moves:");

        solve(n, source, auxiliary, destination, 'A', 'B', 'C');

        input.close();
    }
}