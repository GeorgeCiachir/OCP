package tyrwithresources;

import java.util.Arrays;

public class TryWithResourcesExamples {

    public static void main(String[] args) {


        try (AutoClosableResource autoClosableResource = new AutoClosableResource();
             ClosableResource closableResource = new ClosableResource()) {
            autoClosableResource.doSomething();
            closableResource.doSomething();
        }

        System.out.println();
        System.out.println("*****************************");
        System.out.println();


        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new IllegalStateException("thrown in the try");
        } catch (IllegalStateException e) {
            System.out.println("Primary: " + e.getMessage());
            System.out.println("Secondary exceptions");
            Arrays.stream(e.getSuppressed())
                    .map(Throwable::getMessage)
                    .forEach(System.out::println);
        }


        System.out.println();
        System.out.println("*****************************");
        System.out.println();

        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new IllegalStateException("thrown in the try");
        } catch (IllegalStateException e) {
            System.out.println("The stacktrace also prints the suppressed exceptions");
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("*****************************");
        System.out.println();



        try{
            try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
                throw new IllegalStateException("the exception thrown in the try gets ignored");
            } catch (IllegalStateException e) {
                throw new IllegalStateException("the exception thrown in the catch gets ignored");
            } finally {
                throw new IllegalStateException("the exception thrown in the finally overrides all");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Arrays.stream(e.getSuppressed())
                    .map(Throwable::getMessage)
                    .forEach(System.out::println);
        }


        System.out.println();
        System.out.println("*****************************");
        System.out.println();

        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new RuntimeException("This gets all the way out and stops the app");
        } catch (IllegalStateException e) {
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Secondary exceptions");
            Arrays.stream(e.getSuppressed())
                    .map(Throwable::getMessage)
                    .forEach(System.out::println);
        }

    }
}


