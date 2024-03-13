package main;

import main.displayChronoStyle.DialType.Arabic;
import main.displayChronoStyle.DialType.Roman;
import main.displayChronoStyle.Numeric;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class ControlPanelEnd extends ControlPanel {

    private final ArrayList<Chrono> chronos;

    public ControlPanelEnd(ArrayList<Chrono> chronos) {
        super(null);
        this.chronos = chronos;
    }

    @Override
    protected void drawComponents() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 7, 7));
        add(new JLabel("Tous les chronos"));
        //Adding buttons to the panel
        add(roman);
        add(arab);
        add(num);

        roman.addActionListener(e -> createAndShowChronoFrame(Roman::new));
        arab.addActionListener(e -> createAndShowChronoFrame(Arabic::new));
        num.addActionListener(e -> createAndShowChronoFrame(Numeric::new));
    }

    /**
     * Create and show a ChronoFrame with the given constructor
     *
     * @param constructor the constructor to use to create the ChronoPanel
     */
    private void createAndShowChronoFrame(Function<Chrono, ChronoPanel> constructor) {
        ArrayList<ChronoPanel> panels = new ArrayList<>();
        for (Chrono chrono : chronos) {
            panels.add(constructor.apply(chrono));
        }
        new ChronoFrame(panels);
    }
}
