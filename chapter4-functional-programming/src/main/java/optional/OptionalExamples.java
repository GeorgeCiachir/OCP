package optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalExamples {

    public static void main(String[] args) {
        OptionalExamples examples = new OptionalExamples();


        examples.doAverage();
        examples.chainingOptionals();
    }

    private void doAverage() {
        List<Integer> ints = Arrays.asList(11, 12);

        String value =
                Optional.of(ints)
                        .filter(list -> !list.isEmpty())
                        .flatMap(list -> {
                            Double i = ((double) (ints.get(0) + ints.get(1))) / 2;
                            return Optional.of(i);
                        })
                        .map(average -> "Average is: " + average)
                        .orElse("No average yet");

        System.out.println(value);
    }

    private void chainingOptionals() {
        Function<String, String> functionMapper = (e) -> e + "aaa";
        Function<String, Optional<Integer>> optionalMapper = (e) -> Optional.of(e.length());

        Optional.of("ssss")
                .map(functionMapper)
                .flatMap(optionalMapper)
                .ifPresent(System.out::println);

        System.out.println();

        String nullString = null;
        Optional.ofNullable(nullString)
                .map(functionMapper)
                .flatMap(optionalMapper)
                .ifPresent(System.out::println);

        Optional.ofNullable(nullString)
                .map(functionMapper)
                .flatMap(optionalMapper)
                .ifPresent(System.out::println);
    }
}
