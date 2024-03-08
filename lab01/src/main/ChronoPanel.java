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
                // Handle the mouse click event here
                chrono.pause();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void update() {
        repaint();
    }

    protected void drawText(Graphics g) {

        FontMetrics metrics = g.getFontMetrics(g.getFont());
        // Determine the X coordinate for the text
        int x = (getWidth() - metrics.stringWidth(graphString())) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
        // Draw the String
        g.drawString(graphString(), x, y);
    }

    public String graphString() {
        return chrono.toString();
    }

    public void detatch() {
        chrono.detach(this);
    }
}
