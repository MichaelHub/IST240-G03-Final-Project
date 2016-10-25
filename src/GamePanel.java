
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel  extends JPanel implements ActionListener{
    JButton back;
    JButton menu;
    JButton time;
    JButton score;
    JButton option1, option2, option3, option4;
    myJFrame jf;
    
    String chosen_character, chosen_time, chosen_difficulty;
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
       
        
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        this.setSize(700,500);
        
        JLabel info = new JLabel ("Start of game");
        c.weightx = .5;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(info, c);
        
        time = new JButton ("Time: 0");
        c.weightx = .5;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets (380,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(time, c);
        time.setEnabled(false);
        
        score = new JButton ("Score: 0");
        c.weightx = .5;
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets (380,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(score, c);
        score.setEnabled(false);
        
        menu = new JButton ("Menu");
        c.weightx = .5;
        c.gridx = 2;
        c.gridy = 5;
        c.insets = new Insets (400,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(menu, c);
        back = new JButton ("Back");
        c.weightx = .5;
        c.gridx = 3;
        c.gridy = 5;
        c.insets = new Insets (380,0,0,0);
        c.anchor = GridBagConstraints.PAGE_END;
        this.add(back, c);
        this.back.addActionListener(this);
        
        this.setBackground(Color.GRAY);
        option1 = new JButton(chosen_time);
        add(option1);        
        option2 = new JButton(chosen_time);
        add(option2);      
        option3 = new JButton(chosen_time);
        add(option3);
        option4 = new JButton(chosen_time);
        add(option4);

        
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
