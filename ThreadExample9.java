/**
 * Created by smit on 18/1/22.
 */

//Make a java Thread as a daemon thread

public class ThreadExample9 {

    public static void main(String[] args) {

        Runnable runnable = () -> {

            while (true)
            {
                sleep(1000);
                System.out.println("Running");
            }
        };

        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
        sleep(3100);
    }

    private static void sleep(int millis) {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
