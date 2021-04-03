package concurrencyframeworks.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WeighAnimalsTask extends RecursiveTask<Double> {

    private final int start;
    private final int end;
    private final double[] weights;

    public WeighAnimalsTask(double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {
        System.out.println(Thread.currentThread().getName());
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = new Random().nextInt(100);
                sum += weights[i];
            }
            return sum;
        } else {
            int middle = start + ((end - start) / 2);
            WeighAnimalsTask leftTask = new WeighAnimalsTask(weights, start, middle);
            WeighAnimalsTask rightTask = new WeighAnimalsTask(weights, middle, end);

            leftTask.fork();

            return rightTask.compute() + leftTask.join();
        }
    }

    public static void main(String[] args) {
        int start = 0;
        int end = 20;
        double[] weights = new double[end];
        WeighAnimalsTask task = new WeighAnimalsTask(weights, start, end);

        ForkJoinPool pool = new ForkJoinPool();
        Double result = pool.invoke(task);

        System.out.println("Total weight: " + result);

        //double check the result
        double sum = Arrays.stream(weights).sum();
        System.out.println(sum);
    }
}
