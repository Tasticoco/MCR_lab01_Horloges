package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Numeric extends ChronoFrame {

    public Numeric(Chrono chrono) {
        super(chrono);
    }

    @Override
    public Image graphImage() {
        return null;
    }

    @Override
    protected BufferedImage drawHands() {
        return null;
    }

    @Override
    public String graphString() {
        return super.graphString() + String.format(" %02d:%02d:%02d", time / 3600, (time % 3600) / 60, time % 60);
    }

}
