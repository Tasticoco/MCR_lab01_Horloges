package main.displayChronoStyle.DialType;

import main.Chrono;
import main.displayChronoStyle.Dial;

import java.awt.*;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class Arabic extends Dial {
    public Arabic(Chrono chrono) {
        super(chrono);
    }

    protected String path() {
        return "resources/images/cadran_chiffres_arabes.jpg";
    }

    @Override
    protected Color secondColor() {
        return Color.decode("0xFF0000");
    }

    @Override
    protected Color minuteColor() {
        return Color.decode("0x0000FF");
    }

}
