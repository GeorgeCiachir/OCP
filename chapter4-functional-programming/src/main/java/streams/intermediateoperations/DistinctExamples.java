package streams.intermediateoperations;

import java.util.stream.Stream;

public class DistinctExamples {

    public static void main(String[] args) {
        distinct();
    }

    /*
     * calls equals() on the objects
     */
    private  static void distinct() {

        Stream.of("first", "first", "second", "third", "first", "fourth")
                .distinct()
                .forEach(System.out::println);
    }

}
