/**
 * Created by smit on 18/1/22.
 */

//Stop a Java Thread

public class ThreadExample7 {

    public static class StoppableRunnable implements Runnable
    {
        private boolean stopRequested = false;

        public synchronized void requestStop()
        {
            this.stopRequested = true;
        }

        public synchronized boolean isStopRequested()
        {
            return this.stopRequested;
        }

        private void sleep(long millis)
        {
            try
            {
                Thread.sleep(millis);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("Stoppable Runnable Running");

            while (!isStopRequested())
            {
                sleep(2000);
                System.out.println("...");
            }

            System.out.println("StoppableRunnable Stopped");
        }
    }

    public static void main(String[] args) {

        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread thread = new Thread(stoppableRunnable, "The Thread");

        thread.start();

        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
        System.out.println("stop requested");
    }
}
