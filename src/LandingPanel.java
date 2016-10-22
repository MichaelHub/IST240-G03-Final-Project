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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class LandingPanel extends JPanel implements ActionListener{
    Landing1 L1;
    Landing2 L2;
    
    LandingPanel()
    {
        super();
        setLayout(new BorderLayout());
        
         L1 = new Landing1();
         L2 = new Landing2();

        add(L1,"North");
        add(L2,"Center");
        
        
        L1.start.addActionListener(this);
        L1.about.addActionListener(this);
        L1.instructions.addActionListener(this);
    }
    
       public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == L1.start)
            {
                L2.removeBackground();
                L2.next.setText("THIS WOULD START BE THE GAME");
            }
        else if (obj == L1.instructions)
            {
                L2.next.setText("Instructions Here");
            }
        else if (obj == L1.about)
            {
                L2.next.setText("ABOUT DEVELOPERS...\nMaksim Dubyk\nKyle Mullen\nMichaelBerhane");
            }
}
}
