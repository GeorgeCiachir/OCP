package collectors;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;

public class AdvancedCollectorExamples {

    public static void main(String[] args) {
//        toMap();
//        groupingBy();
//        partitioningBy();
        mapping();
    }

    private static void toMap() {
        System.out.println("************** Collectors.toMap ******************");

        Map<String, Integer> namesAndNameLengths = Stream.of("elephant", "lions", "tigers", "bears")
                .collect(
                        Collectors.toMap(
                                identity(),
                                String::length));
        System.out.println(namesAndNameLengths);

        System.out.println();

        // we would like to have the number of letters as the key
        // the problem is that "lions" and "bears" have the same number of letters => key duplication
        Stream<String> names = Stream.of("elephant", "lions", "tigers", "bears");
        Map<Integer, String> namesLengthAndNames = new HashMap<>();
//        namesLengthAndNames = names.collect(Collectors.toMap(k -> k.length(), k -> k)); //throws IllegalStateException: Duplicate key lions

        // we could have multiple animals with the same number of letters in the name
        // but we could group them with the same key
        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.toMap(
                                String::length,
                                identity(),
                                (value1, value2) -> String.join(",", value1, value2)))
                .forEach((k, v) -> System.out.println("name size: " + k + " ; animals: " + v));

        System.out.println();

        //we could even want them ordered naturally ordered by keys
        Stream.of("elephant", "lions", "an animal with a really long name", "tigers", "bears")
                .collect(
                        Collectors.toMap(
                                String::length,
                                identity(),
                                (value1, value2) -> value1 + "," + value2,
                                TreeMap::new))
                .forEach((k, v) -> System.out.println("name size: " + k + " ; animals: " + v));

        System.out.println();

        //or in a reversed order
        Stream.of("elephant", "lions", "an animal with a really long name", "tigers", "bears")
                .collect(
                        Collectors.toMap(
                                String::length,
                                identity(),
                                (value1, value2) -> String.join(",", value1, value2),
                                () -> new TreeMap<>((k1, k2) -> k2 - k1)))
                .forEach((k, v) -> System.out.println("name size: " + k + " ; animals: " + v));


        System.out.println();
        System.out.println("A \"toMap\" collector that simulates a groupingBy collector");
        System.out.println();

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.toMap(
                                String::length,
                                k -> {
                                    System.out.println("Creating a new list each time");
                                    List<String> list = new ArrayList<>();
                                    list.add(k);
                                    return list;
                                },
                                (list, value2) -> {
                                    System.out.println("Merging list for which we have the same keys");
                                    list.addAll(value2);
                                    return list;
                                }
                        )
                )
                .forEach((k, v) -> System.out.println("name size: " + k + " ; animals: " + v));
    }

    private static void groupingBy() {
        System.out.println("************** Collectors.groupingBy ******************");

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.groupingBy(
                                String::length))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.toSet()))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                () -> new TreeMap<>((x1, x2) -> x2 - x1),
                                Collectors.toSet()))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.joining(",")))
                .forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println();

        Stream.of("elephant", "an animal with a really long name", "lions", "tigers", "bears")
                .collect(
                        Collectors.groupingBy(
                                String::length,
                                Collectors.toMap(
                                        e -> e,
                                        String::length,
                                        Integer::sum,
                                        () -> new TreeMap<>((x1, x2) -> x2.compareTo(x1))))
                )
                .forEach((k, v) -> System.out.println(k + " " + v));
    }


    private static void partitioningBy() {
        System.out.println("************** Collectors.partitioningBy ******************");

        Map<Boolean, List<String>> result1;
        result1 = Stream.of("lions", "elephant", "bears")
                .collect(Collectors.partitioningBy(element -> element.length() <= 5));
        System.out.println(result1);

        System.out.println();


        result1 = Stream.of("lions", "elephant", "bears")
                .collect(Collectors.partitioningBy(element -> element.length() <= 10));
        System.out.println(result1);

        System.out.println();


        Map<Boolean, List<String>> result2 = Stream.of("lions", "elephant", "bears")
                .collect(Collectors.partitioningBy(element -> element.length() <= 10, Collectors.toList()));
        System.out.println(result2);

        System.out.println();

        Map<Boolean, Map<Integer, Set<String>>> result3 = Stream.of("lions", "elephant", "bears")
                .collect(
                        Collectors.partitioningBy(
                                element -> element.length() <= 10,
                                Collectors.groupingBy(String::length, Collectors.toSet())
                        )
                );
        System.out.println(result3);
    }

    private static void mapping() {
        System.out.println("************** Collectors.mapping ******************");
        // These are the same
        // stream().collect(mapping(mapper, collector))
        // stream().map(mapper).collect(collector);

        Function<String, String> mapper = element -> "[" + element + " " + element.length() + "]";
        Collector<CharSequence, ?, String> aCollector = Collectors.joining(";");


        String value = Stream.of("lions", "tigers", "bears")
                .collect(Collectors.mapping(mapper, aCollector));

        System.out.println(value);

        value = Stream.of("lions", "tigers", "bears")
                .map(mapper)
                .collect(aCollector);

        System.out.println(value);
    }

}
