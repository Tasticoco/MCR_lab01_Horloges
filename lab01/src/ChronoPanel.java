import javax.swing.*;
import java.awt.*;

public class ChronoPanel extends JPanel {
    Chrono chrono;
    JPanel panel = new JPanel();

    public ChronoPanel(Chrono c) {
        chrono = c;
    }

    public void paintComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 7));
    }
}
