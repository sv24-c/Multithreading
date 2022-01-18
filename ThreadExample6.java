/**
 * Created by smit on 18/1/22.
 */
public class ThreadExample6 {

    public static void main(String[] args) {

        Runnable runnable = () -> {

            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " running");

            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

    }
}
