
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel  extends JPanel implements ActionListener, KeyListener{
    JButton back, menu, timeDisplay, scoreDisplay, menuClose, menuMain, menuOptions;
    JButton option1, option2, option3;
    JLabel character, background;
    myJFrame jf;
    JPanel gameMenu;
    Timer time;
    int timeNumber;
    int x,y;//Cooridinates for character
    int bx,by,bw,bh; //Bounds for background
    Character lastkey = null;//Remember last key to clear input
    int[] keyLog;
    
    String chosen_character, chosen_time, chosen_difficulty;
    
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
        character.setBounds(x, y, 60, 60);
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
        
        gameScreen.add(background);
        bx = -150;
        by = -150;
        bw = 1400;
        bh = 1000;
        background.setBounds(bx,by, bw, bh);
                
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
    
    public void getCharacterLocation() {
        double cx = character.getBounds().getX();
        double cy = character.getBounds().getY();
        double cw = character.getBounds().getWidth();
        double ch = character.getBounds().getHeight();
        System.out.println("Character X: "+cx);
        System.out.println("Character Y: "+cy);
        System.out.println("Character W: "+cw);
        System.out.println("Character H: "+ch);
        System.out.println("Background X: "+bx);
        System.out.println("Background Y: "+by);
        System.out.println("Background W: "+bw);
        System.out.println("Background H: "+bh);
        System.out.println("---------------");
    }
    
    public boolean intersect (JLabel one, JButton two) {
        double ox = one.getBounds().getX();
        double oy = one.getBounds().getY();
        double ow = one.getBounds().getWidth();
        double oh = one.getBounds().getHeight();
        ox = ox-(ow/2);
        double tx = two.getBounds().getX();
        double ty = two.getBounds().getY();
        double tw = two.getBounds().getWidth();
        double th = two.getBounds().getHeight();
        tx = tx-(tw/2);
        double oLeftSide = ox;
        double oRightSide = ox + ow;
        double tLeftSide = tx;
        double tRightSide = tx + tw;
        double oBottomSide = oy + oh;
        double oTopSide = oy;
        double tBottomSide = ty + th;
        double tTopSide = ty;
        if (oLeftSide < tRightSide && oLeftSide > tLeftSide || oLeftSide < tRightSide && oLeftSide > tLeftSide) {
            System.out.println("Intersecting on X");
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int k = ke.getKeyCode();
        
        //Movement right (and top-right and down-right)
        if(k == ke.VK_RIGHT){
            keyLog[0] = 1;
            getCharacterLocation();
            intersect(character, option2);
            if (character.getBounds().getX() > 570.0 && bx > -240.01) {
                bx = bx - 10;
                background.setBounds(bx, by, bw, bh);
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
            if (character.getBounds().getX() < 60.0 && bx < 0) {
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
            if (character.getBounds().getY() < 60.0 && by < -140) {
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
            if (character.getBounds().getY() > 380.0 && by > -360) {
                by = by - 10;
                background.setBounds(bx, by, bw, bh);
            } else if (keyLog[0] == 0 && keyLog[1] == 0) {
                y = y+10;
                if (y>400){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[0] == 1) {
                x = x+10;
                if (x>650){
                    x = x-10;
                }
                y = y+10;
                if (y>400){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
            } else if (keyLog[1] == 1) {
                x = x-10;
                if (x<0){
                    x=x+10;
                }
                y = y+10;
                if (y>400){
                    y=y-10;
                }
                character.setBounds(x, y, 60, 60);
            }            
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
