
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel  extends JPanel implements ActionListener{
    JButton back, menu, timeDisplay, scoreDisplay, menuClose, menuMain, menuOptions;
    JButton option1, option2, option3;
    JLabel character;
    myJFrame jf;
    JPanel gameMenu;
    Timer time;
    int timeNumber;
    
    String chosen_character, chosen_time, chosen_difficulty;
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
       
        this.setLayout(new BorderLayout());
        
        //Screen for game elements
        JPanel gameScreen = new JPanel();
        gameScreen.setLayout(null);
        
        //Menu within gameScreen
        gameMenu = new JPanel();
        gameMenu.setBounds(250,120,200,220);
        gameMenu.setBackground(Color.YELLOW);
        Border loweredetched;
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        gameMenu.setBorder(loweredetched);
        
        gameMenu.setLayout (new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        //Menu title
        JLabel menuTitle = new JLabel("Menu");
        menuTitle.setFont(menuTitle.getFont().deriveFont(22.0f));
        gbc.weightx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0,75,0,0);
        gameMenu.add(menuTitle, gbc);
        
        //Menu close
        menuClose = new JButton("X");
        menuClose.setFont(menuTitle.getFont().deriveFont(18.0f));
        gbc.weightx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(-300,150,0,0);
        gameMenu.add(menuClose, gbc);
        menuClose.addActionListener(this);
        
        
        //Menu Options
        menuMain = new JButton ("Main Menu");
        gbc.gridy = 1;
        gbc.ipady = 20;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10,0,0,0);
        gameMenu.add(menuMain, gbc);
        menuMain.addActionListener(this);
        
        menuOptions = new JButton ("Game Options");
        gbc.gridy = 2;
        gbc.ipady = 20;
        gbc.insets = new Insets(10,0,0,0);
        gameMenu.add(menuOptions, gbc);
        menuOptions.addActionListener(this);
        
        gameScreen.add(gameMenu);
        gameMenu.setVisible(false);   
        
        //end of gameMenu
        
        option1 = new JButton("1");
        gameScreen.add(option1);       
        option1.setBounds(0, 100, 80, 50);
        
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
        
        timeDisplay = new JButton ("Time: 0");
        c.weightx = .5;
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(timeDisplay, c);
        timeDisplay.setEnabled(false);
        time = new Timer(1000, this);
        time.start();
        
        scoreDisplay = new JButton ("Score: 0");
        c.weightx = .5;
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(scoreDisplay, c);
        scoreDisplay.setEnabled(false);
        
        menu = new JButton ("Menu");
        c.weightx = .5;
        c.gridx = 2;
        c.gridy = 5;
        c.anchor = GridBagConstraints.PAGE_END;
        bottomBar.add(menu, c);
        menu.addActionListener(this);
        
        this.add(bottomBar, BorderLayout.SOUTH);
        this.add(gameScreen, BorderLayout.CENTER);
                
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == menuMain)
            {
                gameMenu.setVisible(false); 
                jf.lpane.remove(jf.G1);
                jf.lpane.add(jf.L1);
                jf.lpane.add(jf.L2);
            }
        if(obj == menu) {
            gameMenu.setVisible(true);
        }
        if (obj == menuClose) {
            gameMenu.setVisible(false);            
        }
        if (obj == menuOptions) {
            gameMenu.setVisible(false); 
            jf.lpane.remove(jf.G1);
            jf.lpane.add(jf.OP);
            jf.lpane.add(jf.L2);
        }
        if (obj == time) {
            timeNumber++;
            timeDisplay.setText("Time: " + timeNumber);
        }
        
    }
}
