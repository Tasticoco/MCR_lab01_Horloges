package main;

import main.observersModel.Subject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class Chrono extends Subject {
    private Timer timer;
    private long seconds = 0;
    private boolean isRunning = false;
    private static int totId = 0;
    private final int id;

    public Chrono() {
        timer = new Timer();
        id = totId++;
    }

    /**
     * @return the string to be displayed
     */
    @Override
    public String toString() {
        return "Chrono #" + id;
    }

    /**
     * Starts the timer
     */
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (isRunning) {
                        seconds++;
                        if (seconds >= 86400) seconds = 0; //Reset the timer after 24 hours
                    }
                    obsNotify();
                }
            }, 1000, 1000); //Started at 1000ms, so it doesn't count a second as soon as we start it
        }
    }

    /**
     * Pauses the timer
     */
    public void pause() {
        if (isRunning) {
            isRunning = false;
            timer.cancel(); //Destroy the timer
            timer = new Timer(); // Create a new Timer for when we resume, only way to effectively "pause" it
            obsNotify();
        }
    }

    /**
     * Resets the timer
     */
    public void reset() {
        seconds = 0;
        obsNotify();
    }

    /**
     * @return the seconds
     */
    public long getSeconds() {
        return seconds;
    }

    /**
     * @return the running state
     */
    public boolean isRunning() {
        return isRunning;
    }
}
