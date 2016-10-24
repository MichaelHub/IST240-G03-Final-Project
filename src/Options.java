import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Options extends JPanel implements ActionListener{
    
    JButton characterSelect;
    JComboBox timeSelect;
    JLabel title;
    JButton characterLabel, timeLabel;
    myJFrame jf;

    
    Options(myJFrame jf){
        super();
        this.jf = jf;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        characterSelect = new JButton();
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 0;
        c.insets = new Insets(0,100,30,0);
        this.add(characterSelect, c);
        this.characterSelect.addActionListener(this);
        
        characterLabel = new JButton();
        characterLabel.setText("Character:");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 0;
        c.insets = new Insets(0,-100,30,0);
        characterLabel.setEnabled(false);
        this.add(characterLabel, c);
        
        String[] times = {"","Day", "Night"};
        timeSelect = new JComboBox(times);
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 0;
        c.insets = new Insets(0,100,30,0);
        this.add(timeSelect, c);
        this.timeSelect.addActionListener(this);
        
        timeLabel = new JButton();
        timeLabel.setText("Time:");
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 0;
        c.insets = new Insets(0,-100,30,0);
        timeLabel.setEnabled(false);
        this.add(timeLabel, c);
        
        title = new JLabel();
        title.setText("Options");
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(40.0f));
        c.insets = new Insets(3,0,65,0);
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        this.add(title,c);
        
    }
    public void actionPerformed(ActionEvent event) {
        Object obj = event.getSource();
    }
}