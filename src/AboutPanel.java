
import java.awt.*;
import javax.swing.*;

public class AboutPanel  extends JPanel {
    AboutPanel () {
        this.setLayout( new BorderLayout() );
        JLabel about = new JLabel ("<html>About<br /><br /><ul><li>Maksim Dubyk</li><li>Kyle Mullen</li><li>Michael Berhane</li>");
        add(about, BorderLayout.CENTER);
        this.setBackground(Color.GRAY);
    }
}
