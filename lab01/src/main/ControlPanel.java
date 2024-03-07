package main;

import main.displayChronoStyle.*;
import main.displayChronoStyle.DialType.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class ControlPanel extends JPanel {
    Chrono chrono;
    JButton start = new JButton("Démarrer");
    JButton stop = new JButton("Arrêter");
    JButton reset = new JButton("Réinitialiser");
    JButton roman = new JButton("Cadran romain");
    JButton arab = new JButton("Cadran arabe");
    JButton num = new JButton("Numérique");

    public ControlPanel(Chrono c) {

        chrono = c;
        drawComponents();
        setVisible(true);
    }

    protected void drawComponents(){
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
        stop.addActionListener(e -> chrono.pause());
        reset.addActionListener(e -> chrono.reset());
        roman.addActionListener(e -> new ChronoFrame(new ArrayList<>(Collections.singletonList(new Roman(chrono)))));
        arab.addActionListener(e -> new ChronoFrame(new ArrayList<>(Collections.singletonList(new Arabic(chrono)))));
        num.addActionListener(e -> new ChronoFrame(new ArrayList<>(Collections.singletonList(new Numeric(chrono)))));
    }
}
