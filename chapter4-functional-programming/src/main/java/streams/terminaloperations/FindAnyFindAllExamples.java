package streams.terminaloperations;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class FindAnyFindAllExamples {

    public static void main(String[] args) {
        Stream.of("monkey", "ape", "bonobo")
                .min(Comparator.comparingInt(String::length))
                .ifPresent(System.out::println);
        Stream.empty()
                .min((first, second) -> 0)
                .ifPresent(System.out::println);

        System.out.println();
        System.out.println("*****************************************");
        System.out.println();


        Stream.of("monkey", "ape", "bonobo", "x", "y", "z")
                .findAny()
                .ifPresent(System.out::println); //prints monkey

        // parallel
        Stream.of("monkey", "ape", "bonobo", "x", "y", "z", "q", "w")
                .parallel()
                .findAny()
                .ifPresent(System.out::println); //prints z

        System.out.println();
        System.out.println("*****************************************");
        System.out.println();

        Stream.of("monkey", "ape", "bonobo", "x", "y", "z")
                .findFirst()
                .ifPresent(System.out::println); //prints monkey

        // parallel
        Stream.of("monkey", "ape", "bonobo", "x", "y", "z")
                .parallel()
                .findFirst()
                .ifPresent(System.out::println); //prints monkey


        System.out.println();
        System.out.println("******************Parallel on infinite stream**********************");
        System.out.println();

        AtomicInteger integer = new AtomicInteger(1);
        Stream.generate(() -> "chimp + ")
                .parallel()
                .peek(element -> System.out.println(integer.getAndIncrement()))
                .findAny()
                .ifPresent(element -> System.out.println(element + integer));

        Stream.iterate(0, i -> i + 1)
                .parallel()
                .filter(element -> element > 4000)
                .findAny()
                .ifPresent(element -> System.out.println("Found " + element));

    }
}
