import static java.lang.Thread.sleep;

public class Main {
    public static void test() {
        Chrono chrono = new Chrono();
        ChronoFrame panel = new ChronoFrame();
        //chrono.addObserver(panel);
        chrono.start();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        chrono.stop();
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        test();
    }


    

}




