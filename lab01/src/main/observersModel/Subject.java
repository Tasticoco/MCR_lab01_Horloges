package main.observersModel;

import java.util.LinkedList;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public abstract class Subject {

    LinkedList<Observer> observers = new LinkedList<>();

    public void attach(Observer o) {
        if (o != null) {
            observers.add(o);
        }
    }

    public void detach(Observer o) {
        if (o != null) {
            observers.remove(o);
        }
    }

    public void obsNotify() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
