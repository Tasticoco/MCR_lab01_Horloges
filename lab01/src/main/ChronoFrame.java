package main;

import javax.swing.*;
import main.observersModel.Observer;

import java.awt.*;

abstract public class ChronoFrame extends JFrame implements Observer {

    Chrono chrono;
    JPanel panel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(graphImage() != null) {
                g.drawImage(graphImage(), 0, 0, this); // Dessine l'image au coordonnées x=0, y=0.
            }
            if(graphString() != null) {
                g.drawString(graphString(), 0, 100);
            }
        }

    };



    protected ChronoFrame(Chrono chrono){
        this.chrono = chrono;
        chrono.attach(this);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        add(panel);
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