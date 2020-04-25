package executors;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SingleThreadScheduledExecutorExamples {

    public static void main(String[] args) throws InterruptedException {
//        usingSchedule();
        usingScheduleAtFixedRate();
//        usingScheduleWithFixedDelay();
    }

    private static void usingSchedule() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> scheduledFuture = service.schedule(() -> System.out.println("done"), 2000, TimeUnit.MILLISECONDS);
        service.shutdown();
    }

    private static void usingScheduleAtFixedRate() throws InterruptedException {
        AtomicInteger iteration = new AtomicInteger(0);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            Random random = new Random();
            try {
                if (iteration.get() == 3) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("iteration: " + iteration.getAndIncrement());
        };
        service.scheduleAtFixedRate(task, 0, 100, TimeUnit.MILLISECONDS);
        while (iteration.get() < 10) {
            Thread.sleep(2000);
        }
        System.out.println("shutdown service");
        service.shutdown();
    }

    private static void usingScheduleWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        AtomicLong atomicLong = new AtomicLong(System.currentTimeMillis());

        AtomicInteger iteration = new AtomicInteger(1);
        Runnable task = () -> {
            try {
                long start = System.currentTimeMillis();
                System.out.println("Starting after: " + (start - atomicLong.get()) / 1000 + " seconds");

                Thread.sleep(1000);
                System.out.println("iteration: " + iteration.getAndIncrement());
                System.out.println("Process took: " + (System.currentTimeMillis() - start) / 1000 + " seconds");
                atomicLong.set(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);

        while (iteration.get() < 3) {
            Thread.sleep(1000);
        }
        System.out.println("shutdown service");
        service.shutdown();
    }
}
