
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HighScorePanel extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;
    
    HighScorePanel(myJFrame jf){
        this.jf = jf;
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
        back = new JButton ("Back");
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        
        this.back.addActionListener(this);
        this.add(back,c);
        
        
        
        
        String[] columnNames = {"First Name","Last Name",
                                        "Score"};     
                String[][] data = new String[5][3];

                data[0][0] = "Kyle";   
                data[0][1] = "Mullen";
                data[0][2] = "40";
                
                data[1][0] = "Max";   
                data[1][1] = "D";
                data[1][2] = "27";
                
                data[2][0] = "Mike";   
                data[2][1] = "Smith";
                data[2][2] = "10";
                
                data[3][0] = "Connor";   
                data[3][1] = "Ohara";
                data[3][2] = "2";
                
                data[4][0] = "Matt";   
                data[4][1] = "Tim";
                data[4][2] = "0";
                

                JTable table = new JTable(data, columnNames);
                JScrollPane tableScrollPane = new JScrollPane(table);
                
                c.gridx = 1;
                c.gridy = 1;
		this.add(tableScrollPane,c);
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
        if (obj == this.back)
            {
                jf.lpane.remove(jf.SB);
                
     
                jf.lpane.add(jf.L1, new Integer(1),0);
                jf.lpane.add(jf.L2, new Integer(0),0);
                
                //jf.lpane.add(jf.L2);
            }
    }
}
