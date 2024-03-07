package main;

import main.displayChronoStyle.DialType.Arabic;
import main.displayChronoStyle.DialType.Roman;
import main.displayChronoStyle.Numeric;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class ControlPanelEnd extends ControlPanel{

    ArrayList<Chrono> chronos;
    public ControlPanelEnd(ArrayList<Chrono> chronos) {
        super(null);
        this.chronos = chronos;
    }

    @Override
    protected void drawComponents(){
        setLayout(new FlowLayout(FlowLayout.RIGHT, 7, 7));
        add(new JLabel("Tous les chronos"));
        //Adding buttons to the panel
        add(roman);
        add(arab);
        add(num);

        roman.addActionListener(e -> {
            ArrayList<ChronoPanel> romans = new ArrayList<>();
            for (Chrono chrono : chronos) {
                romans.add(new Roman(chrono));
            }
            new ChronoFrame(romans);
        });

        arab.addActionListener(e -> {
            ArrayList<ChronoPanel> arabics = new ArrayList<>();
            for (Chrono chrono : chronos) {
                arabics.add(new Arabic(chrono));
            }
            new ChronoFrame(arabics);
        });

        num.addActionListener(e -> {
            ArrayList<ChronoPanel> numerics = new ArrayList<>();
            for (Chrono chrono : chronos) {
                numerics.add(new Numeric(chrono));
            }
            new ChronoFrame(numerics);
        }
        );
    }

}
