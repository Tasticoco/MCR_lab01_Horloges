package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class ChronoFrame extends JFrame {

    protected long time;
    ArrayList<ChronoPanel> panels;

    protected ChronoFrame(ArrayList<ChronoPanel> panels) {
        this.panels = panels;

        var dim = new Dimension(220 * panels.size(), 240);
        setSize(dim);
        setMinimumSize(dim);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout()); // Set layout to FlowLayout to arrange panels horizontally

        // Create and add panels
        for (ChronoPanel panel : panels) {
            panel.setPreferredSize(new Dimension(200, 200));
            add(panel); // Add the panel to the ChronoFrame
        }

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (ChronoPanel panel : panels) {
                    panel.detatch();
                }
                dispose();
            }
        });

        setVisible(true);
        revalidate();
        repaint();
    }
}