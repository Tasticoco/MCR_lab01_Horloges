package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;

abstract public class Dial extends ChronoFrame {



    public Dial(Chrono chrono){
        super(chrono);
    }

    @Override
    public void update() {
        System.out.println("Dial");
    }

    @Override
    public void updateDisplay(long time) {

    }
}
