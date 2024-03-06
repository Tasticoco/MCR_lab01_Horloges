package main;

import javax.swing.*;
import main.observersModel.Observer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract public class ChronoFrame extends JFrame implements Observer {

    protected long time;
    Chrono chrono;
    protected JPanel panel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

                try {
                    Image img = graphImage();
                    if (img != null) {
                        img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                        int x = (getWidth() - img.getWidth(null)) / 2;
                        int y = (getHeight() - img.getHeight(null)) / 2;
                        g.drawImage(img, x, y, this);
                    } else {
                        System.out.println("graphImage() returned null");
                    }
                } catch (IOException e) {
                    System.out.println("IOException occurred in graphImage(): " + e.getMessage());
                }


            if(graphString() != null) {
                // Get the FontMetrics
                FontMetrics metrics = g.getFontMetrics(g.getFont());
                // Determine the X coordinate for the text
                int x = (getWidth() - metrics.stringWidth(graphString())) / 2;
                // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                // Draw the String
                g.drawString(graphString(), x, y);
            }

            BufferedImage hands = drawHands();
            if(hands != null){
                g.drawImage(hands, 0, 0, this);
            }
        }

    };



    protected ChronoFrame(Chrono chrono){
        this.chrono = chrono;
        chrono.attach(this);
        var dim = new Dimension(220,240);
        setSize(dim);
        setMinimumSize(dim);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);
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
        panel.repaint();
    }

    public void updateDisplay(long time) {
        this.time = time;
    }

    abstract public Image graphImage() throws IOException;

    abstract protected BufferedImage drawHands();

    public String graphString(){
        return chrono.toString();
    }
}