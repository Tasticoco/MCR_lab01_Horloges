package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;

public class Dial extends ChronoFrame {

    public Dial(Chrono chrono){
        super(chrono);
    }

    @Override
    public void update() {
        System.out.println("Dial");
    }
}
