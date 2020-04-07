package parallelstreams;

import java.util.*;
import java.util.stream.Collectors;

public class ParallelStreamsExamples {

    public static void main(String[] args) {
//        orderingAStream();
//        usingOrderedOperationsOnParallelStreamsProducesTheSameEffectAsWithASerialStream();
//        usingOrderedOperationsOnAnUnorderedStream();
//        useReduce();
        useCollect();
    }

    private static void useCollect() {
        Map<Integer, String> result = Arrays.asList("lions", "tigers", "bears")
                .parallelStream()
                .collect(Collectors.toConcurrentMap(String::length, k -> k, (v1, v2) -> v1 + " , " + v2));
        System.out.println(result);

        Map<Integer, List<String>> secondResult = Arrays.asList("lions", "tigers", "bears")
                .parallelStream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(secondResult);

        Map<Integer, Set<String>> thirdResult = Arrays.asList("lions", "tigers", "bears")
                .parallelStream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(thirdResult);
    }

    private static void useReduce() {
        String result = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l')
                .stream()
                .reduce("", (str, ch) -> str + ch, (str1, str2) -> str1 + str2);
        System.out.println(result);

        result = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l')
                .parallelStream()
                .reduce("", (str, ch) -> str + ch, (str1, str2) -> str1 + str2);
        System.out.println(result);
    }


    private static void usingOrderedOperationsOnAnUnorderedStream() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 9, 15, 44, 32, 66, 99, 5, 4, 3, 2));

        Integer integer = list.stream().unordered()
                .parallel()
                .skip(3)
                .findFirst()
                .get();
        System.out.println(integer);

    }

    private static void usingOrderedOperationsOnParallelStreamsProducesTheSameEffectAsWithASerialStream() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 4, 3, 2));

        Integer integer = list.parallelStream()
                .skip(2)
                .limit(2)
                .findFirst()
                .get();
        System.out.println(integer);
    }

    private static void orderingAStream() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 5, 4, 3, 2));

        System.out.println();
        System.out.println("***** simple stream ****");
        System.out.println();

        System.out.println("prints the initial list, in the same order");
        list.stream()
                .forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("sort the initial list on the stream");
        list.stream()
                .sorted()
                .forEach(value -> System.out.print(value + " "));
        System.out.println();


        //parallel
        System.out.println();
        System.out.println("***** parallel stream ****");
        System.out.println();

        System.out.println("prints the initial list, in random order");
        list.parallelStream()
                .forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("sort the initial list on the parallel stream doesn't work");
        list.parallelStream()
                .sorted(Comparator.naturalOrder())
                .forEach(value -> System.out.print(value + " "));
        System.out.println();

        System.out.println("use forEachOrdered for parallel stream in order to at least keep the order of the initial list");
        list.parallelStream()
                .forEachOrdered(value -> System.out.print(value + " "));


        //combine streams
        System.out.println();
        System.out.println("***** combine simple stream with parallel stream ****");
        System.out.println();

        // the problem with the forEachOrdered() is that it behaves as a normal stream -> sequential -> performance is lost
        // we keep the advantage of using a parallel stream to the some async work before the forEachMethod is called
        // this is basically like using a join() -> each thread does stuff independent of the others, but in the end we collect them all to process the results
        // in the receiving order
        System.out.println("sort the initial list on the simple stream and then use a parallel stream to do the work, keeping the order");
        list.stream()
                .sorted(Comparator.naturalOrder())
                .parallel()
                .forEachOrdered(value -> System.out.print(value + " "));
        System.out.println();
    }
}
