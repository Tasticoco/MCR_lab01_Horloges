package main;

import javax.swing.*;
import main.observersModel.Observer;

public class ChronoFrame extends JFrame implements Observer {

    Chrono chrono;

    protected ChronoFrame(Chrono chrono){
        this.chrono = chrono;
        chrono.attach(this);
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                chrono.detach(ChronoFrame.this);
                System.exit(0);
            }
        });

    }

    @Override
    public void update() {

    }
}