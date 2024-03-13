package main;

import main.observersModel.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
abstract public class ChronoPanel extends JPanel implements Observer {
    protected Chrono chrono;

    protected ChronoPanel(Chrono chrono) {
        this.chrono = chrono;
        chrono.attach(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Start or pause the chrono when the panel is clicked
                if (chrono.isRunning())
                    chrono.pause();
                else
                    chrono.start();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Repaint the panel when the observer is updated
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * Draw the text on the panel
     *
     * @param g the graphics object
     */
    protected void drawText(Graphics g) {

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int x = (getWidth() - metrics.stringWidth(graphString())) / 2;
        int y = verticalPlacement(metrics);

        g.drawString(graphString(), x, y);
    }

    /**
     * Determine the vertical placement of the text
     *
     * @param metrics the font metrics
     * @return the vertical placement
     */
    protected int verticalPlacement(FontMetrics metrics) {
        return (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
    }

    /**
     * Generates the String to be displayed on the panel
     *
     * @return the string to be displayed
     */
    public String graphString() {
        return chrono.toString();
    }

    /**
     * Detach the observer from the subject
     */
    public void detatch() {
        chrono.detach(this);
    }
}
