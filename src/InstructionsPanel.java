import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InstructionsPanel  extends JPanel implements ActionListener{
    JButton back;
    myJFrame jf;

    InstructionsPanel (myJFrame jf) {
        this.jf = jf;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel instructions = new JLabel ("Instructions");
        instructions.setForeground(Color.WHITE);
        instructions.setFont(instructions.getFont().deriveFont(40.0f));
        c.weightx = 0.0;
        c.gridwidth = 4;
        c.gridx = 1;
        c.gridy = 0;
        this.add(instructions,c);

        JEditorPane steps = new JEditorPane("text/html", "");
        steps.setText("<b>How to Play</b><br><br><b>Step 1.</b> Select your character, time, and difficulty and click continue. <br><br><b>Step 2.</b> Use the arrow keys to catch the clown. <br><br><b>Step 3.</b> You will have 60 seconds to catch the clown as many times as you can. <br><br><b>Step 4.</b> When the game is finished, add your name to the score board and try to set the high score!");
        steps.setEditable(false);
        steps.setEnabled(false);
        steps.setDisabledTextColor(Color.BLACK);
        c.gridx = 1;
        c.gridy = 1;
        this.add(steps,c);


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
                jf.lpane.remove(jf.I1);
                jf.lpane.add(jf.L1);
            }
    }
}