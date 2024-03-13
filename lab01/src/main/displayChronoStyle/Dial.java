package main.displayChronoStyle;

import main.Chrono;
import main.ChronoPanel;
import main.utilities.ImageCacheManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
abstract public class Dial extends ChronoPanel {

    private final static int LENGTH_HOUR = 30;
    private final static int LENGTH_MINUTE = 50;
    private final static int LENGTH_SECOND = 80;
    private final static int IMG_DIMENSION = 200;


    public Dial(Chrono chrono) {
        super(chrono);
    }

    /**
     * Draws what's inside the panel
     *
     * @param g the graphics
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawImage(g);
        drawText(g);
        g.drawImage(drawHands(), 0, 0, this);
    }

    /**
     * Function used to read the image of the dial
     *
     * @return the image of the dial
     * @throws IOException in case the file is not found
     */
    public Image graphImage() throws IOException {

        Image img = ImageIO.read(new File(path()));

        //If the image is not loaded, tells it to the user
        if (img.getWidth(null) == -1 || img.getHeight(null) == -1) {
            System.out.println("Error loading file: \"" + new File(path()).getAbsolutePath() + "\"");
        }
        return img;
    }

    /**
     * Function used to place the text slightly below the center of the dial
     *
     * @param metrics the font metrics
     * @return the vertical placement of the text
     */
    @Override
    protected int verticalPlacement(FontMetrics metrics) {
        return super.verticalPlacement(metrics) + 10;
    }

    /**
     * Function used to draw the hands of the dial
     *
     * @return the image of the hands of the dial
     */
    protected BufferedImage drawHands() {

        long time = chrono.getSeconds();

        BufferedImage image = new BufferedImage(IMG_DIMENSION, IMG_DIMENSION, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Map the time to hours, minutes and seconds
        long hours = time / 3600;
        long minutes = (time % 3600) / 60;
        long seconds = time % 60;

        // Calculate the angles for the hands, hour and minute are smooth
        double hourAngle = Math.toRadians(((hours % 12) + minutes / 60.0 + seconds / 3600.0) * 30 - 90);
        double minuteAngle = Math.toRadians((minutes + seconds / 60.0) * 6 - 90);
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
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(secondColor());
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(middle, middle, secondX, secondY);
        g2d.setColor(minuteColor());
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(middle, middle, minuteX, minuteY);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(middle, middle, hourX, hourY);

        return image;
    }

    /**
     * Converts a given Image into a BufferedImage and stores it in the object
     *
     * @param img The Image to be converted
     */
    public void addImageToCache(Image img) {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // add the buffered image to cache
        ImageCacheManager.addImage(key(), bimage);
    }

    /**
     * @return a key based on the class name
     */
    private String key() {
        return getClass().getSimpleName();
    }

    /**
     * Function used to draw the image of the dial
     *
     * @param g the graphics
     */
    private void drawImage(Graphics g) {
        // If the image is not cached, cache it
        if (!ImageCacheManager.isImageCached(key())) {
            try {
                Image img = graphImage();
                if (img != null) {
                    img = img.getScaledInstance(IMG_DIMENSION, IMG_DIMENSION, Image.SCALE_SMOOTH);
                    addImageToCache(img);
                } else {
                    System.out.println("graphImage() returned null");
                }
            } catch (IOException e) {
                System.out.println("IOException occurred in graphImage(): " + e.getMessage());
            }
        } else {
            //Get the image from the cache
            BufferedImage cachedImage = ImageCacheManager.getImage(key());

            //Draw the image centered
            int x = (getWidth() - cachedImage.getWidth(null)) / 2;
            int y = (getHeight() - cachedImage.getHeight(null)) / 2;
            g.drawImage(cachedImage, x, y, this);
        }
    }

    /**
     * Function used to get the color of the second hand, to be implemented by the subclasses
     *
     * @return the color of the second hand
     */
    protected abstract Color secondColor();

    /**
     * Function used to get the color of the minute hand, to be implemented by the subclasses
     *
     * @return the color of the second hand
     */
    protected abstract Color minuteColor();

    /**
     * Function used to get the path of the image of the dial
     *
     * @return the path of the image of the dial
     */
    protected abstract String path();

}
