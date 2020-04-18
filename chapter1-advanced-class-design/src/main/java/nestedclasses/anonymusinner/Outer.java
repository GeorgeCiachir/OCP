package nestedclasses.anonymusinner;

import java.util.function.Function;
import java.util.stream.Stream;

public class Outer {

    private int outerClassValue = 2;

    Function<Integer, Integer> function2 = input -> {
        System.out.println(outerClassValue);
        outerClassValue++;
        System.out.println(outerClassValue);
        return outerClassValue;
    };

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.printSomething(22);
    }

    private void printSomething(int received) {
        int v = 5;

        Function<Integer, Integer> function1 = input -> {
            System.out.println(outerClassValue);
            outerClassValue++;
            System.out.println(outerClassValue);
            return outerClassValue;
        };
        function1.apply(received);

        Stream.of(1,2,3)
                .map(function1)
                .forEach(e -> System.out.println());

    }

}

