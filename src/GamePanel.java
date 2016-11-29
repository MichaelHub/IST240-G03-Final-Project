
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
    int p;
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
    int clown_move_speed;
    //Screen size
    int screenTop, screenBottom, screenLeft, screenRight, screenWidth, screenHeight;
           
    //Stores where to move and how long
    int direction[] = new int [4];
    
        
    Icon catLeft = new ImageIcon("images/cat_left.gif");
    Icon catRight = new ImageIcon("images/cat_right.gif");
    Icon hunterLeft = new ImageIcon("images/hunter_left.gif");
    Icon hunterRight = new ImageIcon("images/hunter_right.gif");
    Icon skeletonLeft = new ImageIcon("images/skeleton_left.gif");
    Icon skeletonRight = new ImageIcon("images/skeleton_right.gif");

    Icon catStillLeft = new ImageIcon("images/cat_still_left.gif");
    Icon catStillRight = new ImageIcon("images/cat_still_right.gif");
    Icon hunterStillLeft = new ImageIcon("images/hunter_still_left.gif");
    Icon hunterStillRight = new ImageIcon("images/hunter_still_right.gif");
    Icon skeletonStillLeft = new ImageIcon("images/skeleton_still_left.gif");
    Icon skeletonStillRight = new ImageIcon("images/skeleton_still_right.gif");
    
    GamePanel (myJFrame jf) {
        this.jf = jf;
        
        this.setFocusable(true);
        this.addKeyListener(this);
        this.grabFocus();
       
        this.setLayout(new BorderLayout());
        
        //Screen for game elements
        JPanel gameScreen = new JPanel();
        gameScreen.setLayout(null);
       
        //actual playable area
        screenTop = 20;
        screenBottom = (int) this.getSize().getHeight() - 20;
        screenLeft = 20;
        screenRight = (int) this.getSize().getWidth() - 20;
        screenWidth = (int) this.getSize().getWidth() - 40;
        screenHeight = (int) this.getSize().getHeight() - 40;
        
        p = 1;
        //Set keys as not pressed
        keyLog = new int[4];
        keyLog[0] = 0;
        keyLog[1] = 0;
        keyLog[2] = 0;
        keyLog[3] = 0;
        
        //Intial directions for clowns
        direction[0] = 0; // right
        direction[1] = 0; // left
        direction[2] = 0; // up
        direction[3] = 0; // down
        
        //default clown movement speed
        clown_move_speed = 10;
        
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
        option1.setBounds(20, 20, 20, 20);
        gameScreen.add(option1);
        
        option2 = new JButton("2");
        option2.setBounds(100, 100, 80, 50);
        gameScreen.add(option2);      
        option3 = new JButton("3");
        option3.setBounds(200, 100, 80, 50);
        gameScreen.add(option3);
        character = new JLabel();
        x = 333;
        y = 228;
        character.setBounds(x, y, 40, 40);
        
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
        bx = 0;
        by = 0;
        bw = 1400;
        bh = 1000;
        background.setBounds(bx,by, bw, bh);
        background.add(clown);
        background.add(character);
    }
      
    public void getCharacterLocation() {
        double cx = character.getLocation().getX();
        double cy = character.getLocation().getY();
        double cw = character.getBounds().getWidth();
        double ch = character.getBounds().getHeight();
        System.out.println("Character X: " + cx);
        System.out.println("Character Y: " + cy);
        System.out.println("Background X: " + bx);
        System.out.println("Background Y: " + by);
        System.out.println("Intersect: " + intersect(character, clown));
        System.out.println("--------------");
    }
      
    public void getClownLocation(int up, int down) {
        double cx = clown.getLocation().getX();
        double cy = clown.getLocation().getY();
        System.out.println("Clown X: " + cx);
        System.out.println("Clown Y: " + cy);        
        if (up != 0) {
            double up1 = clown.getLocation().getY() + (direction[2]*clown_move_speed);
            double up2 = (direction[2]*clown_move_speed) + by;
            System.out.println("UP IF1, should be < 36: " + up1);
            System.out.println("UP IF2, should be > 0: " + up2);
        } else if (down != 0) {
            double down1 = clown.getLocation().getY() + (direction[3]*clown_move_speed);
            double down2 = by - (direction[3]*clown_move_speed);
            System.out.println("DOWN IF1, should be > 396: " + down1);
            System.out.println("DOWN IF2, should be < -520: " + down2);
        }
    }
    
    public void moveClown() {
        
        // direction[0]  right
        // direction[1]   left
        // direction[2]   up
        // direction[3]   down
        
        if (direction[0]==0 && direction[1]==0 && direction[2]==0 && direction[3]==0) {
            // Generates direction for clown to travel
            // if 0, doesn't move. if 1, right or up. if 2, left or down.
            int dir = (int) (Math.floor(Math.random() * 3));

            // Generates how far clown will travel in the location
            // if 0, 1 spaces. if 1, 2 spaces. if 2, 3 spaces.
            int period = (int) (Math.floor(Math.random() * 3));

            //Move left or right
            switch (period) {
                case 0: period = 1; break;
                case 1: period = 2; break;
                case 2: period = 3; break;
            }
            switch (dir) {
                case 0: direction[0] = 0; direction[1] = 0; break;
                case 1: direction[0] = period; break;
                case 2: direction[1] = period; break;
            }
            //If left/right movement can't fit, recalculate
            if (direction[0] != 0 && clown_move_speed - clownx < 1350) {
                //System.out.println(-(direction[0]*clown_move_speed) + " and "+ bx + "is less than -700");
                moveClown();
            } else if (clownx + clown_move_speed> 50) {
                //System.out.println((direction[1]*clown_move_speed) + " and "+ bx + "is greater than 0");
                moveClown();
            } else {
                //System.out.print("Fits");
            }

            dir = (int) (Math.floor(Math.random() * 3));
            period = (int) (Math.floor(Math.random() * 3));

            //Move up or down
            switch (period) {
                case 0: period = 3; break;
                case 1: period = 6; break;
                case 2: period = 9; break;
            }

            switch (dir) {
                case 0: direction[2] = 0; direction[3] = 0; break;
                case 1: direction[2] = period; break;
                case 2: direction[3] = period; break;
            }
            getClownLocation(direction[2],direction[3]);

            //If up/down movement can't fit, recalculate
            if (direction[2] != 0 && (clowny + clown_move_speed) > 50) {
                //System.out.println((direction[2]*clown_move_speed) + " and "+ by + "is greater than 0");
                moveClown();
            } else if (direction[3] != 0 && (clowny - clown_move_speed) > 600) {
                //System.out.println((direction[3]*clown_move_speed) + " and "+ by + " is less than -520.");
                moveClown();
            } else {
                //System.out.print("Fits");
            }
        } else {
            if (direction[0] > 0) {
                clownx = clownx + clown_move_speed;
                direction[0] = 0;
            } else if (direction[1] > 0) {
                clownx = clownx - clown_move_speed;
                direction[1] = 0;              
            }
            
            if (direction[2] > 0) {
                clowny = clowny - clown_move_speed;
                direction[2] = 0;
            } else if (direction[3] > 0) {
                clowny = clowny + clown_move_speed;
                direction[3] = 0;             
            }
            
            clown.setBounds(clownx, clowny, 40, 60);
        }
        
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
                direction[0] = 0;
                direction[1] = 0;
                direction[2] = 0;
                direction[3] = 0;
                jf.G1.clown_time.stop();
                score_count = 0;
                scoreDisplay.setText("Score: " + score_count);
                jf.G1.time.stop();
                timeNumber = 0;
                timeDisplay.setText("Time: " + timeNumber);
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
            direction[0] = 0;
            direction[1] = 0;
            direction[2] = 0;
            direction[3] = 0;
            jf.G1.clown_time.stop();
            score_count = 0;
            scoreDisplay.setText("Score: " + score_count);
            jf.G1.time.stop();
            timeNumber = 0;
            timeDisplay.setText("Time: " + timeNumber);
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
            moveClown();
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        int k = ke.getKeyCode();
        
        if (k != 0) {
            boolean state = intersect(character, clown);
            if (state == true) {
                clownx = (int) (Math.random()*(650)+250);
                System.out.println(clownx);
                clowny = (int) (Math.random()*(150)+250);
                System.out.println(clowny);
                clown.setBounds(clownx,clowny,40,60);
                direction[0] = 0;
                direction[1] = 0;
                direction[2] = 0;
                direction[3] = 0;
                score_count++;
                scoreDisplay.setText("Score: " + score_count);
            }
        }
        //Movement right (and top-right and down-right)
        if(k == ke.VK_RIGHT){
            keyLog[0] = 1;
            //getCharacterLocation();
            if (option1.getText() == "Cat") {
                character.setIcon(catRight);
            } else if (option1.getText() == "Hunter") {
                character.setIcon(hunterRight);
            } else if (option1.getText() == "Skeleton") {
                character.setIcon(skeletonRight);
            }
            //if (character.getLocation().getX() < 606 && bx > -700) {
            if (keyLog[2] == 0 && keyLog[3] == 0) {
                if (character.getLocation().getX() < 616 + (-bx)) {
                    x = x+10;
                    character.setBounds(x, y, 60, 60);                    
                }
                if (character.getLocation().getX() >= 616 + (-bx) && bx > -700) {
                    bx = bx - 10;
                    background.setBounds(bx, by, bw, bh);   
                }
            } else if (keyLog[2] == 1) {
                if (character.getLocation().getX() < 616 + (-bx)) { 
                    x = x+10;
                }
                if (character.getLocation().getY() > 36 + (-by)) {
                    y = y-10;
                }
                
                if (by < 0 && character.getLocation().getY() <= 36 + (-by)) {
                    by = by + 10;  
                    y = y-10;
                }
                if (character.getLocation().getX() >= 616 + (-bx) && bx > -700) {
                    bx = bx - 10;
                    x = x+10;
                }
                
                character.setBounds(x, y, 60, 60); 
                background.setBounds(bx, by, bw, bh);   
            } else if (keyLog[3] == 1) {
                
                if (character.getLocation().getX() < 616 + (-bx)) {
                    x = x+10;
                }
                if (character.getLocation().getX() >= 616 + (-bx) && bx > -700) {
                    bx = bx - 10;
                    x = x+10;
                }
                 
                if (character.getLocation().getY() < 396 + (-by)) {
                    y = y+10;
                }
                if (by > -520 && character.getLocation().getY() >= 396 + (-by)) {
                    by = by - 10;
                    y = y+10;
                }
                
                character.setBounds(x, y, 60, 60);
                background.setBounds(bx, by, bw, bh);    
            }
        }
        //Movement left (and down-left and down-right)
        if(k == ke.VK_LEFT){
            keyLog[1] = 1;
            //getCharacterLocation();
            if (option1.getText() == "Cat") {
                character.setIcon(catLeft);
            } else if (option1.getText() == "Hunter") {
                character.setIcon(hunterLeft);
            } else if (option1.getText() == "Skeleton") {
                character.setIcon(skeletonLeft);
            }
            if (keyLog[2] == 0 && keyLog[3] == 0) {
                if (character.getLocation().getX() > 6 + (-bx)) {
                    x = x-10;
                    character.setBounds(x, y, 60, 60);
                }
                if (character.getLocation().getX() <= 6 + (-bx) && bx < 0) {
                    bx = bx + 10;
                    background.setBounds(bx, by, bw, bh);   
                }
            } else if (keyLog[2] == 1) {
                if (character.getLocation().getX() > 6 + (-bx)) {
                    x = x-10;
                }
                if (character.getLocation().getX() <= 6 + (-bx) && bx < 0) {
                    bx = bx + 10;
                    x = x-10;
                }
                if (character.getLocation().getY() > 36 + (-by)) {
                    y = y-10;
                }
                
                if (by < 0 && character.getLocation().getY() <= 36 + (-by)) {
                    by = by + 10;  
                    y = y-10;
                }
                
                character.setBounds(x, y, 60, 60);
                background.setBounds(bx, by, bw, bh);
                
            } else if (keyLog[3] == 1) {
                if (character.getLocation().getX() > 6 + (-bx)) {
                    x = x-10;
                }
                if (character.getLocation().getX() <= 6 + (-bx) && bx < 0) {
                    bx = bx + 10;
                    x = x-10;
                }
                if (character.getLocation().getY() < 396 + (-by)) {
                    y = y+10;
                }
                if (by > -520 && character.getLocation().getY() >= 396 + (-by)) {
                    by = by - 10;
                    y = y+10;
                }
                
                character.setBounds(x, y, 60, 60);
                background.setBounds(bx, by, bw, bh);
            }
        }
        if(k == ke.VK_UP){
            keyLog[2] = 1;
            //getCharacterLocation();
            if (character.getIcon() == catStillLeft) {
                character.setIcon(catLeft);
            } else if (character.getIcon() == catStillRight) {
                character.setIcon(catRight);
            } else if (character.getIcon() == hunterStillLeft) {
                character.setIcon(hunterLeft);
            } else if (character.getIcon() == hunterStillRight) {
                character.setIcon(hunterRight);
            } else if (character.getIcon() == skeletonStillLeft) {
                character.setIcon(skeletonLeft);
            } else if (character.getIcon() == skeletonStillRight) {
                character.setIcon(skeletonRight);
            }
            if (keyLog[0] == 0 && keyLog[1] == 0) {
                if (character.getLocation().getY() > 36 + (-by)) {
                    y = y-10;
                    character.setBounds(x, y, 60, 60);
                }
                if (by < 0 && character.getLocation().getY() <= 36 + (-by)) {
                    by = by + 10;
                    background.setBounds(bx, by, bw, bh);                    
                }
            } else if (keyLog[0] == 1) {
                if (character.getLocation().getX() < 616 + (-bx)) { 
                    x = x+10;
                }
                if (character.getLocation().getX() >= 616 + (-bx) && bx > -700) {
                    bx = bx - 10;
                    x = x+10;
                }
                if (character.getLocation().getY() > 36 + (-by)) {
                    y = y-10;
                }
                if (by < 0 && character.getLocation().getY() <= 36 + (-by)) {
                    by = by + 10;
                    y = y-10;                 
                }
                background.setBounds(bx, by, bw, bh);   
                character.setBounds(x, y, 60, 60);
                
            } else if (keyLog[1] == 1) {
                if (character.getLocation().getX() > 6 + (-bx)) {
                    x = x-10;
                }
                if (character.getLocation().getX() <= 6 + (-bx) && bx < 0) {
                    x = x-10;
                    bx = bx + 10;
                }
                if (character.getLocation().getY() > 36 + (-by)) {
                    y = y-10;
                }
                if (by < 0 && character.getLocation().getY() <= 36 + (-by)) {
                    y = y-10;
                    by = by + 10;                   
                }
                character.setBounds(x, y, 60, 60);
                background.setBounds(bx, by, bw, bh); 
                
            }
        }
        if(k == ke.VK_DOWN){
            keyLog[3] = 1;
            //getCharacterLocation();
            if (character.getIcon() == catStillLeft) {
                character.setIcon(catLeft);
            } else if (character.getIcon() == catStillRight) {
                character.setIcon(catRight);
            } else if (character.getIcon() == hunterStillLeft) {
                character.setIcon(hunterLeft);
            } else if (character.getIcon() == hunterStillRight) {
                character.setIcon(hunterRight);
            } else if (character.getIcon() == skeletonStillLeft) {
                character.setIcon(skeletonLeft);
            } else if (character.getIcon() == skeletonStillRight) {
                character.setIcon(skeletonRight);
            }
            if (keyLog[0] == 0 && keyLog[1] == 0) {
                if (character.getLocation().getY() < 396 + (-by)) {
                    y = y+10;
                    character.setBounds(x, y, 60, 60);
                }
                if (by > -520 && character.getLocation().getY() >= 396 + (-by)) {
                    by = by - 10;
                    background.setBounds(bx, by, bw, bh);                    
                }
            } else if (keyLog[0] == 1) {
                if (character.getLocation().getX() < 616 + (-bx)) { 
                    x = x+10;
                }
                if (character.getLocation().getX() >= 616 + (-bx) && bx > -700) {
                    bx = bx - 10;
                    x = x+10;
                }
                if (character.getLocation().getY() < 396 + (-by)) {
                    y = y+10;
                }
                if (by > -520 && character.getLocation().getY() >= 396 + (-by)) {
                    by = by - 10;
                    y = y+10;                    
                }
                
                character.setBounds(x, y, 60, 60);
                background.setBounds(bx, by, bw, bh);             
            } else if (keyLog[1] == 1) {
                if (character.getLocation().getY() < 396 + (-by)) {
                    y = y+10;
                }
                if (by > -520 && character.getLocation().getY() >= 396 + (-by)) {
                    by = by - 10;
                    y = y+10;                 
                }
                if (character.getLocation().getX() > 6 + (-bx)) {
                    x = x-10;
                }
                if (character.getLocation().getX() <= 6 + (-bx) && bx < 0) {
                    x = x-10;
                    bx = bx + 10; 
                }
                
                background.setBounds(bx, by, bw, bh);   
                character.setBounds(x, y, 60, 60);
            }
        }
        if (k == ke.VK_R) {
            moveClown();
        }
        if (k == ke.VK_P) {
            /*
            if (p = 1) {
                
                p++;
            } else {
                p = 1;
            }*/
        }
        if (k == ke.VK_O) {
            clown_move_speed = 30;
        }
        if (k == ke.VK_I) {
            clown_move_speed = 20;
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        int k = ke.getKeyCode();
        
        if(k == ke.VK_RIGHT){
            keyLog[0] = 0;
            if (option1.getText() == "Cat") {
                character.setIcon(catStillRight);
            } else if (option1.getText() == "Hunter") {
                character.setIcon(hunterStillRight);
            } else if (option1.getText() == "Skeleton") {
                character.setIcon(skeletonStillRight);
            }
        }
        if(k == ke.VK_LEFT){
            keyLog[1] = 0;
            if (option1.getText() == "Cat") {
                character.setIcon(catStillLeft);
            } else if (option1.getText() == "Hunter") {
                character.setIcon(hunterStillLeft);
            } else if (option1.getText() == "Skeleton") {
                character.setIcon(skeletonStillLeft);
            }
        }
        if(k == ke.VK_UP){
            keyLog[2] = 0;
            if (character.getIcon() == catLeft) {
                character.setIcon(catStillLeft);
            } else if (character.getIcon() == catRight) {
                character.setIcon(catStillRight);
            } else if (character.getIcon() == hunterLeft) {
                character.setIcon(hunterStillLeft);
            } else if (character.getIcon() == hunterRight) {
                character.setIcon(hunterStillRight);
            } else if (character.getIcon() == skeletonLeft) {
                character.setIcon(skeletonStillLeft);
            } else if (character.getIcon() == skeletonRight) {
                character.setIcon(skeletonStillRight);
            }
        }
        if(k == ke.VK_DOWN){
            keyLog[3] = 0;
            if (character.getIcon() == catLeft) {
                character.setIcon(catStillLeft);
            } else if (character.getIcon() == catRight) {
                character.setIcon(catStillRight);
            } else if (character.getIcon() == hunterLeft) {
                character.setIcon(hunterStillLeft);
            } else if (character.getIcon() == hunterRight) {
                character.setIcon(hunterStillRight);
            } else if (character.getIcon() == skeletonLeft) {
                character.setIcon(skeletonStillLeft);
            } else if (character.getIcon() == skeletonRight) {
                character.setIcon(skeletonStillRight);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
