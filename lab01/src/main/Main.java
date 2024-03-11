package main;

/**
 * @author Arthur Junod
 * @author Edwin Haeffner
 * @version 1.0
 * @since 2024-02-22
 */
public class Main {

    public static void main(String[] args) {

        int nbChrono;
        //If no argument is given, we create a control frame with 3 chrono
        if (args.length == 0) {
            nbChrono = 3;
            System.out.println("No argument given, creating 3 chronos");
        } else {
            nbChrono = Integer.parseInt(args[0]);
            System.out.println("Creating " + nbChrono + " chronos");
        }

        new ControlFrame(nbChrono);
    }

}




