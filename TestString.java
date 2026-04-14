import java.util.ArrayDeque;
import java.util.Deque;
    public class TestString{
        public static void main (String[] args){
            Deque<String> stack = new ArrayDeque<String>(); 
            stack.push("ON");
            stack.push("HT");
            stack.push("UG");
            stack.push("HO");
        System.out.println(stack);
        System.out.println("stack.pop(); " + stack.peek());   
        System.out.println("stack.pop(); " + stack.pop());
        System.out.println(stack);
        System.out.println("stack.push(\"CHIBOZYI\");");
        stack.push("CHIBOZYI");
        System.out.println(stack);
         
                   }
    }