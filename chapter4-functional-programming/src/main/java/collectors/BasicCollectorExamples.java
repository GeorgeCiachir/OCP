package collectors;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicCollectorExamples {

    public static void main(String[] args) {
        BasicCollectorExamples examples = new BasicCollectorExamples();
        examples.averaging();
        examples.counting();
        examples.joining();
        examples.toCollection();
        examples.maxByMinBy();
        examples.summing();
        examples.summarizing();
    }

    private void averaging() {
        System.out.println();
        System.out.println();
        System.out.println("************** AVERAGING ******************");
        Double average = Stream.of(1, 2)
                .collect(Collectors.averagingInt(element -> element));
        System.out.println(average);

        average = Stream.of("aa", "bbb")
                .collect(Collectors.averagingInt(element -> element.length()));
        System.out.println(average);

        average = Stream.of("aa", "bbb")
                .collect(Collectors.averagingDouble(element -> element.length() + 0.5));
        System.out.println(average);
    }

    private void counting() {
        System.out.println();
        System.out.println();
        System.out.println("************** COUNTING ******************");
        Long count = Stream.of("aa", "bbb")
                .collect(Collectors.counting());
        System.out.println("count " + count);
    }

    private void joining() {
        System.out.println();
        System.out.println();
        System.out.println("************** JOINING ******************");
        String result = Stream.of("aa", "bbb")
                .collect(Collectors.joining());
        System.out.println(result);

        result = Stream.of("aa", "bbb")
                .collect(Collectors.joining("  "));
        System.out.println(result);

        result = Stream.of("aa", "bbb")
                .collect(Collectors.joining("  ", "prefix  ", "  suffix"));
        System.out.println(result);
    }

    private void toCollection() {
        TreeSet<String> collect = Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect);
    }

    private void maxByMinBy() {
        System.out.println();
        System.out.println();
        System.out.println("************** MAXBY MINBY ******************");

        Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.maxBy(Comparator.naturalOrder()))
                .ifPresent(System.out::println);

        Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.minBy(Comparator.naturalOrder()))
                .ifPresent(System.out::println);


        Stream.empty()
                .collect(Collectors.maxBy(Comparator.comparingInt(Object::hashCode)))
                .ifPresent(System.out::println);
    }

    private void summing() {
        System.out.println();
        System.out.println();
        System.out.println("************** SUMMING ******************");

        Double aDouble = Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.summingDouble(string -> string.length()));
        System.out.println(aDouble);

        Integer anInt = Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.summingInt(string -> string.length()));
        System.out.println(anInt);
    }

    private void summarizing() {
        System.out.println();
        System.out.println();
        System.out.println("************** SUMMARIZING STATISTICS ******************");

        DoubleSummaryStatistics statistics = Stream.of("zzzz", "aa", "bbb", "ssss")
                .collect(Collectors.summarizingDouble(String::length));
        System.out.println(statistics.getMax());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getCount());
        System.out.println(statistics.toString());
    }
}
