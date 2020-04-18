package nestedclasses;

public class OverloadExamples {

    public static void main(String[] args) {

    }

    private static int method(int input) {
        return 2;
    }

    private static int method(long input) {
        return 2;
    }

    private static int method(Integer input) {
        return 2;
    }

    private static int method(int... input) {
        return 2;
    }

    private static int method(Number input) {
        return 2;
    }

    private static int method(Object input) {
        return 2;
    }

    private static int method(Integer... input) {
        return 2;
    }

    private static int method(Number... input) {
        return 2;
    }

}
