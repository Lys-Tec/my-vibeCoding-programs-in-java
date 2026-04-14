import java.util.Scanner;

public class StudentAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Full Name:");
        String fullName = scanner.nextLine();

                    System.out.println("Enter your First Score:" );
                    double score1 = scanner.nextDouble();

                    System.out.println("Enter your Second Score:");
                    double score2 = scanner.nextDouble();

                    
        System.out.println("Enter Your Third Score:");
        double score3 = scanner.nextDouble();

        double average = (score1 + score2 + score3) / 3;


    System.out.println("Student Name: " + fullName);

    System.out.printf("Average Score: %.2f\n", average);

        if (average >= 50) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }
}