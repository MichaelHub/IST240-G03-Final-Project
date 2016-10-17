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
import javax.swing.*;
public class Landing1 extends JPanel{
    JButton start,instructions,about;
    Landing1(){
        super();
        start = new JButton();
        start.setText("Start Game");
        
        instructions = new JButton();
        instructions.setText("Instructions");
        
        about = new JButton();
        about.setText("About");
        
        add(start);
        add(instructions);
        add(about);
    }
    
}
