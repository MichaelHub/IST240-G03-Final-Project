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
    Landing1 L1;
    Landing2 L2;
    
    LandingPanel()
    {
        super();
        setLayout(new BorderLayout());
        
         L1 = new Landing1(L2);
         L2 = new Landing2(L1);

        add(L1,"North");
        add(L2,"Center");
    }
    
}
