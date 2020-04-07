package manuallycreatingthreads;

public class CreatingThreadsExamples extends Thread {

    public static void main(String[] args) {
        Thread executingThread;

        executingThread = new Thread() {

            @Override
            public void run() {
                System.out.println("This will be printed");
            }
        };
        executingThread.start();

        System.out.println();
        System.out.println("**********************");
        System.out.println();

        executingThread = new Thread(() -> System.out.println("This will not be printed")) {

            @Override
            public void run() {
                System.out.println("This takes precedence and will be printed");
            }
        };
        executingThread.start();

        System.out.println();
        System.out.println("**********************");
        System.out.println();

        executingThread = new Thread(() -> System.out.println("Inside the runnable"));
        executingThread.start();


        System.out.println("Main thread: " + Thread.currentThread().getName());

        executingThread = new Thread(() -> {
            System.out.println("Current thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executingThread.start();
        executingThread.start(); //IllegalThreadStateException


    }

}
