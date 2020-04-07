package concurrencyframeworks.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LionPenManager {

    private void removeAnimals() {
        String message = String.format("Worker [%s] removing animals", Thread.currentThread().getName());
        System.out.println(message);
    }

    private void cleanPen() {
        String message = String.format("Worker [%s] cleaning pen", Thread.currentThread().getName());
        System.out.println(message);
    }

    private void addAnimals() {
        String message = String.format("Worker [%s] adding animals", Thread.currentThread().getName());
        System.out.println(message);
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int noOfWorkers = 4;
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(4);

            LionPenManager manager = new LionPenManager();
            CyclicBarrier barrier1 = new CyclicBarrier(noOfWorkers);
            CyclicBarrier barrier2 = new CyclicBarrier(noOfWorkers, () -> System.out.println("Pen cleaned"));

            for (int i = 0; i < noOfWorkers; i++) {
                service.submit(() -> manager.performTask(barrier1, barrier2));
            }

        } finally {
            if (service != null) service.shutdown();
        }
    }
}
