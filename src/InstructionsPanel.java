
import java.awt.*;
import javax.swing.*;

public class InstructionsPanel  extends JPanel {
    InstructionsPanel () {
        this.setLayout( new BorderLayout() );
        JLabel instructions = new JLabel ("<html>Instructions<br /><br /><ul><li>Instructions</li><li>will</li><li>go</li><li>here</li></ul></html>");
        add(instructions, BorderLayout.CENTER);
        this.setBackground(Color.GRAY);
    }
}
