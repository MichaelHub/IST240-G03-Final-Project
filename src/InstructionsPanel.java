
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InstructionsPanel  extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;
    
    InstructionsPanel (myJFrame jf) {
        this.jf = jf;
        
        this.setLayout( new BorderLayout() );
        JLabel instructions = new JLabel ("<html>Instructions<br /><br /><ul><li>Instructions</li><li>will</li><li>go</li><li>here</li></ul></html>");
        
        this.add(instructions, BorderLayout.NORTH);
        back = new JButton ("Back");
        this.add(back, BorderLayout.CENTER);
        this.back.addActionListener(this);
        
        //this.setBackground(Color.GRAY);
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.I1);
                jf.lpane.add(jf.L1);
                //jf.lpane.add(jf.L2);
            }
    }
}
