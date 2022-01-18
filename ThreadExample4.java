/**
 * Created by smit on 18/1/22.
 */

//Implement Runnable interface with an anonymous class
public class ThreadExample4 {

    public static void main(String[] args)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable running");
                System.out.println("Runnable finished");

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
