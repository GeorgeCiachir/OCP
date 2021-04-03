package synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncExamples {

    private static synchronized void simpleSyncOnClassNameMethod() throws InterruptedException {
        System.out.println("Simple static sync method; entering thread: " + Thread.currentThread().getName());
        System.out.println("Simple static sync method acquired lock");
        Thread.sleep(3000);
        System.out.println("Simple static sync method releasing lock");
        System.out.println();
        System.out.println("*****************************");
        System.out.println();
    }

    private void firstManuallySyncOnClassNameMethod() throws InterruptedException {
        synchronized (SyncExamples.class) {
            System.out.println("First method; entering thread: " + Thread.currentThread().getName());
            System.out.println("First method acquired lock");
            Thread.sleep(3000);
            System.out.println("First method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }

    private void secondManuallySyncOnClassNameMethod() throws InterruptedException {
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

    private synchronized void simpleSyncMethod() throws InterruptedException {
        System.out.println("Simple sync method; entering thread: " + Thread.currentThread().getName());
        System.out.println("Simple sync method acquired lock");
        Thread.sleep(3000);
        System.out.println("Simple sync method releasing lock");
        System.out.println();
        System.out.println("*****************************");
        System.out.println();
    }

    private void firstManuallySyncMethod() throws InterruptedException {
        synchronized (this) {
            System.out.println("First method; entering thread: " + Thread.currentThread().getName());
            System.out.println("First method acquired lock");
            Thread.sleep(3000);
            System.out.println("First method releasing lock");
            System.out.println();
            System.out.println("*****************************");
            System.out.println();
        }
    }

    private void secondManuallySyncMethod() throws InterruptedException {
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


    public static void main(String[] args) {
//        oneMethodSyncAnotherUnsyncExample();
//        threeMethodsSyncOnTheSameInstanceExample();
//        twoMethodsSyncOnDifferentInstancesExample();
//        threeMethodsSyncOnClassNameEvenThoughDifferentInstances();
//        waitMethodOnANonMonitor();
        becauseSyncIsDoneOnDifferentMonitorsSyncOnClassIsDifferentThanSyncOnInstance();
    }

    private static void oneMethodSyncAnotherUnsyncExample() {
        SyncExamples examples = new SyncExamples();
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(() -> {
            examples.firstManuallySyncMethod();
            return 2;
        });

        service.submit(() -> {
            examples.unSyncMethod();
            return 2;
        });

        service.shutdown();
    }

    /*
     * Because the 3 methods sync on the instance, they will execute in sync, one after another
     * The result is the same if the sync is done manually on the instance object, or using the synchronized keyword on the method's signature
     */
    private static void threeMethodsSyncOnTheSameInstanceExample() {
        SyncExamples examples = new SyncExamples();
        ExecutorService service = Executors.newFixedThreadPool(3);

        service.submit(() -> {
            examples.simpleSyncMethod();
            return 2;
        });

        service.submit(() -> {
            examples.firstManuallySyncMethod();
            return 2;
        });

        service.submit(() -> {
            examples.secondManuallySyncMethod();
            return 2;
        });

        service.shutdown();
    }

    /*
     * This time the methods are synced on different instances, therefore the execution is concurrent
     */
    private static void twoMethodsSyncOnDifferentInstancesExample() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        SyncExamples firstInstance = new SyncExamples();
        SyncExamples secondInstance = new SyncExamples();

        service.submit(() -> {
            firstInstance.firstManuallySyncMethod();
            return 2;
        });

        service.submit(() -> {
            secondInstance.secondManuallySyncMethod();
            return 2;
        });

        service.shutdown();
    }

    /*
     * The execution is synced because even though the methods are called from different instances
     * they are synced on the class name, not on the instance
     */
    private static void threeMethodsSyncOnClassNameEvenThoughDifferentInstances() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        SyncExamples firstInstance = new SyncExamples();
        SyncExamples secondInstance = new SyncExamples();
        SyncExamples thirdInstance = new SyncExamples();

        service.submit(() -> {
            thirdInstance.simpleSyncOnClassNameMethod();
            return 2;
        });

        service.submit(() -> {
            SyncExamples.simpleSyncOnClassNameMethod();
            return 2;
        });

        service.submit(() -> {
            firstInstance.firstManuallySyncOnClassNameMethod();
            return 2;
        });

        service.submit(() -> {
            secondInstance.secondManuallySyncOnClassNameMethod();
            return 2;
        });

        service.shutdown();
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
     * What I want to say is that in this case, the synchronizations don't interfere
     */
    private static void becauseSyncIsDoneOnDifferentMonitorsSyncOnClassIsDifferentThanSyncOnInstance() {
        ExecutorService service = Executors.newFixedThreadPool(2);
        SyncExamples firstInstance = new SyncExamples();
        SyncExamples secondInstance = new SyncExamples();

        service.submit(() -> {
            firstInstance.simpleSyncOnClassNameMethod();
            return 2;
        });

        service.submit(() -> {
            secondInstance.simpleSyncMethod();
            return 2;
        });

        service.shutdown();
    }
}
