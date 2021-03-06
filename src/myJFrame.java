import java.awt.*;
import javax.swing.*;

public class myJFrame extends JFrame
{    
    JFrame frame = new JFrame();
    JLayeredPane lpane = new JLayeredPane();
    
    //Home panel
    Landing2 L2 = new Landing2();
    Landing1 L1 = new Landing1(L2, this);
    
    //Game panel
    GamePanel G1 = new GamePanel(this);
    
    //About Panel
    AboutPanel A1 = new AboutPanel(this);
    
    //Instructions Panel
    InstructionsPanel I1 = new InstructionsPanel(this);
    
    //Options Panel
    Options OP = new Options(this);
    
    //Score Board Panel
    HighScorePanel SB = new HighScorePanel(this);
    
	public myJFrame ()
	{
		super ("Clown Catcher");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                
                //Window frame
                frame.setPreferredSize(new Dimension(700,500));
                frame.setLayout(new BorderLayout());
                frame.add(lpane, BorderLayout.CENTER);
                
                //Home title and buttons
                L1.setBounds(100,50,500,400);
                L1.setOpaque(false);
                //Home background
                L2.setBounds(0,-10,700,500);
                L2.setBackground(Color.GREEN);
                L2.setOpaque(true);
                
                //Game Panel (not visible at start)
                G1.setBounds(0,-28,700,500);
                G1.setBackground(Color.GRAY);
                G1.setOpaque(true);
                
                //About Panel (not visible at start)
                A1.setBounds(0,0,700,500);
                A1.setBackground(Color.GRAY);
                A1.setOpaque(false);
                
                //Instructions Panel (not visible at start)
                I1.setBounds(0,0,700,500);
                I1.setBackground(Color.GRAY);
                I1.setOpaque(false);
                
                //Score Board Panel (not visible at start)
                SB.setBounds(0,0,700,500);
                SB.setBackground(Color.GRAY);
                SB.setOpaque(false);
                
                
                //Options Panel (not visible at start)
                OP.setBounds(0,0,700,500);
                OP.setOpaque(false);
                
                lpane.add(L1, new Integer(1), 0);
                lpane.add(L2, new Integer(0), 0);
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);


	}

}
