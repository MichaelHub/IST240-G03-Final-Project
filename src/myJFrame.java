/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;

public class myJFrame extends JFrame
{
	public myJFrame ()
	{
		super ("My First Frame");

  	 	myJPanel mjp = new myJPanel();

		//getContentPane().add(mjp,"Center");
                add(mjp,"Center");
                
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize (800, 800);
		setVisible(true);
	}

}
