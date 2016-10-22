
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AboutPanel  extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;
    
    AboutPanel (myJFrame jf) {
        this.jf = jf;
        
        this.setLayout( new BorderLayout() );
        JLabel about = new JLabel ("<html>About<br /><br /><ul><li>Maksim Dubyk</li><li>Kyle Mullen</li><li>Michael Berhane</li>");
        
        this.add(about, BorderLayout.NORTH);
        back = new JButton ("Back");
        this.add(back, BorderLayout.CENTER);
        this.back.addActionListener(this);
        
        this.setBackground(Color.GRAY);
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.A1);
                jf.lpane.add(jf.L1);
                jf.lpane.add(jf.L2);
            }
    }
}
