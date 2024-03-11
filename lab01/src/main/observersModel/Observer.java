package main.observersModel;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public interface Observer {
    /**
     * Function called by the Subject to update the Observer
     */
    void update();

}
