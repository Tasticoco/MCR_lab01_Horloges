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
    Timer timer;
    long seconds = 0;
    boolean isRunning = false;

    static int totId = 0;
    int id;

    public Chrono() {
        timer = new Timer();
        id = totId++;
    }

    public String toString() {
        return "Chrono #" + id;
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isRunning) {
                    seconds++;
                    if (seconds >= 86400) seconds = 0;
                }
                obsNotify();
            }
        }, 1000, 1000);
    }

    public void pause() {
        if (isRunning) {
            isRunning = false;
            timer.cancel();
            timer = new Timer(); // Create a new Timer for when we resume
            obsNotify();
        } else start(); // It's especially used for when we click on the clock itself to pause it
    }

    public void stop() {
        timer.cancel();
        isRunning = false;
        obsNotify();
    }

    public void reset() {
        seconds = 0;
        obsNotify();
    }

    public long getSeconds() {
        return seconds;
    }
}
