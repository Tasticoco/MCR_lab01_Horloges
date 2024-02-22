import javax.swing.*;
import java.awt.*;

public class ChronoPanel extends JPanel {
    Chrono chrono;
    static int totId = 0;
    int id;

    JPanel panel = new JPanel();

    public ChronoPanel(Chrono c) {
        chrono = c;
        id = totId++;
    }

    public JPanel paintComponent() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 7));
        panel.add(new JLabel("Chrono #" + id));
        panel.add(new JButton("Démarrer"));
        panel.add(new JButton("Arrêter"));
        panel.add(new JButton("Réinitialiser"));
        panel.add(new JButton("Cadran romain"));
        panel.add(new JButton("Cadran arabe"));
        panel.add(new JButton("Numérique"));

        return panel;
    }
}
