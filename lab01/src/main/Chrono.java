package main;

import main.observersModel.Subject;
import java.util.Timer;
import java.util.TimerTask;

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
                if(isRunning) seconds++;
                System.out.println(seconds);
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
        }
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
