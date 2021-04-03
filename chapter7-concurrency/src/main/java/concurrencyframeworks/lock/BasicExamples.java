package concurrencyframeworks.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasicExamples {

    public static void main(String[] args) {
        BasicExamples client = new BasicExamples();
        client.runExamples();
    }

    private void runExamples() {
//        unlockALockThatYouDontHold();
//        firstLocksAndSecondHangsIfNotUnlockedByFirst();
//        lockAndUnlock();
        tryLockAndUnlock();
    }

    private void unlockALockThatYouDontHold() {
        Lock lock = new ReentrantLock();
        try {
            lock.unlock();
        } catch (IllegalMonitorStateException e) {
            System.out.println("Exception");
        }
    }

    private void lockAndUnlock() {
        Helper helper = new Helper();
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(() -> helper.lockAndUnlock());
        service.submit(() -> helper.lockAndUnlock());
        service.submit(() -> helper.lockAndUnlock());

        service.shutdown();
    }

    /*
     * There are four threads, so one of them won't be able to acquire the lock, because
     * the tryLock() waits for 3 seconds and each thread takes 1 second to perform the job
     */

    private void tryLockAndUnlock() {
        Helper helper = new Helper();
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(() -> helper.tryLockAndUnlock());
        service.submit(() -> helper.tryLockAndUnlock());
        service.submit(() -> helper.tryLockAndUnlock());
        service.submit(() -> helper.tryLockAndUnlock());

        service.shutdown();
    }

    private void firstLocksAndSecondHangsIfNotUnlockedByFirst() {
        Lock lock = new ReentrantLock();

        Runnable methodCall = () -> {
            lock.lock();
            System.out.println(String.format("Thread %s locked", Thread.currentThread().getName()));
        };

        new Thread(methodCall).start();
        new Thread(methodCall).start();
    }

    static class Helper {

        Lock lock = new ReentrantLock();

        private void lockAndUnlock() {
            try {
                lock.lock();
                System.out.println("Thread locking: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Should not happen");
            } finally {
                System.out.println("Unlocked by: " + Thread.currentThread().getName());
                lock.unlock();
            }
        }

        private void tryLockAndUnlock() {
            try {
                if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
                    //I choose to reuse the lockAndUnlock() method to also exemplify the "Reentrant" characteristic of
                    //the ReentrantLock
                    //The lock.tryLock() method call above is the first lock acquisition and in the lockAndUnlock()
                    //we acquire the same lock again
                    //This means we need to unlock it twice
                    try {
                        lockAndUnlock();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(String.format("Thread %s could not obtain the lock", Thread.currentThread().getName()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
