package main.observersModel;

import java.util.LinkedList;

public abstract class Subject {

    LinkedList<Observer> observers;
    public void attach(Observer o){
        if(o != null) {
            observers.add(o);
        }
    }
    public void detach(Observer o){
        if(o != null){
            observers.remove(o);
        }
    }

    public void obsNotify(){
        for(Observer o : observers){
            o.update();
        }
    }
}
