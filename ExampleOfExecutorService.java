import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by smit on 25/1/22.
 */
public class ExampleOfExecutorService {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                System.out.println("Executor Service is running");
            }
        });

        executorService.shutdown();

    }

}
