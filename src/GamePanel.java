
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel  extends JPanel implements ActionListener{
    JButton back, menu, time, score;
    JButton option1, option2, option3;
    JLabel character;
    myJFrame jf;
    int x = 1;
    JPanel gameMenu;
    
    String chosen_character, chosen_time, chosen_difficulty;
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
       
        this.setLayout(new BorderLayout());
        
        //Screen for game elements
        JPanel gameScreen = new JPanel();
        gameScreen.setLayout(null);
        
        //Menu within gameScreen
        gameMenu = new JPanel();
        gameMenu.setBounds(250,80,200,350);
        gameMenu.setBackground(Color.RED);
        gameScreen.add(gameMenu);
        
        option1 = new JButton("1");
        gameScreen.add(option1);       
        option1.setBounds(0, 100, 80, 50);
        
        option1.addActionListener(this);
        option2 = new JButton("2");
        option2.setBounds(100, 100, 80, 50);
        gameScreen.add(option2);      
        option3 = new JButton("3");
        option3.setBounds(200, 100, 80, 50);
        gameScreen.add(option3);
        character = new JLabel();
        character.setBounds(300, 100, 60, 60);
        gameScreen.add(character);
        
        
        
        //Bottom bar for timer and menu
        JPanel bottomBar = new JPanel();
        
        bottomBar.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        time = new JButton ("Time: 0");
        c.weightx = .5;
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(time, c);
        time.setEnabled(false);
        
        score = new JButton ("Score: 0");
        c.weightx = .5;
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(score, c);
        score.setEnabled(false);
        
        menu = new JButton ("Menu");
        c.weightx = .5;
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(menu, c);
        back = new JButton ("Back");
        c.weightx = .5;
        c.gridx = 3;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(back, c);
        back.addActionListener(this);
        
        this.add(bottomBar, BorderLayout.SOUTH);
        this.add(gameScreen, BorderLayout.CENTER);
                
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == back)
            {
                jf.lpane.remove(jf.G1);
                jf.lpane.add(jf.L1);
                jf.lpane.add(jf.L2);
            }
        if(obj == option1) {
            if (x == 1) {
                gameMenu.setVisible(false);
                x++;
            } else {
                gameMenu.setVisible(true);
                x = 1;
            }
        }
    }
}
