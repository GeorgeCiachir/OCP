package streams.intermediateoperations;

import java.util.stream.Stream;

public class DistinctExamples {

    public static void main(String[] args) {
        DistinctExamples examples = new DistinctExamples();
        examples.runExamples();
    }

    /*
     * Removes duplicates
     * It calls equals() on the objects
     */
    private void runExamples() {
        Stream.of("first", "first", "second", "third", "first", "fourth")
                .distinct()
                .forEach(System.out::println);
    }

}
