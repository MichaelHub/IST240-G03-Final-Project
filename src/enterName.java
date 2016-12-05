
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class enterName extends JPanel implements ActionListener{
    JButton next;
    JLabel message,player_score, title;
    JTextField player_name;
    myJFrame jf;
    
    enterName(myJFrame jf){
        
        this.jf = jf;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        title = new JLabel("GAME OVER!");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(40.0f));
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        this.add(title,c);
        
        message = new JLabel("Enter information to maintain high score records: ");
        message.setForeground(Color.WHITE);
        message.setFont(title.getFont().deriveFont(25.0f));
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        this.add(message,c);
        
        player_score = new JLabel("Your Score: ");
        player_score.setForeground(Color.WHITE);
        player_score.setFont(title.getFont().deriveFont(25.0f));
        c.gridx = 1;
        c.gridy = 2;
        this.add(player_score,c);
        
        player_name = new JTextField("Enter Name Here");
        c.gridx = 1;
        c.gridy = 3;
        this.add(player_name,c);
        

        next = new JButton ("Continue");
        c.gridx = 1;
        c.gridy = 4;
        this.add(next,c);    
        this.next.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.next)
            {
                jf.lpane.remove(jf.EN);
                jf.lpane.add(jf.SB, new Integer(1),0);
            }
    }
}
