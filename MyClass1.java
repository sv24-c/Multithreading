import java.util.concurrent.CountDownLatch;

/**
 * Created by smit on 25/1/22.
 */
public class MyClass1 {

    public static void main(String[] args) throws InterruptedException {


        CountDownLatch countDownLatch = new CountDownLatch(3);

        Myclass2 m1 = new Myclass2("worker ", 1, countDownLatch);
        Myclass2 m2 = new Myclass2("worker ", 2, countDownLatch);
        Myclass2 m3 = new Myclass2("worker ", 3, countDownLatch);

         m1.start();
         m2.start();
         m3.start();

        countDownLatch.await();
    }
}

class Myclass2 extends Thread
{

    private String name;
    private int number;
    private CountDownLatch latch;

    Myclass2(String name, int number, CountDownLatch latch)
    {
        this.name = name;
        this.number = number;
        this.latch = latch;
    }
    @Override
    public void run() {

        try {

            System.out.println(name + number+ " is started ");
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Currently"  + name + number+" is running and Up");

    }
}