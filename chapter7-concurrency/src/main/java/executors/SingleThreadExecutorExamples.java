package executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SingleThreadExecutorExamples {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        Callable<String> first = () -> "first";
        Callable<String> second = () -> "second";
        Callable<String> third = () -> "third";
        Callable<String> fourth = () -> "fourth";
        Callable<String> fifth = () -> "fifth";
        Callable<String> sixth = () -> "sixth";
        List<Callable<String>> callables = Arrays.asList(first, second, third, fourth, fifth, sixth);

//        usingExecute();
//        usingSubmit();
//        usingInvokeAll(callables);
//        usingInvokeAny(callables);
//        testMethodsOnAFuture();
//        usingCancel();
//        usingGetWithTimeout();

//        throwingExceptions(false);
        test();
    }

    private static void test() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            System.out.println("Entering runnable");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Exiting runnable");
        };

        service.execute(runnable);
        service.shutdownNow();


    }

    private static void throwingExceptions(boolean throwsException) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        service.submit(() -> {
            if (throwsException) {
                // this does not compile because by specifying the return type void,
                // we make it a Runnable and Runnable cannot throw checked exceptions
//                throw new Exception();
            }
            return;
        });

        service.submit(() -> {
            // this is a Runnable because we return something
            if (throwsException) {
                throw new Exception();
            }
            return 3;
        });

    }

    private static void usingGetWithTimeout() throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = service.submit(() -> {
            Thread.sleep(3000);
            return "Some string";
        });
        service.shutdown();

        System.out.println("If the waiting time (timeout) for the future is reached and the future is not yet done, a TimeoutException is thrown");
        System.out.println(stringFuture.get(1000, TimeUnit.MILLISECONDS));
    }

    private static void usingCancel() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = service.submit(() -> {
            Thread.sleep(3000);
            return "Some string";
        });
        service.shutdown();

        stringFuture.cancel(true);
        System.out.println("cancel called");
        System.out.println("try to get the future's value, but an CancellationException will be thrown");
        stringFuture.get();
    }

    private static void testMethodsOnAFuture() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = service.submit(() -> {
            Thread.sleep(2000);
            return "Some string";
        });
        // this waits for the result of the task, so it makes the call sync
        // it's like introducing the polling block
//        while (!stringFuture.isDone()) {
//            Thread.sleep(100);
//        }
        System.out.println(stringFuture.get());

        System.out.println("This is not printed until the Future above is done");
        service.shutdown();


        service = Executors.newSingleThreadExecutor();
        Future<String> anotherStringFuture = service.submit(() -> {
            Thread.sleep(3000);
            return "Some string";
        });
        service.shutdown(); // it is ok to call it here because it will execute the started tasks

        service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            while (!anotherStringFuture.isDone()) {
                Thread.sleep(100);
            }

            System.out.println("This will be printed at some time in the future: " + System.currentTimeMillis());
            System.out.println("The future's values: " + anotherStringFuture.get());
            return "Using a Callable doesnt require the exception to be handled, so I return something to make it a Callable instead of a Runnable";
        });
        service.shutdown(); // it is ok to call it here because it will execute the started tasks

        System.out.println("This will now be printed: " + System.currentTimeMillis());

    }

    /*
     * executes sync
     */
    private static void usingInvokeAny(List<Callable<String>> callables) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        String result = service.invokeAny(callables);
        service.shutdown();
        System.out.println(result);
    }

    /*
     * <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
     *
     * executes sync
     * waits until all the tasks are finished, so calling isDone() on any of the returned futures will give true
     */
    private static void usingInvokeAll(List<Callable<String>> callables) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        List<Future<String>> futures = service.invokeAll(callables);

        //Not required at this point all the futures are done
//        boolean allFuturesAreDone = false;
//        while (!allFuturesAreDone) {
//            allFuturesAreDone = futures.stream().allMatch(Future::isDone);
//        }
        service.shutdown();

        futures.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

    }


    /*
     * executes async
     */
    private static void usingSubmit() throws ExecutionException, InterruptedException {
        ExecutorService service;

        service = Executors.newSingleThreadExecutor();
        Future<?> simpleFuture = service.submit(() -> System.out.println("dsdsds")); //takes Runnable
        while (!simpleFuture.isDone()) {
            Thread.sleep(100);
        }
        System.out.println("simpleFuture is finished");
        service.shutdown();
        System.out.println(simpleFuture.get());

        ExecutorService service2 = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = service2.submit(() -> "Some string"); //takes Callable
        while (!stringFuture.isDone()) {
            Thread.sleep(100);
        }
        System.out.println("stringFuture is finished");
        service2.shutdown();
        System.out.println(stringFuture.get());
    }

    /*
     * executes async
     */
    private static void usingExecute() {
        ExecutorService service = null;

        try {
            service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                System.out.println("Entered runnable 1 on thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Exiting runnable 1 on thread: " + Thread.currentThread().getName());

            });


            // do not reassign the ExecutorService
            // A thread executor creates a non-daemon thread on the first task that is executed
            // if the reference is reassigned, we lose contact with that ExecutorService and we'll not be
            // able to call the shutdown() method on it
            // because the thread is non-daemon, the app will never terminate
            // service = Executors.newSingleThreadExecutor();
            service.execute(() -> {
                System.out.println("Entered runnable 2 on thread: " + Thread.currentThread().getName());
                System.out.println("Exiting runnable 2 on thread: " + Thread.currentThread().getName());
            });

        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
