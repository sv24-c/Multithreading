/**
 * Created by smit on 18/1/22.
 */

//Create a subclass of java the Thread class
public class ThreadExample2 {

    public static class MyThread extends Thread
    {
        public void run()
        {
            System.out.println("MyThread is running.");
            System.out.println("MyThread finished");

        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
         myThread.start();
    }
}
