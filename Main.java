import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by smit on 25/1/22.
 */

class CountDivisorsUsingExecutor {

    private final static int MAX = 10000;

    private static class Result {
        int maxDivisorFromTask;  // Maximum number of divisors found.
        int intWithMaxFromTask;  // Which integer gave that maximum number.
        Result(int maxDivisors, int whichInt) {
            maxDivisorFromTask = maxDivisors;
            intWithMaxFromTask = whichInt;
        }
    }

    private static class Task implements Callable<Result> {
        int min, max; // Start and end of the range of integers for this task.
        Task(int min, int max) {
            this.min = min;
            this.max = max;
        }
        public Result call() {
            int maxDivisors = 0;
            int whichInt = 0;
            for (int i = min; i < max; i++) {
                int divisors = countDivisors(i);
                if (divisors > maxDivisors) {
                    maxDivisors = divisors;
                    whichInt = i;
                }
            }
            return new Result(maxDivisors,whichInt);
        }
    }

    private static void countDivisorsWithExecutor(int numberOfThreads) {

        System.out.println("\nCounting divisors using " +
                    numberOfThreads + " threads...");

        /* Create the ExecutorService and an ArrayList to hold the Futures. */

        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        ArrayList<Future<Result>> results = new ArrayList<>();

        /* Create the tasks and add them to the executor.  Each
         * task consists of a range of 1000 integers, so the number of
         * tasks is (MAX+999)/1000.  (The "+999"  gives the correct number
         * of tasks when MAX is not an exact multiple of 1000.  The last
         * task in that case will consist of the last (MAX%1000)) ints. */

        int numberOfTasks = (MAX + 999) / 1000;
        for (int i = 0; i < numberOfTasks; i++) {
            int start = i*1000 + 1;
            int end = (i+1)*1000;
            if (end > MAX)
                end = MAX;
            //System.out.println(start + " " + end);  // for testing
            Future<Result> res = executor.submit( new Task(start,end) );
            results.add(res);
        }

        /* As the executor executes the tasks, results become available
         * in the Futures that are stored in the ArrayList.  Get the
         * results and combine them to produce the final output.
         * Note that each call to res.get() blocks, if necessary,
         * until the result is available. */

        int maxDivisorCount = 0;         // Over maximum found by any task.
        int intWithMaxDivisorCount = 0;  // Which integer gave that maximum?
        for (Future<Result> res : results) {
            try {
                Result result = res.get();
                if (result.maxDivisorFromTask > maxDivisorCount) { // new maximum.
                    maxDivisorCount = result.maxDivisorFromTask;
                    intWithMaxDivisorCount = result.intWithMaxFromTask;
                }
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred! Error:");
                System.out.println(e);
                System.exit(1);
            }
        }

        /* Report the results. */

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("\nThe largest number of divisors " +
                "for numbers between 1 and " + MAX + " is " + maxDivisorCount);
        System.out.println("An integer with that many divisors is " +
                intWithMaxDivisorCount);
        System.out.println("Total elapsed time:  " +
                (elapsedTime/1000.0) + " seconds.\n");

        executor.shutdown(); // Needed since otherwise the threads in the
        // ExecutorService will stop the Java Virtual
        // Machine from shutting down normally.

    } // end countDivisorsWithExecutor()


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int numberOfThreads = 0;
        while (numberOfThreads < 1 || numberOfThreads > 10) {
            System.out.print("How many threads do you want to use  (1 to 10) ?  ");
            numberOfThreads = s.nextInt();
            if (numberOfThreads < 1 || numberOfThreads > 10)
                System.out.println("Please enter a number from 1 to 10 !");
        }
        countDivisorsWithExecutor(numberOfThreads);
    }

    private static int countDivisors(int N) {
        int count = 0;
        for (int i = 1; i <= N ; i++) {
            if ( N % i == 0 )
                count ++;
        }
        return count;
    }

} // end CountDivisorsUsingExecutor
