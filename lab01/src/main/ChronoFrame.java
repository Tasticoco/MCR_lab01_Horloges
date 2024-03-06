package main;

import javax.swing.*;
import main.observersModel.Observer;

import java.awt.*;

abstract public class ChronoFrame extends JFrame implements Observer {

    Chrono chrono;
    protected JPanel panel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(graphImage() != null) {
                g.drawImage(graphImage(), 0, 0, this); // Dessine l'image au coordonnées x=0, y=0.
            }
        }

    };

    protected JLabel panelText = new JLabel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(graphString() != null) {
                // Get the FontMetrics
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                // Determine the X coordinate for the text
                int x = (getWidth() - metrics.stringWidth(graphString())) / 2;
                // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                // Draw the String
                g.drawString(graphString(), x, y);
            }
        }

    };


    protected ChronoFrame(Chrono chrono){
        this.chrono = chrono;
        chrono.attach(this);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);

        // Add panelText to the center of the frame
        panelText.setHorizontalAlignment(JLabel.CENTER);
        panelText.setVerticalAlignment(JLabel.CENTER);
        add(panelText, BorderLayout.CENTER);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chrono.detach(ChronoFrame.this);
                dispose();
            }
        });

        setVisible(true);
        revalidate();
        repaint();
    }

    @Override
    public void update() {
        updateDisplay(chrono.getSeconds());
    }

    abstract public void updateDisplay(long time);

    abstract public Image graphImage();

    public String graphString(){
        return chrono.toString();
    }
}