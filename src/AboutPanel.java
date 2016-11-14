import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.beans.*;

public class AboutPanel  extends JPanel implements ActionListener{
    private JButton back,b1,b2;
    private myJFrame jf;
    private XMLEncoder xe;
    private XMLDecoder de;
    private JLabel jl1;
    private String About;


    AboutPanel (myJFrame jf) {
        this.jf = jf;

        this.setLayout( new BorderLayout() );
        JLabel jb1 = new JLabel (getAbout());

        this.add(jb1, BorderLayout.NORTH);
        back = new JButton ("Back");
        this.add(back, BorderLayout.CENTER);
        this.back.addActionListener(this);

        b2 = new JButton("Save");
        add(b2);
        b2.addActionListener(this);
        b2.setBounds(0, 50, 50, 50);

        this.setBackground(Color.GRAY);
    }
    public String getAbout(){
        return About;
    }

    public void setAbout(String About){
        this.About = About;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        //read read the xml file with the about information
        //display
        try {
              de = new XMLDecoder(new BufferedInputStream(new FileInputStream("about.xml")));
             }
          catch(Exception xx) {xx.printStackTrace();}
          try {
                         About = (String)de.readObject();
                         //display it

            }
          catch(Exception xx) {xx.printStackTrace();}
          try {
             de.close();
            }
          catch(Exception xx) {xx.printStackTrace();}
        }

    



    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.getBack())
            {
                getJf().lpane.remove(getJf().A1);
                getJf().lpane.add(getJf().L1);
                getJf().lpane.add(getJf().L2);
            }
        if (obj==b2)
         {
           try {
                setXe(new XMLEncoder(new BufferedOutputStream(new FileOutputStream("about.xml"))));
             }
           catch(Exception xx) {xx.printStackTrace();}

           try {
                         getXe().writeObject(getJl1());
            }
           catch(Exception xx) {xx.printStackTrace();}

           try {
                getXe().close();
            }
           catch(Exception xx) {xx.printStackTrace();}
         }

    }

    /**
     * @return the back
     */
    public JButton getBack() {
        return back;
    }

    /**
     * @param back the back to set
     */
    public void setBack(JButton back) {
        this.back = back;
    }

    /**
     * @return the jf
     */
    public myJFrame getJf() {
        return jf;
    }

    /**
     * @param jf the jf to set
     */
    public void setJf(myJFrame jf) {
        this.jf = jf;
    }

    /**
     * @return the xe
     */
    public XMLEncoder getXe() {
        return xe;
    }

    /**
     * @param xe the xe to set
     */
    public void setXe(XMLEncoder xe) {
        this.xe = xe;
    }

    /**
     * @return the jl1
     */
    public JLabel getJl1() {
        return jl1;
    }

    /**
     * @param jl1 the jl1 to set
     */
    public void setJl1(JLabel jl1) {
        this.jl1 = jl1;
    }

}