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
    ArrayList<ChronoPanel> panels;

    protected ChronoFrame(ArrayList<ChronoPanel> panels) {

        this.panels = panels;
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Must use a window listener to close the frame to ensure the panels are detached
        setLayout(new FlowLayout()); // Set layout to FlowLayout to arrange panels horizontally

        // Create and add panels
        for (ChronoPanel panel : panels) {
            panel.setPreferredSize(new Dimension(200, 200));
            add(panel); // Add the panel to the ChronoFrame
        }

        // Detach all panels when the frame is closed
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                for (ChronoPanel panel : panels) {
                    panel.detatch();
                }
                dispose();
            }
        });

        pack();
        setResizable(false);
        setVisible(true);
        revalidate();
        repaint();
    }
}