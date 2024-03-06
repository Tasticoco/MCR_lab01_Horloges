package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;

import javax.swing.*;
import java.awt.*;

public class Numeric extends ChronoFrame {


    long time;

    public Numeric(Chrono chrono) {
        super(chrono);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setVisible(true);
//        add(label);
    }

    @Override
    public void updateDisplay(long time) {
        this.time = time;
    }

    @Override
    public Image graphImage() {
        return null;
    }

    @Override
    public String graphString() {
        return super.graphString() + String.format(" %02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
    }

    @Override
    public void update() {
        super.update();
        super.panelText.repaint();
    }

}
