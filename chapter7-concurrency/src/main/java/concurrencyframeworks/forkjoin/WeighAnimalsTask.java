package concurrencyframeworks.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WeighAnimalsTask extends RecursiveTask<Double> {

    private int start;
    private int end;
    private Double[] weights;

    public WeighAnimalsTask(Double[] weights, int start, int end) {
        this.start = start;
        this.end = end;
        this.weights = weights;
    }

    @Override
    protected Double compute() {

        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                String message = String.format("Animal %d weighted", i);
                System.out.println(message);
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
        int end = 10;
        Double[] weights = new Double[end];
        WeighAnimalsTask task = new WeighAnimalsTask(weights, start, end);

        ForkJoinPool pool = new ForkJoinPool();
        Double result = pool.invoke(task);

        System.out.println("Total weight: " + result);
        Arrays.stream(weights)
                .forEach(System.out::println);
        double sum = Arrays.stream(weights)
                .mapToDouble(Double::new)
                .sum();
        System.out.println(sum);
    }
}
