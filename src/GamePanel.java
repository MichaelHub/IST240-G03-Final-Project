
import java.awt.*;
import javax.swing.*;

public class GamePanel  extends JPanel {
    GamePanel () {
        this.setLayout( new BorderLayout() );
        JLabel info = new JLabel ("Start of game");
        add(info, BorderLayout.CENTER);
        this.setBackground(Color.GRAY);
    }
}
