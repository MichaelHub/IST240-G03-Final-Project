
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel  extends JPanel implements ActionListener, KeyListener{
    JButton back, menu, timeDisplay, scoreDisplay, menuClose, menuMain, menuOptions;
    JButton option1, option2, option3;
    //Character, enemy, and background displays
    JLabel character, clown, background;
    Icon clown_icon;
    //Panels
    myJFrame jf;
    JPanel gameMenu;
    //Timer
    Timer time, clown_time;
    int timeNumber;
    //Coordinates
    int x,y;//Cooridinates for character
    int bx, by, bw, bh; //Bounds for background
    int clownx, clowny;//coordinates for clown
    //Key tracking
    Character lastkey = null;//Remember last key to clear input
    int[] keyLog; //Tracks currently pressed keys
    //Score
    int score_count;
    //Settings
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
       
        //Set keys as not pressed
        keyLog = new int[4];
        keyLog[0] = 0;
        keyLog[1] = 0;
        keyLog[2] = 0;
        keyLog[3] = 0;
        
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
        //gameScreen.add(character);
        
                
        
        clown = new JLabel();
        clownx = 100;
        clowny = 100;
        clown.setBounds(x,y,40,60);
        clown_icon = new ImageIcon("images/clown_left.gif");
        clown.setIcon(clown_icon);
        
        clown_time = new Timer(300, this);
       
        
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
        bx = -150;
        by = -150;
        bw = 1400;
        bh = 1000;
        background.setBounds(bx,by, bw, bh);
        background.add(clown);
        background.add(character);
    }
      
    public void getCharacterLocation() {
        double cx = character.getLocationOnScreen().getX();
        double cy = character.getLocationOnScreen().getY();
        double cw = character.getBounds().getWidth();
        double ch = character.getBounds().getHeight();
        System.out.println("Character X: " + cx);
        System.out.println("Character Y: " + cy);
        System.out.println("Background X: " + bx);
        System.out.println("Background Y: " + by);
        System.out.println("--------------");
        System.out.println(intersect(character, clown));
    }
    
    public boolean intersect(JLabel one, JLabel two){
       Rectangle rectB = two.getBounds();
       Rectangle result = SwingUtilities.computeIntersection(one.getX(), one.getY(), one.getWidth(), one.getHeight(), rectB);
       return (result.getWidth() > 0 && result.getHeight() > 0);
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
        /*
        if (obj == clown_time){
            int randx = (int) (Math.floor(Math.random() * 3));
            int randy = (int) (Math.floor(Math.random() * 3));
                
            switch (randx) {
                case 0: clownx = clownx + 10;; break;
                case 1: clownx = clownx - 10; break;
                case 2: clownx = clownx; break;
            }
                
            switch (randy) {
                case 0: clowny = clowny + 10;; break;
                case 1: clowny = clowny - 10; break;
                case 2: clowny = clowny; break;
            }
            
            clown.setBounds(new Rectangle(clownx,clowny,40,60));    
        }
        */
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        int k = ke.getKeyCode();
        
        Icon catLeft = new ImageIcon("images/cat_left.gif");
        Icon catRight = new ImageIcon("images/cat_right.gif");
        
        if (k != 0) {
            boolean state = intersect(character, clown);
            if (state == true) {
                clownx = (int) (Math.random()*(650)+20);
                clowny = (int) (Math.random()*(400));
                clown.setBounds(new Rectangle(clownx,clowny,40,60));                
            }
        }
        //Movement right (and top-right and down-right)
        if(k == ke.VK_RIGHT){
            keyLog[0] = 1;
            getCharacterLocation();
            character.setIcon(catRight);
            if (character.getLocationOnScreen().getX() > 550 && bx > -700) {
                bx = bx - 10;
                x = x+10;
                background.setBounds(bx, by, bw, bh);
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[2] == 1) {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                y = y-10;
                if (y<30){
                    y=y+10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[3] == 1) {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                y = y+10;
                if (y<30){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
            } else {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                character.setBounds(x, y, 60, 60);
            }
        }
        //Movement left (and down-left and down-right)
        if(k == ke.VK_LEFT){
            keyLog[1] = 1;
            getCharacterLocation();
            character.setIcon(catLeft);
            if (character.getLocationOnScreen().getX() < 10.0 && bx < 0) {
                bx = bx + 10;
                background.setBounds(bx, by, bw, bh);
            } else if (keyLog[2] == 1) {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                y = y-10;
                if (y<30){
                    y=y+10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[3] == 1) {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                y = y+10;
                if (y<30){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
            } else {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                character.setBounds(x, y, 60, 60);
            }
        }
        if(k == ke.VK_UP){
            keyLog[2] = 1;
            getCharacterLocation();
            if (character.getLocationOnScreen().getY() < 40.0 && by < -140) {
                by = by + 10;
                background.setBounds(bx, by, bw, bh);
            } else if (keyLog[0] == 0 && keyLog[1] == 0) {
                y = y-10;
                if (y<30){
                    y=y+10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[0] == 1) {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                y = y-10;
                if (y<30){
                    y=y+10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[1] == 1) {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                y = y-10;
                if (y<30){
                    y=y+10;
                }
                character.setBounds(x, y, 60, 60);
            }
        }
        if(k == ke.VK_DOWN){
            keyLog[3] = 1;
            getCharacterLocation();
            if (character.getLocationOnScreen().getY() < 388 && y > -520) {
               //If character is within the screen and not hitting bounds
                y = y + 10;
                character.setBounds(x, y, 60, 60);
            } else if (character.getLocationOnScreen().getY() == 398.0 && y == -520) {
              
            } else if (keyLog[0] == 0 && keyLog[1] == 0 && character.getLocationOnScreen().getY() > 368){
                //If character is hitting 
                if (by >= -510) {
                    by = by - 10;
                    background.setBounds(bx, by, bw, bh);                    
                }
                y = y + 10;
                character.setBounds(x, y, 60, 60);
            } /* else if (keyLog[0] == 1 && character.getLocationOnScreen().getY() > 388 && by >= -510) {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                y = y+10;
                if (y>400){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
                if (by >= -510) {
                    by = by - 10;
                    background.setBounds(bx, by, bw, bh);                    
                }
            } else if (keyLog[1] == 1 && character.getLocationOnScreen().getY() > 388 && by >= -510) {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                y = y+10;
                if (y>400){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
                if (by >= -510) {
                    by = by - 10;
                    background.setBounds(bx, by, bw, bh);                    
                }
            }*/
        }
        if (k == ke.VK_P) {
            clown.setBounds(0,600,40,60);
        }
        if (k == ke.VK_O) {
            clown.setBounds(600,0,40,60);
        }
        if (k == ke.VK_I) {
            clown.setBounds(600,600,40,60);
        }
        if (k == ke.VK_U) {
            clown.setBounds(0,0,40,60);
        }
        if (k == ke.VK_Y) {
            clown.setBounds(800,0,40,60);
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
       int k = ke.getKeyCode();
        
        if(k == ke.VK_RIGHT){
            keyLog[0] = 0;
        }
        if(k == ke.VK_LEFT){
            keyLog[1] = 0;
        }
        if(k == ke.VK_UP){
            keyLog[2] = 0;
        }
        if(k == ke.VK_DOWN){
            keyLog[3] = 0;
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
