package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class Dial extends ChronoFrame {

    final int LENGTH_HOUR = 50;
    final int LENGTH_MINUTE = 70;
    final int LENGTH_SECOND = 90;

    public Dial(Chrono chrono){
        super(chrono);
    }

    @Override
    public Image graphImage() throws IOException {

        Image img = ImageIO.read(new File(path()));

        //If the image is not loaded, tells it to the user
        if (img.getWidth(null) == -1 || img.getHeight(null) == -1) {
            System.out.println("Error loading file: \"" + new File(path()).getAbsolutePath() + "\"");
        }
        return  img;
    }

    protected abstract String path();

    @Override
    protected BufferedImage drawHands() {
        // Create a new BufferedImage and get its Graphics2D object
        BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Get the current time
        long hours = time / 3600;
        long minutes = (time % 3600) / 60;
        long seconds = time % 60;

        // Calculate the angles for the hands
        double hourAngle = Math.toRadians((hours % 12 + minutes / 60.0) * 30 - 90);
        double minuteAngle = Math.toRadians(minutes * 6 - 90);
        double secondAngle = Math.toRadians(seconds * 6 - 90);

        int middle = super.getWidth() / 2;
        // Calculate the end points for the hands
        int hourX = (int) (middle + LENGTH_HOUR * Math.cos(hourAngle));
        int hourY = (int) (middle + LENGTH_HOUR * Math.sin(hourAngle));
        int minuteX = (int) (middle + LENGTH_MINUTE * Math.cos(minuteAngle));
        int minuteY = (int) (middle + LENGTH_MINUTE * Math.sin(minuteAngle));
        int secondX = (int) (middle + LENGTH_SECOND * Math.cos(secondAngle));
        int secondY = (int) (middle + LENGTH_SECOND * Math.sin(secondAngle));

        // Draw the hands
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(middle, middle, hourX, hourY);
        g2d.setColor(minuteColor());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(middle, middle, minuteX, minuteY);
        g2d.setColor(secondColor());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(middle, middle, secondX, secondY);

        return image;
    }

    protected abstract Color secondColor();
    protected abstract Color minuteColor();

}
