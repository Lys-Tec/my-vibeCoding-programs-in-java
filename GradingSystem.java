 import java.util.Scanner;
import java.util.InputMismatchException;

public class GradingSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] count = new int[8];
        int studentnum = 0;

        // Ask for number of students
        while (true) {
            try {
                System.out.println("Enter Number of Students (Or enter -1 to Exit)");
                studentnum = (int) scanner.nextDouble();

                if (studentnum == -1) {
                    System.out.println("You Have Gone Offline!");
                    scanner.close();
                    return;
                }

                if (studentnum < 0) {
                    System.out.println("Enter positive numbers only! Those greater than 0.");
                    continue;
                }

                break;

            } catch (InputMismatchException e) {
                System.out.println("Something Went Wrong! Correct input needed.");
                scanner.next(); 
            }
        }

        // Enter each student's grade
        for (int i = 0; i < studentnum; i++) {
            while (true) {
           try {
                    System.out.println("Enter grade for Student " + (i + 1));
                    double grade = scanner.nextDouble();

                    if (grade >= 0 && grade <= 100) {

                        // --- SIMPLIFIED GRADE ALLOCATION (same technique) ---
                        if (grade >= 86)      count[0]++; // A+
                        else if (grade >= 75) count[1]++; // A
                        else if (grade >= 65) count[2]++; // B+
                        else if (grade >= 60) count[3]++; // B
                        else if (grade >= 55) count[4]++; // C+
                        else if (grade >= 50) count[5]++; // C
                        else if (grade >= 45) count[6]++; // D+
                        else                  count[7]++; // D
                        // ---------------------------------------------------

                        break;

                    } else {
                        System.out.println("Invalid Grade. Please enter grade from 0 to 100");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! You can Only Enter Numbers, (e.g 44).");
                    scanner.next();
                }
            }
        }

        // Print grade summary
        System.out.println("== Grade Summary ==");

        System.out.println("A+\t=     " + count[0]);
        System.out.println("A\t=      " + count[1]);
        System.out.println("B+\t=     " + count[2]);
        System.out.println("B\t=      " + count[3]);
        System.out.println("C+\t=     " + count[4]);
        System.out.println("C\t=      " + count[5]);
        System.out.println("D+\t=     " + count[6]);
        System.out.println("D\t=      " + count[7]);

        scanner.close();
    }
}
