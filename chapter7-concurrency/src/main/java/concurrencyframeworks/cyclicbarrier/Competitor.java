package concurrencyframeworks.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Competitor extends Thread {

    private String destination;

    public Competitor(String destination) {
        this.destination = destination;
    }

    static CyclicBarrier barrier;
    static Runnable announcer = () -> {
        System.out.println("Last to arrive is: " + Thread.currentThread().getName());
        System.out.println("All competitors have arrived at the finish line. Letting them all cross");
    };

    public void run() {
        try {
            System.out.println("Competitor thread [" + Thread.currentThread().getName() + "] approaching the finish line at destination: " + destination);
            announceArrivalAtTheFinishLine(barrier);
            System.out.println("Competitor thread [" + Thread.currentThread().getName() + "] crossed the finish line");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void announceArrivalAtTheFinishLine(CyclicBarrier barrier) throws BrokenBarrierException, InterruptedException {
        System.out.println("Competitor thread [" + Thread.currentThread().getName() + "] arrived at the finish line");
        barrier.await();
    }


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        barrier = new CyclicBarrier(3, announcer);

        Competitor firstCompetitor = new Competitor("First city");
        firstCompetitor.start();

        Competitor secondCompetitor = new Competitor("Second city");
        secondCompetitor.start();

        // The current main thread can also be a competitor
        Thread.sleep(1000);
        System.out.println("Competitor thread [" + Thread.currentThread().getName() + "] approaching the finish line at destination: " + "[Application exit]");
        announceArrivalAtTheFinishLine(barrier);
        System.out.println("Competitor thread [" + Thread.currentThread().getName() + "] crossed the finish line");
    }
}

