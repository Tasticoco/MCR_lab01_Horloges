package main.observersModel;

import java.util.LinkedList;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public abstract class Subject {

    private final LinkedList<Observer> observers = new LinkedList<>();

    /**
     * Attach an observer to the subject
     *
     * @param o the observer to attach
     */
    public void attach(Observer o) {
        if (o != null) {
            observers.add(o);
        }
    }

    /**
     * Detach an observer from the subject
     *
     * @param o the observer to detach
     */
    public void detach(Observer o) {
        if (o != null) {
            observers.remove(o);
        }
    }

    /**
     * Notify all the observers
     */
    public void obsNotify() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
