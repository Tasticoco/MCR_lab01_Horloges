package main.displayChronoStyle;

import main.Chrono;
import main.ChronoPanel;

import java.awt.*;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class Numeric extends ChronoPanel {

    public Numeric(Chrono chrono) {
        super(chrono);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g);
    }

    /**
     * Generates the String to be displayed on the panel
     *
     * @return the string to be displayed
     */
    @Override
    public String graphString() {
        long time = chrono.getSeconds();
        return super.graphString() + String.format(" %02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
    }

}
