
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HighScorePanel extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;
    XML_240 x2;
    
    HighScorePanel(myJFrame jf) {
        this.jf = jf;
        
        x2 = new XML_240();
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        
        back = new JButton ("Back");
        c.weightx = 0.0;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 2;
        
        this.back.addActionListener(this);
        this.add(back,c);
        int lines = 0;
        try{
            URL scores = new URL("http://ec2-35-162-147-239.us-west-2.compute.amazonaws.com/scores.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(scores.openStream()));
            String inputLine;
            while ((inputLine = reader.readLine()) != null) lines++;
            reader.close();
            //System.out.println(lines);
        } catch (IOException ex) {
            Logger.getLogger(HighScorePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (lines != 0){
            int entryCount = (lines - 3)/4;
            int dataCount = lines - 3;
            //System.out.println(entryCount);
            //System.out.println(dataCount);
            String[] columnNames = {"First Name","Last Name","Score","Difficulty"};     
            Object[][] data = new Object[entryCount][4];
            URL scores = null;
            try {
                scores = new URL("http://ec2-35-162-147-239.us-west-2.compute.amazonaws.com/scores.xml");
            } catch (MalformedURLException ex) {
                Logger.getLogger(HighScorePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            x2.openReaderXML(scores);
            for (int z = 1, x = 0, y = 0; z <= lines; z++ ) {
                if (y == 0 || y == 1 || y == 3) {
                    data[x][y] = (String)x2.ReadObject();
                    y++;
                } else if (y == 2) {
                    data[x][y] = (int)x2.ReadObject();
                    y++;                    
                } else if (y == 4) {
                    x++;
                    y = 0;
                }
            }
            x2.closeReaderXML();


            JTable table = new JTable();
            JScrollPane tableScrollPane = new JScrollPane(table);
            
            table.setModel(new DefaultTableModel(data, columnNames) {
                Class[] types = { String.class, String.class, Integer.class,
                        String.class };
                boolean[] canEdit = { false, false, false, false };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return this.types[columnIndex];
                }

                public boolean isCellEditable(int columnIndex) {
                    return this.canEdit[columnIndex];
                }
            });
            
            table.getColumnModel().setColumnMargin(15);
            table.setAutoCreateRowSorter(true);

            c.gridx = 1;
            c.gridy = 1;
            this.add(tableScrollPane,c);

        }
        
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
