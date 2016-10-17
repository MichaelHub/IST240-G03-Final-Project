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
public class LandingPanel extends JPanel{
    
    
    LandingPanel()
    {
        super();
        setLayout(new BorderLayout());
        
        Landing1 L1 = new Landing1();
        Landing2 L2 = new Landing2();

        add(L1,"North");
        add(L2,"Center");
    }
    
}
