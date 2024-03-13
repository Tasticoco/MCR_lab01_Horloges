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

    protected ChronoFrame(ArrayList<ChronoPanel> panels) {

        Dimension dimension = new Dimension(200, 200);
        setSize(dimension);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Must use a window listener to close the frame to ensure the panels are detached
        setLayout(new FlowLayout());

        // Create and add panels
        for (ChronoPanel panel : panels) {
            panel.setPreferredSize(dimension);
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
        // Using insets to ensure the minimum size is set correctly so the panels are not cut off
        Insets insets = getInsets();
        Dimension minDimension = new Dimension(dimension.width + insets.left + insets.right, dimension.height + insets.top + insets.bottom);
        setMinimumSize(minDimension);

        setVisible(true);
        revalidate();
        repaint();
    }
}