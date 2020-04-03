public class BasicExamples {

    public static void main(String[] args) {
        exceptionCatcher(BasicExamples::foo);
        System.out.println(exceptionCatchAndReturn(BasicExamples::foo));


        try {
            foo();
        } catch (CustomException e) {
            foo();
        } catch (Exception e) {
            System.out.println("This will not be printed");
        } finally {
            System.out.println("In the finally");
        }

    }

    private static void exceptionCatcher(Action action) {

        try {
            action.doAction();
        } catch (Exception e) {
            System.out.println("printing stacktrace");

            e.printStackTrace();
            System.out.println("**********************");
            e.getCause().printStackTrace();
        }

    }

    private static String exceptionCatchAndReturn(Action action) {

        try {
            action.doAction();
        } catch (Exception e) {
            return "Value returned from catch";
        } finally {
            return "Value returned from finally";
        }
    }

    private static void foo() throws CustomException{
        throw new CustomException(new RuntimeException("some message"));
    }

}

class CustomException extends RuntimeException {

    public CustomException(Exception e) {
        super(e);
    }
}

interface Action {

    void doAction();
}
