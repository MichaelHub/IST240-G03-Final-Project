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

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel About = new JLabel ("About");
        About.setForeground(Color.WHITE);
        About.setFont(About.getFont().deriveFont(40.0f));
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        this.add(About,c);

        JEditorPane creators = new JEditorPane("text/html", "");
        creators.setText("<b>About the Creators</b><br><ul><li><b>Michael Berhane</b> is a junior from  Montgomery Village, MD and is majoring in IST.<ul><li>Michael enjoys creating websites and procrastinating.</li></ul><li><b>Max Dubyk</b> is a senior from Philadelphia, PA and is majoring in SRA.<ul><li>Max likes going hiking and also enjoys watching Penn State football.</li></ul><li><b>Kyle Mullen</b> is a sophomore from Philadelphia, PA and is majoring in IST.<ul><li>Kyle enjoys going skiing and playing soccer with his friends.</li></ul></li></ul><b><center>We've worked hard on Clown Catcher and hope you enjoy!</center></b>");
        creators.setEditable(false);
        creators.setEnabled(false);
        creators.setDisabledTextColor(Color.BLACK);
        c.gridx = 1;
        c.gridy = 1;
        this.add(creators,c);


        back = new JButton ("Back");
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 4;
        this.back.addActionListener(this);
        this.add(back,c);


    }


    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.A1);
                jf.lpane.add(jf.L1);
            }

    }

}