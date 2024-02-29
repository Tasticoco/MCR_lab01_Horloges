package main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ControlFrame extends JFrame {

    LinkedList<Chrono> chronos = new LinkedList<>();
    public ControlFrame(int nbChrono){
        setTitle("Panneau de contrôle");
        setLayout(new GridLayout(nbChrono + 1, 1));
        for(int i = 0; i < nbChrono; i++){
            Chrono c = new Chrono();
            chronos.add(c);
            add(new ChronoPanel(c));
        }

        pack();
        setBackground(Color.GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        revalidate();
        repaint();
    }
}
