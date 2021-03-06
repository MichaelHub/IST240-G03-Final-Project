import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Options extends JPanel implements ActionListener, ItemListener{
    
    JComboBox timeSelect, difficultySelect, characterSelect;
    JLabel title, characterImage;
    JButton characterLabel, timeLabel, difficultyLabel, cont, back;
    myJFrame jf;

    
    Options(myJFrame jf){
        super();
        this.jf = jf;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        //Character select
        String[] characters = {"Cat", "Hunter", "Skeleton"};
        characterSelect = new JComboBox(characters);
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 0;
        c.insets = new Insets(0,100,30,0);
        this.add(characterSelect, c);
        this.characterSelect.addItemListener(this);
        
        Icon cat = new ImageIcon("images/cat.gif");
        
        characterImage = new JLabel();
        characterImage.setIcon(cat);
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 0;
        c.insets = new Insets(0,250,30,0);
        this.add(characterImage, c);
        
        characterLabel = new JButton();
        characterLabel.setText("Character:");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 0;
        c.insets = new Insets(0,-100,30,0);
        characterLabel.setEnabled(false);
        this.add(characterLabel, c);
        
        //Time select
        String[] times = {"Day", "Night"};
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
        
        //Difficulty select
        String[] difficulties = {"Easy", "Medium", "Hard", "Clown Hell"};
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
        
        //Continue button
        cont = new JButton();
        cont.setText("Continue");
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = new Insets(0,250,30,0);
        cont.setEnabled(true);
        this.add(cont, c);
        this.cont.addActionListener(this);
        
        //Back button
        back = new JButton();
        back.setText("Back");
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(0,100,30,0);
        back.setEnabled(true);
        this.add(back, c);
        this.back.addActionListener(this);
        
        //Titie
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
                //Set button text on GamePanel for Assignment 2
                jf.G1.chosen_character = String.valueOf(characterSelect.getSelectedItem());
                
                if (String.valueOf(difficultySelect.getSelectedItem()) == "Easy"){
                    jf.G1.clown_time.setDelay(200);
                    jf.G1.clown_move_speed = 10;
                }
                if (String.valueOf(difficultySelect.getSelectedItem()) == "Medium"){
                    jf.G1.clown_time.setDelay(150);
                    jf.G1.clown_move_speed = 20;
                }
                if (String.valueOf(difficultySelect.getSelectedItem()) == "Hard"){
                    jf.G1.clown_time.setDelay(100);
                    jf.G1.clown_move_speed = 25;
                }
                if (String.valueOf(difficultySelect.getSelectedItem()) == "Clown Hell"){
                    jf.G1.clown_time.setDelay(75);
                    jf.G1.clown_move_speed = 25;
                }

                
                jf.G1.chosen_time = String.valueOf(timeSelect.getSelectedItem());
                
                jf.G1.option1.setText(jf.G1.chosen_character);
                jf.G1.option2.setText(String.valueOf(difficultySelect.getSelectedItem()));
                jf.G1.option3.setText(jf.G1.chosen_time);
                
                Icon hunter = new ImageIcon("images/hunter.gif");
                Icon skeleton = new ImageIcon("images/skeleton.gif");
                Icon cat = new ImageIcon("images/cat.gif");
                int selection = characterSelect.getSelectedIndex();
                    switch (selection) {
                        case 0: jf.G1.character.setIcon(cat); break;
                        case 1: jf.G1.character.setIcon(hunter); break;
                        case 2: jf.G1.character.setIcon(skeleton); break;
                    }
                    
                Icon day = new ImageIcon("images/map1.png");
                Icon night = new ImageIcon("images/map2.png");
                if (String.valueOf(timeSelect.getSelectedItem()) == "Day"){
                    jf.G1.background.setIcon(day);
                } else {
                    jf.G1.background.setIcon(night);
                }
                
                jf.G1.timeNumber = 0;
                jf.G1.option1.setVisible(false);
                jf.G1.option2.setVisible(false);
                jf.G1.option3.setVisible(false);
                
                jf.lpane.remove(jf.L2);
                jf.lpane.remove(jf.OP);
                jf.lpane.add(jf.G1);
                jf.G1.grabFocus();
                jf.G1.timeNumber = 0;
                jf.G1.clown_time.start();      
                jf.G1.timeNumber = 0;
                jf.G1.time.start();
                
        }
        if (obj == back) {
            jf.lpane.remove(jf.OP);
            jf.lpane.add(jf.L1, new Integer(1), 0);
        }
    }
    public void itemStateChanged(ItemEvent event) {
        if ((event.getStateChange() == ItemEvent.SELECTED)) {
            Icon hunter = new ImageIcon("images/hunter.gif");
            Icon skeleton = new ImageIcon("images/skeleton.gif");
            Icon cat = new ImageIcon("images/cat.gif");
            int selection = characterSelect.getSelectedIndex();
                switch (selection) {
                    case 0: characterImage.setIcon(cat); break;
                    case 1: characterImage.setIcon(hunter); break;
                    case 2: characterImage.setIcon(skeleton); break;
                }
        }
    }
}