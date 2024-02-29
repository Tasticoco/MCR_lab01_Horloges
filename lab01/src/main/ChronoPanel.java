package main;

import main.displayChronoStyle.*;

import javax.swing.*;
import java.awt.*;

public class ChronoPanel extends JPanel {
    Chrono chrono;
    JButton start = new JButton("Démarrer");
    JButton stop = new JButton("Arrêter");
    JButton reset = new JButton("Réinitialiser");
    JButton roman = new JButton("Cadran romain");
    JButton arab = new JButton("Cadran arabe");
    JButton num = new JButton("Numérique");

    public ChronoPanel(Chrono c) {

        chrono = c;
        setLayout(new FlowLayout(7));
        add(new JLabel(chrono.toString()));
        //Adding buttons to the panel
        add(start);
        add(stop);
        add(reset);
        add(roman);
        add(arab);
        add(num);

        start.addActionListener(e -> chrono.start());
        stop.addActionListener(e -> chrono.stop());
        reset.addActionListener(e -> chrono.reset());
        roman.addActionListener(e -> new Dial(chrono));
        arab.addActionListener(e -> new Dial(chrono));
        num.addActionListener(e -> new Numeric(chrono));

        setVisible(true);
    }
}
