/**
 * Created by smit on 18/1/22.
 */

//Join a Thread - wait for the Java Thread to terminate
public class ThreadExample10 {

    public static void main(String[] args) {

        Runnable runnable = () ->
        {
            while (true)
            {
                sleep(1000);
                System.out.println("Running");
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

        try
        {
            thread.join();
        }

        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
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
