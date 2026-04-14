import java.util.Stack;

public class InfixConverter {

    // Utility: check precedence
    private static int precedence(char ch) {
        switch (ch) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
        }
        return -1;
    }

    // Convert infix to postfix
    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // Operand → add directly
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
            // Opening bracket → push
            else if (c == '(') {
                stack.push(c);
            }
            // Closing bracket → pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // remove '('
            }
            // Operator
            else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Convert infix to prefix
    public static String infixToPrefix(String exp) {
        // Step 1: reverse the infix expression
        StringBuilder input = new StringBuilder(exp).reverse();
        StringBuilder modified = new StringBuilder();

        // Step 2: swap '(' with ')' while reversing
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') modified.append(')');
            else if (c == ')') modified.append('(');
            else modified.append(c);
        }

        // Step 3: get postfix of modified expression
        String postfix = infixToPostfix(modified.toString());

        // Step 4: reverse postfix → prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    // Demo
    public static void main(String[] args) {
        String infix = "(A-B/C)*(A/K-L)";
        System.out.println("Infix   : " + infix);
        System.out.println("Postfix : " + infixToPostfix(infix));
        System.out.println("Prefix  : " + infixToPrefix(infix));
    }
}
