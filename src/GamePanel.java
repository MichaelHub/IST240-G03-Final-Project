
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel  extends JPanel implements ActionListener, KeyListener{
    JButton back, menu, timeDisplay, scoreDisplay, menuClose, menuMain, menuOptions;
    JButton option1, option2, option3;
    JLabel character, clown, background;
    myJFrame jf;
    JPanel gameMenu;
    Timer time, clown_time;
    int timeNumber;
    int x,y;//Cooridinates for character
    int clownx, clowny;//coordinates for clown
    Icon clown_icon;
    Character lastkey = null;//Remember last key to clear input
    int score_count;

    
    String chosen_character, chosen_time;
    int chosen_difficulty;
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
        
        this.setFocusable(true);
        this.addKeyListener(this);
        this.grabFocus();
       
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
        //set game background
        Icon img = new ImageIcon("images/map1.png");
        background = new JLabel();
        background.setIcon( img );
        
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
        x = 0;
        y = 30;
        character.setBounds(x, y, 40, 40);
        gameScreen.add(character);
        
                
        
        clown = new JLabel();
        clownx = 100;
        clowny = 100;
        clown.setBounds(x,y,60,60);
        clown_icon = new ImageIcon("images/clown_icon.png");
        clown.setIcon(clown_icon);
        gameScreen.add(clown);
        
        clown_time = new Timer(1000, this);
       
        
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
        
        score_count = 0;
        scoreDisplay = new JButton ("Score: "+score_count);
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
        
        gameScreen.add(background);
        background.setBounds(-150,-150,1400,1000);
        
        
                
    }
    
    
    
    public void intersection(JLabel characterinput, JLabel clowninput){
        
        
        if (Math.abs((characterinput.getX()-clowninput.getX())) < 5 && Math.abs((characterinput.getY()-clowninput.getY())) < 5){
            score_count++;
            scoreDisplay.setText("Score: "+score_count);
            clownx = (int) (Math.random()*(650)+20);
            clowny = (int) (Math.random()*(400));
            clown.setBounds(new Rectangle(clownx,clowny,60,60));  
        }
       
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
        if (obj == clown_time){
            clownx = (int) (Math.random()*(650)+20);
            clowny = (int) (Math.random()*(400));
            clown.setBounds(new Rectangle(clownx,clowny,60,60));
        }
        
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        int k = ke.getKeyCode();
        
        if(k == ke.VK_RIGHT){
            x = x+10;
            if (x>650){x=x-10;}
            character.setBounds(x, y, 60, 60);
            }
            intersection(character,clown);
        if(k == ke.VK_LEFT){
            x = x-10;
            if (x<0){x=x+10;}
            character.setBounds(x, y, 60, 60);}
            intersection(character,clown);
        if(k == ke.VK_UP){
            y = y-10;
            if (y<30){y=y+10;}
            character.setBounds(x, y, 60, 60);}
            intersection(character,clown);
        if(k == ke.VK_DOWN){
            y = y+10;
            if (y>400){y=y-10;}
            character.setBounds(x, y, 60, 60);}
            intersection(character,clown);
    }
    @Override
    public void keyReleased(KeyEvent ke) {
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
