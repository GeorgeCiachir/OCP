package streams.terminaloperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ForEachExamples {

    public static void main(String[] args) {
        findMaxInAList();
        methodReference();
    }

    private static void methodReference() {
        class MyProcessor {
            private final int value;

            public MyProcessor(int value) {
                this.value = value;
            }

            public void process() {
                System.out.print(value + " ");
            }

            @Override
            public String toString() {
                return value + " ";
            }
        }
        IntStream.of(1, 2, 3)
                .mapToObj(MyProcessor::new)
                .forEach(MyProcessor::process);

        System.out.println();

        IntStream.of(1, 2, 3)
                .mapToObj(MyProcessor::new)
                .forEach(System.out::print);
    }


    private static void findMaxInAList() {
        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);

        System.out.println(ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b));
        System.out.println(ls.stream().max(Integer::max).get());
        System.out.println(ls.stream().max(Integer::compare).get());
        System.out.println(ls.stream().max((a, b) -> a > b ? a : b));
    }
}
