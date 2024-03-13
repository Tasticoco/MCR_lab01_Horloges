package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class ControlFrame extends JFrame {

    public ControlFrame(int nbChrono) {
        setTitle("Panneau de contrôle");

        //Adding the control panel for each chrono
        setLayout(new GridLayout(nbChrono + 1, 1));
        ArrayList<Chrono> chronos = new ArrayList<>();
        for (int i = 0; i < nbChrono; i++) {
            Chrono c = new Chrono();
            chronos.add(c);
            add(new ControlPanel(c));
        }
        //Adding the last panel
        add(new ControlPanelEnd(chronos));

        pack();
        setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        revalidate();
        repaint();
    }
}
