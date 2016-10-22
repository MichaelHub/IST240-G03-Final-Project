/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maxdubyk
 */
import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Landing2 extends JPanel{
    
    JLabel next;
    public JLabel background;
    Landing2(){
        super();
        setBackground(Color.GRAY);
        next = new JLabel("");
        add(next);
        JButton button = new JButton();
        Icon img = new ImageIcon(getClass().getResource("background.gif"));
        
        background = new JLabel();
        background.setIcon( img );
        background.setLayout( new BorderLayout() );
        add( background );
        JLabel title = new JLabel("Clown Catcher");
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        background.add(title);
    }
    public void removeBackground() {
        remove (background);
        setBackground(Color.CYAN);
        setBackground(Color.GRAY);
    }
    
}
