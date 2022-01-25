import java.util.concurrent.CountDownLatch;

/**
 * Created by smit on 25/1/22.
 */
//This program is to implement the CountDownLatch concept

public class Tester {
    public static void main(String args[]) {
        //Set the counter to 2 being two applications
        CountDownLatch countDownLatch = new CountDownLatch(4);

        Thread app1 = new Thread(new Application("App1",  countDownLatch));
        Thread app2 = new Thread(new Application("App2",  countDownLatch));
        Thread app3 = new Thread(new Application("App3",  countDownLatch));
        Thread app4 = new Thread(new Application("App4",  countDownLatch));

        app1.start();
        app2.start();
        app3.start();
        app4.start();

        try {
            //wait until countDownLatch reduces to 0.
            countDownLatch.await();

            System.out.println("All applications are up and running.");
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Application implements Runnable {
    private String name;
    private CountDownLatch countDownLatch;

    public Application(String name, CountDownLatch countDownLatch) {
        this.name = name;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        try {
            System.out.println(name + " started. ");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println( name + " is Up and running.");
        //reduce the count by 1
        countDownLatch.countDown();
    }
}