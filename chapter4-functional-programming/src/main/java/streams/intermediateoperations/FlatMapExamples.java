package streams.intermediateoperations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class FlatMapExamples {

    public static void main(String[] args) {
        List<Integer> first = Arrays.asList();
        List<Integer> second = Arrays.asList(1, 2, 3);
        List<Integer> third = Arrays.asList(4, 5, 6);

        Stream.of(first, second, third)
                .flatMap(List::stream)
                .forEach(System.out::print);
        System.out.println();

        Stream.of(first, second, third)
                .flatMap(element -> {
                    return element.stream()
                            .map(e -> "element " + e + " ; ");
                })
                .forEach(System.out::print);
        System.out.println();

        Stream.of(1, 2, 3)
                .flatMap(e -> {
                    return Stream.iterate(e, i -> i + 1)
                            .limit(5);
                })
                .forEach(e -> System.out.print("element " + e + " ; "));
        System.out.println();


        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<String> strings = Arrays.asList("a", "b", "c");
        Stream.of(integers, strings)
                .flatMap(Collection::stream)
                .map(e -> (String) e) //ClassCastException
                .forEach(System.out::println);
    }
}
