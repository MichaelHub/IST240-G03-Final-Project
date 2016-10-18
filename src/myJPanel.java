/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;

public class myJPanel extends JPanel
{
	public myJPanel()
	{
		super();
		setBackground(Color.gray);
		setLayout(new GridLayout(1,2));
		LandingPanel lp1 = new LandingPanel();
 
                add(lp1);
                

	}
}
