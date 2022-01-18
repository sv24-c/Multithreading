/**
 * Created by smit on 18/1/22.
 */
//Implement the Runnable interface with a Java Lambda Functionter..

public class ThreadExample5 {

    public static void main(String[] args) {
        Runnable runnable = () -> {

            System.out.println("Lambda running");
            System.out.println("Lambda finished");

        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
