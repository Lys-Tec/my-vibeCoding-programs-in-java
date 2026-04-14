import javax.swing.JOptionPane;

public class DialogueBox{
    public static void main (String [] args){
        
        String name = JOptionPane.showInputDialog(
            null, 
            "Welcome! Please enter your name:", 
            "Name", 
            JOptionPane.QUESTION_MESSAGE
        );
        int age = Integer.parseInt(JOptionPane.showInputDialog(name + " How old Are you?"));
     
           JOptionPane.showMessageDialog(null, "Alright Buddy You Are " + age + " Years Old!");
        
        
        double height = Double.parseDouble(JOptionPane.showInputDialog("Hey! " + name + " How tall are you in centimeters?"));
           JOptionPane.showMessageDialog(null, "Intresting! I Didn't Know You Are " + height + "cm Tall!");
    }

}

