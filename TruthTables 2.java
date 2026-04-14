import java.util.Scanner;

public class TruthTables{


    public static void main(String args[]) {

        Scanner scanner = new Scanner(System.in);

        boolean restart;

        while(restart=true) {
            System.out.print("Logical operations on truth tables ");
            System.out.println("\n1. OR,\n2. AND,\n3. NOT,\n4. EXCLUSIVE-OR.");

            System.out.println("Enter Operator choose(1-4)");
            String userchoice = scanner.nextLine();
            if (userchoice.equals("1")) {
                OR();

            } else if (userchoice.equals("2")) {
                AND();
            } else if (userchoice.equals("3")) {
                NOT();
            } else if (userchoice.equals("4")) {
                Exclusive_OR();
            } else {
                System.out.println("Invalid Input");
            }

            System.out.println("DO YOU WANT TO CHOOSE ANOTHER OPERATOR");
            String userchoice2 = "";

            userchoice2 = scanner.nextLine();
            if(userchoice2.equals("no")){
                restart = false;
                break;
            }
            else if(userchoice2.equals("yes")){
                restart = true;
            }
            else{

                while(!userchoice2.equals("no") && !userchoice2.equals("yes")){
                    System.out.println("Invalid input");
                    System.out.println("DO YOU WANT TO CHOOSE ANOTHER OPERATOR");
                    userchoice2 = scanner.nextLine();
                }
            }

        }
        }

    public static void OR(){
        // OR OPERATOR
        System.out.println("\nOR operator (||)");
        System.out.println("A\t\t\tB\t\t\tA || B");

        // TRUTH TABLE FOR OR OPERATOR

        System.out.println("false\t\tfalse\t\t" + (false || false));
        System.out.println("false\t\ttrue\t\t" + (false || true));
        System.out.println("true\t\tfalse\t\t" + (true || false));
        System.out.println("true\t\ttrue\t\t" + (true || true));


    }

    public static void AND()  {
        // AND OPERATOR

        System.out.println("\nAND operator (&&)");
        System.out.println("A\t\t\t B\t\t\t A && B");

        //TRUTH TABLE FOR AND OPERATOR

        System.out.println("false\t\tfalse\t\t" + (false && false));
        System.out.println("false\t\ttrue\t\t" + (false && true));
        System.out.println("true\t\tfalse\t\t" + (true && false));
        System.out.println("true\t\ttrue\t\t" + (true && true));

    }

    public static void  NOT () {
        // NOT OPERATOR
        System.out.println("\n NOT operator (!)");
        System.out.println("A\t\t !A");

        //TRUTH TABLE FOR NOT OPERATOR

        System.out.println("true\t" + (!true));
        System.out.println("false\t" + (!false));
    }

    public static void Exclusive_OR () {
        //EXCLUSIVE-OR OPERATOR

        System.out.println("\nEXCLUSIVE-OR operator (^)");
        System.out.println("A\t\t\t B\t\t\t A ^ B");

        //TRUTH TABLE FOR EXCLUSIVE-OR OPERATOR

        System.out.println("false\t\tfalse\t\t" +  (false ^ false));
        System.out.println("false\t\t true\t\t" + (false ^ true));
        System.out.println("true\t\tfalse\t\t" + (true ^ false));
        System.out.println("true\t\ttrue\t\t" + (true ^ true));

    }
    }
