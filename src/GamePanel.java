
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel  extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
        
        this.setLayout( new BorderLayout() );
        JLabel info = new JLabel ("Start of game");
        this.add(info, BorderLayout.NORTH);
        back = new JButton ("Back");
        this.add(back, BorderLayout.CENTER);
        this.back.addActionListener(this);
        
        this.setBackground(Color.GRAY);
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.G1);
                jf.lpane.add(jf.L1);
                jf.lpane.add(jf.L2);
            }
    }
}
