package main;

import main.displayChronoStyle.*;
import main.displayChronoStyle.DialType.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class ControlPanel extends JPanel {
    private final Chrono chrono;
    private final JButton start = new JButton("Démarrer");
    private final JButton stop = new JButton("Arrêter");
    private final JButton reset = new JButton("Réinitialiser");
    protected JButton roman = new JButton("Cadran romain");
    protected JButton arab = new JButton("Cadran arabe");
    protected JButton num = new JButton("Numérique");

    public ControlPanel(Chrono c) {

        chrono = c;
        drawComponents();
        setVisible(true);
    }

    protected void drawComponents() {
        setLayout(new FlowLayout());
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
