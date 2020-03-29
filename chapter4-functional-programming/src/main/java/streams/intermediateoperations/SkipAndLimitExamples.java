package streams.intermediateoperations;

import java.util.stream.Stream;

public class SkipAndLimitExamples {

    public static void main(String[] args) {
        Stream.iterate(1, i -> i + 1)
                .skip(25)
                .limit(55)
                .forEach(value -> System.out.print(value + " ")); //prints all from 26 to 80

        System.out.println();

        Stream.iterate(1, i -> i + 1)
                .limit(55)
                .skip(25)
                .forEach(value -> System.out.print(value + " ")); //prints all from 26 to 55
    }
}
