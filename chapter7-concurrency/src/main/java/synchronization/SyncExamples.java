package synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncExamples {

    private void firstSyncOnClassNameMethod() throws InterruptedException {
        synchronized (SyncExamples.class) {
            System.out.println("First method; entering thread: " + Thread.currentThread().getName());
            System.out.println("first method acquired lock");
            Thread.sleep(3000);
            System.out.println("first method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }

    private void secondSyncOnClassNameMethod() throws InterruptedException {
        synchronized (SyncExamples.class) {
            System.out.println("Second method; entering thread: " + Thread.currentThread().getName());
            System.out.println("Second method acquired lock");
            Thread.sleep(3000);
            System.out.println("Second method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }


    private void firstSyncMethod() throws InterruptedException {
        synchronized (this) {
            System.out.println("First method; entering thread: " + Thread.currentThread().getName());
            System.out.println("first method acquired lock");
            Thread.sleep(3000);
            System.out.println("first method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }

    private void secondSyncMethod() throws InterruptedException {
        synchronized (this) {
            System.out.println("Second method; entering thread: " + Thread.currentThread().getName());
            System.out.println("Second method acquired lock");
            Thread.sleep(3000);
            System.out.println("Second method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }

    private void unSyncMethod() {
        System.out.println("Unsync method; entering thread " + Thread.currentThread().getName());
        System.out.println();
        System.out.println("*****************************");
        System.out.println();
    }


    public static void main(String[] args) throws InterruptedException {
//        oneMethodSyncAnotherUnsyncExample();
//        twoMethodsSyncOnTheSameInstanceExample();
//        twoMethodsSyncOnDifferentInstancesExample();
//        twoMethodsSyncOnClassNameEvenThoughDifferentInstances();
        waitMethodOnANonMonitor();
    }

    /**
     * @see IllegalMonitorStateException
     * @see Object#wait(long timeout)
     * <p>
     * Remember that an object becomes a monitor only if in a synchronized block
     * If the object is not a monitor at the time the wait method is called, an IllegalMonitorStateException is thrown
     */
    private static void waitMethodOnANonMonitor() {
        Object o = new Object();
        try {
            o.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*
     * The execution is synced because even though the methods are called from different instances
     * they are synced on the class name, not on the instance
     */
    private static void twoMethodsSyncOnClassNameEvenThoughDifferentInstances() throws InterruptedException {
        ExecutorService service;

        SyncExamples examples1 = new SyncExamples();
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples1.firstSyncOnClassNameMethod();
            return 2;
        });
        service.shutdown();

        SyncExamples examples2 = new SyncExamples();
        Thread.sleep(100);
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples2.secondSyncOnClassNameMethod();
            return 2;
        });
        service.shutdown();
    }

    /*
     * This time the methods are synced on different instances, therefore the execution is concurrent
     */
    private static void twoMethodsSyncOnDifferentInstancesExample() throws InterruptedException {
        ExecutorService service;

        SyncExamples examples1 = new SyncExamples();
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples1.firstSyncMethod();
            return 2;
        });
        service.shutdown();

        SyncExamples examples2 = new SyncExamples();
        Thread.sleep(100);
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples2.secondSyncMethod();
            return 2;
        });
        service.shutdown();
    }

    /*
     * Because the 2 methods sync on the instance, they will execute in sync, one after another
     * The result is the same if the sync is done manually on the instance object, or using the synchronized keyword on the method's signature
     */
    private static void twoMethodsSyncOnTheSameInstanceExample() throws InterruptedException {
        SyncExamples examples = new SyncExamples();
        ExecutorService service;

        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples.firstSyncMethod();
            return 2;
        });
        service.shutdown();

        Thread.sleep(100);
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples.secondSyncMethod();
            return 2;
        });
        service.shutdown();

        examples.secondSyncMethod();
    }

    private static void oneMethodSyncAnotherUnsyncExample() throws InterruptedException {
        SyncExamples examples = new SyncExamples();
        ExecutorService service;

        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples.firstSyncMethod();
            return 2;
        });
        service.shutdown();

        Thread.sleep(100);
        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            examples.unSyncMethod();
            return 2;
        });
        service.shutdown();


        examples.unSyncMethod();
    }
}
