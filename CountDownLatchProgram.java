import java.util.concurrent.CountDownLatch;

/**
 * Created by smit on 25/1/22.
 */
public class CountDownLatchProgram {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(4);

        Worker worker1 = new Worker(5000, countDownLatch , "Worker 1");
        Worker worker2 = new Worker(2000, countDownLatch , "Worker 2");
        Worker worker3 = new Worker(3000, countDownLatch , "Worker 3");
        Worker worker4 = new Worker(4000, countDownLatch , "Worker 4");

        worker1.start();
        worker2.start();
        worker3.start();
        worker4.start();

        countDownLatch.await();
        System.out.println("Smit");
    }
}

class Worker extends Thread
{
    private int delay;
    private CountDownLatch latch;

    public Worker(int delay, CountDownLatch latch, String name)
    {
        super(name);
        this.delay = delay;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);

            System.out.println(Thread.currentThread().getName()+" finished ");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}