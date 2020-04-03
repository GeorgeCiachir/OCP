package assertions;

public class AssertionsExamples {

    public static void main(String[] args) {
        assertSize(3);
        assertSize(1);

        assert true;
        assert false; // throws AssertionError
    }

    private static void assertSize(int size) {
        assert size > 2: "The minimum size should be " + 2;
        System.out.println("The size is: " + size);

    }
}
