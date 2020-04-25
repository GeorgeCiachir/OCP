package threadingissues;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadlockExample {

    public static final String FOOD = "food";
    public static final String WATER = "water";

    public static void main(String[] args) throws InterruptedException {
        Fox gina = new Fox("Gina");
        Fox tzichi = new Fox("Tzichi");

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(gina::eatThenDrink);
        service.submit(tzichi::drinkThenEat);

        service.shutdown();
    }
}
