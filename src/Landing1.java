import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Landing1 extends JPanel implements ActionListener{
    
    JButton start,instructions,about,scoreboard;
    JLabel title;
    Landing2 L2;
    myJFrame jf;

    
    Landing1(Landing2 L2, myJFrame jf){
        super();
        this.L2 = L2;
        this.jf = jf;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        start = new JButton();
        start.setText("Start Game");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(3,0,65,0);
        this.add(start, c);
        this.start.addActionListener(this);
        
        instructions = new JButton();
        instructions.setText("Instructions");
        c.gridx = 1;
        c.gridy = 4;
        this.add(instructions, c);
        this.instructions.addActionListener(this);
        
        about = new JButton();
        about.setText("About");
        c.gridx = 2;
        c.gridy = 4;
        this.add(about, c);
        this.about.addActionListener(this);
        
        scoreboard = new JButton();
        scoreboard.setText("Scoreboard");
        c.gridx = 3;
        c.gridy = 4;
        this.add(scoreboard, c);
        this.scoreboard.addActionListener(this);
        
        title = new JLabel();
        title.setText("Clown Catcher");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(40.0f));
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 0;
        this.add(title,c);
        
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.start)
            {
                jf.lpane.remove(jf.L1);
                jf.lpane.add(jf.OP, new Integer(1), 0);
            }
        else if (obj == this.instructions)
            {
                jf.lpane.remove(jf.L1);
                jf.lpane.add(jf.I1, new Integer(1),0);
            }
        else if (obj == this.about)
            {
                jf.lpane.remove(jf.L1);
                jf.lpane.add(jf.A1, new Integer(1),0);
            }
        else if (obj == this.scoreboard)
            {
                jf.lpane.remove(jf.L1);
                jf.lpane.add(jf.SB, new Integer(1),0);
                jf.SB.loadScores();
            }
        
    }
}