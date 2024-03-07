package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;
import main.ChronoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Numeric extends ChronoPanel {

    public Numeric(Chrono chrono) {
        super(chrono);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g);
    }

    @Override
    public String graphString() {
        long time = chrono.getSeconds();
        return super.graphString() + String.format(" %02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
    }

}
