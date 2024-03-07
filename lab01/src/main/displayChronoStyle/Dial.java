package main.displayChronoStyle;

import main.Chrono;
import main.ChronoFrame;
import main.ChronoPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

abstract public class Dial extends ChronoPanel {

    final int LENGTH_HOUR = 50;
    final int LENGTH_MINUTE = 70;
    final int LENGTH_SECOND = 90;
    final int IMG_DIMENSION = 200;
    BufferedImage cachedImage = null;


    public Dial(Chrono chrono){
        super(chrono);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawImage(g);
        drawText(g);
        g.drawImage(drawHands(), 0, 0, this);
    }


    public Image graphImage() throws IOException {

        Image img = ImageIO.read(new File(path()));

        //If the image is not loaded, tells it to the user
        if (img.getWidth(null) == -1 || img.getHeight(null) == -1) {
            System.out.println("Error loading file: \"" + new File(path()).getAbsolutePath() + "\"");
        }
        return  img;
    }

    protected abstract String path();


    protected BufferedImage drawHands() {

        long time = chrono.getSeconds();
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

    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public void addImageToCache(Image img)
    {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // add the buffered image to cache
        cachedImage = bimage;

    }

    private void drawImage(Graphics g){
        if(cachedImage == null){
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
            int x = (getWidth() - cachedImage.getWidth(null)) / 2;
            int y = (getHeight() - cachedImage.getHeight(null)) / 2;
            g.drawImage(cachedImage,x,y,this);
        }
    }

    protected abstract Color secondColor();
    protected abstract Color minuteColor();

}
