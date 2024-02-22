import ObserversModel.Subject;
import java.util.Timer;
import java.util.TimerTask;

public class Chrono extends Subject {
    Timer timer;
    long seconds = 0;
    boolean isRunning = false;

    public Chrono() {
        timer = new Timer();
    }
    public void start() {
        if (!isRunning) {
            isRunning = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    seconds++;
                    System.out.println(seconds);
                    obsNotify();
                }
            }, 1000, 1000);
        }
    }
    public void stop() {
        timer.cancel();
        isRunning = false;
        obsNotify();
    }
}
