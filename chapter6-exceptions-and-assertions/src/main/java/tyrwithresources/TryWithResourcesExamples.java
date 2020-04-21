package tyrwithresources;

import java.util.Arrays;

public class TryWithResourcesExamples {

    public static void main(String[] args) {

        try {
            method1();
        } catch (Exception e) {

        }

        try {
            method2();
        } catch (Exception e) {

        }

        try {
            method3();
        } catch (Exception e) {

        }

        try {
            method4();
        } catch (Exception e) {

        }
    }

    private static void method1() {
        System.out.println("************* method1 ****************");

        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new IllegalStateException("thrown in the try");
        } catch (IllegalStateException e) {
            System.out.println("Primary: " + e.getMessage());
            System.out.println("Secondary exceptions");
            Arrays.stream(e.getSuppressed())
                    .map(Throwable::getMessage)
                    .forEach(System.out::println);
        }
    }

    private static void method2() {
        System.out.println("************** method2 ***************");

        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new IllegalStateException("thrown in the try");
        } catch (IllegalStateException e) {
            System.out.println("The stacktrace also prints the suppressed exceptions");
            e.printStackTrace();
        }
    }

    private static void method3() {
        System.out.println("************** method3 ***************");

        try {
            try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
                throw new IllegalStateException("this one is caught");
            } catch (IllegalStateException e) {
                throw new IllegalStateException("this one is overridden in the stack trace");
            } finally {
                throw new IllegalStateException("this one overrides all, including the one from the implicit finally of the try with resources");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // There are no suppressed exceptions -> there is only the one from he finally block
        }
    }

    private static void method4() {
        System.out.println("************* method4 ****************");

        try (AutoClosableThrowsException closable = new AutoClosableThrowsException()) {
            throw new RuntimeException("This gets all the way out and stops the app");
        } catch (IllegalStateException e) {
            System.out.println("It will never get here because the exception thrown when closing the resource is suppressed and can't be caught on it's own");
        }
    }
}


