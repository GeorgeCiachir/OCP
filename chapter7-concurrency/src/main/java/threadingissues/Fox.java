package threadingissues;

import static threadingissues.DeadlockExample.FOOD;
import static threadingissues.DeadlockExample.WATER;

public class Fox {

    private String name;

    public Fox(String name) {
        this.name = name;
    }

    private void move() {
        System.out.println(name + " going to the other bowl");
    }

    public void eatThenDrink()  {
        synchronized (FOOD) {
            System.out.println(name + " eating");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            synchronized (WATER) {
                System.out.println(name + " drinking");
            }
        }
    }

    public void drinkThenEat()  {
        synchronized (WATER) {
            System.out.println(name + " drinking");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            synchronized (FOOD) {
                System.out.println(name + " eating");
            }
        }
    }
}
