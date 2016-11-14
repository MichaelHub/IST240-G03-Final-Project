import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.beans.*;

public class AboutPanel  extends JPanel implements ActionListener{

    XML_240 x2;
    JButton back;
    myJFrame jf;
    JLabel about;
    


    AboutPanel (myJFrame jf) {
        this.jf = jf;

        x2 = new XML_240();
        
        this.setLayout( new BorderLayout() );
        
        String test1 = "These will be where the directions will be written and read from in XML";
        x2.openWriterXML("about.xml");
        x2.writeObject(test1);
        x2.closeWriterXML();

        x2.openReaderXML("about.xml");
        String s1 = (String)x2.ReadObject();
        x2.closeReaderXML();
        
        about = new JLabel(s1);

        this.add(about, BorderLayout.NORTH);
        back = new JButton ("Back");
        this.add(back, BorderLayout.CENTER);
        this.back.addActionListener(this);


        this.setBackground(Color.GRAY);
        
        
    }
    

    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.A1);
                jf.lpane.add(jf.L1);
                jf.lpane.add(jf.L2);
            }

    }
    
}

    