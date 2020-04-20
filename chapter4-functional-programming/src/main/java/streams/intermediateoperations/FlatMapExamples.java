package streams.intermediateoperations;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class FlatMapExamples {

    public static void main(String[] args) {

        Stream.of(asList(), asList(1, 2, 3), asList(4, 5, 6))
                .flatMap(element -> element.stream().map(e -> "element " + e + " ; "))
                .forEach(System.out::print);

        System.out.println();

        Stream.of(1, 2, 3)
                .flatMap(e -> Stream.iterate(e, i -> i + 1).limit(5))
                .forEach(e -> System.out.print("element " + e + " ; "));
        System.out.println();

        Stream.of(asList(1, 2, 3), asList("a", "b", "c"))
                .flatMap(Collection::stream)
                .map(e -> (String) e) //ClassCastException
                .forEach(System.out::println);
    }
}
