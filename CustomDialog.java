import javax.swing.*;
import java.awt.*;

public class CustomDialog extends JDialog {
    public CustomDialog() {
        setLayout(new FlowLayout());
        add(new JLabel("HELLO HOUGHTON!"));
        setSize(300, 100);
     
        setModal(true);
    }

    public static void main(String[] args) {
        CustomDialog dialog = new CustomDialog();
        dialog.setVisible(true);
    }
}
