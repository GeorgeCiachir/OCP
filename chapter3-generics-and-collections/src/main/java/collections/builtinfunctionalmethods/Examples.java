package collections.builtinfunctionalmethods;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Examples {

    public static void main(String[] args) {
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus tour");
        favorites.put("Tom", "Tram");
        System.out.println(favorites);

        System.out.println();
        System.out.println("*****************************");
        System.out.println();

        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() >= v2.length() ? v1 : v2;
        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);
        String mihai = favorites.merge("Mihai", "This is added by the merge function because there is no existing value", mapper);

        System.out.println(favorites);
        System.out.println(jenny);
        System.out.println(tom);
        System.out.println(mihai);

        System.out.println();
        System.out.println("*****************************");
        System.out.println();

        BiFunction<String, String, String> removerByKey = (v1, v2) -> null;
        jenny = favorites.merge("Jenny", "Skyride", removerByKey);

        System.out.println(favorites);
        System.out.println(jenny);
        System.out.println(tom);

        System.out.println();
        System.out.println("*****************************");
        System.out.println();

        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        BiFunction<String, Integer, Integer> intMapper = (k, v) -> v + 1;
        Integer jennyCounts = counts.computeIfPresent("Jenny", intMapper);
        Integer samCounts = counts.computeIfPresent("Sam", intMapper);

        System.out.println(counts);
        System.out.println(jennyCounts);
        System.out.println(samCounts);

        Function<String, Integer> addder = k -> 1;
        samCounts = counts.computeIfAbsent("Sam", addder);
        System.out.println(samCounts);
        samCounts = counts.computeIfPresent("Sam", intMapper);
        System.out.println(samCounts);
        System.out.println(counts);
    }
}
