import javax.swing.*;
import java.awt.*;

public class ControlFrame extends JFrame {
    JFrame frame = new JFrame("Panneau de contrôle");

    public ControlFrame(int nbChrono){


        for(int i = 0; i < nbChrono; i++){
            ChronoPanel chronoPanel = new ChronoPanel(new Chrono());
            frame.add(chronoPanel.paintComponent());
        }
    }
}
