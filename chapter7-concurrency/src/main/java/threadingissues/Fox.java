package threadingissues;

import static threadingissues.DeadlockExample.FOOD;
import static threadingissues.DeadlockExample.WATER;

public class Fox {

    private String name;

    public Fox(String name) {
        this.name = name;
    }

    private void goToTheOtherBowl() {
        System.out.println(name + " going to the other bowl. It will take approx. 100 ms");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void eatThenDrink() {
        synchronized (FOOD) {
            System.out.println(name + " eating");

            goToTheOtherBowl();
            synchronized (WATER) {
                System.out.println(name + " drinking");
            }
        }
    }

    public void drinkThenEat() {
        synchronized (WATER) {
            System.out.println(name + " drinking");
            goToTheOtherBowl();
            synchronized (FOOD) {
                System.out.println(name + " eating");
            }
        }
    }
}
