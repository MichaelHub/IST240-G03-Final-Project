import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Options extends JPanel implements ActionListener{
    
    JComboBox timeSelect, difficultySelect, characterSelect;
    JLabel title;
    JButton characterLabel, timeLabel, difficultyLabel, cont;
    myJFrame jf;

    
    Options(myJFrame jf){
        super();
        this.jf = jf;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        String[] characters = {"","Cat", "Hunter", "Skeleton"};
        characterSelect = new JComboBox(characters);
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
        
        String[] difficulties = {"","Easy", "Medium", "Hard", "Clown Hell"};
        difficultySelect = new JComboBox(difficulties);
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 0;
        c.insets = new Insets(0,100,30,0);
        this.add(difficultySelect, c);
        this.difficultySelect.addActionListener(this);
        
        difficultyLabel = new JButton();
        difficultyLabel.setText("Difficulty:");
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 0;
        c.insets = new Insets(0,-100,30,0);
        difficultyLabel.setEnabled(false);
        this.add(difficultyLabel, c);  
        
        cont = new JButton();
        cont.setText("Continue");
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 0;
        c.insets = new Insets(0,-100,30,0);
        cont.setEnabled(true);
        this.add(cont, c);
        this.cont.addActionListener(this);
        
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
        if (obj == cont)
        {
            
                jf.lpane.remove(jf.L2);
                jf.lpane.remove(jf.OP);
                jf.lpane.add(jf.G1);
        }
    }
}