package concurrencyframeworks.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WeighAnimalsAction extends RecursiveAction {

    private final int start;
    private final int end;
    private final double[] weights;

    public WeighAnimalsAction(double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected void compute() {

        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = new Random().nextInt(100);
                String message = String.format("Animal %d weighted", i);
                System.out.println(message);
            }
        } else {
            int middle = start + ((end - start) / 2);
            invokeAll(new WeighAnimalsAction(weights, start, middle), new WeighAnimalsAction(weights, middle, end));
        }
    }

    public static void main(String[] args) {
        int start = 0;
        int end = 10;
        double[] weights = new double[end];
        WeighAnimalsAction task = new WeighAnimalsAction(weights, start, end);

        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        System.out.println("********");
        System.out.println("Weights: ");
        Arrays.stream(weights)
                .forEach(System.out::println);
    }
}
