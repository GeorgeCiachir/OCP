package collections.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Examples {

    public static void main(String[] args) {
        // Note: the merge() and the compute() functions on the map do more or less the same thing
        System.out.println("");
        System.out.println("*** merge() ***");
        System.out.println("");
        merge();
        System.out.println("");
        System.out.println("*** compute() ***");
        System.out.println("");
        compute();
        System.out.println("");
        System.out.println("*** computeIfPresent() ***");
        System.out.println("");
        computeIfPresent();
        System.out.println("");
        System.out.println("*** computeIfAbsent() ***");
        System.out.println("");
        computeIfAbsent();
    }

    // 1. If map.get(key) returns a value, then the computation is performed between the existing and the proposed value
    //
    // 2. If the value of the specified key is null -> basically if map.get(key) returns null
    //      -> the key might be present but the value null -> the proposed value is inserted for the mentioned key
    //      -> the key does not exist at all -> a new (k,v) pair is added to the map (the mentioned key with the proposed value)
    //
    // 3. If map.get(key) returns a value and if the mapper returns a null value after the computation, then the initial (k,v) pair is removed from the map

    private static void merge() {
        Map<String, Integer> favoritesNumbers = new HashMap<>();
        favoritesNumbers.put("Jenny", 15);
        favoritesNumbers.put("Tom", 22);
        favoritesNumbers.put("Michael", null);
        System.out.println(favoritesNumbers);

        BiFunction<Integer, Integer, Integer> mapper = (initialValue, proposedValue) -> {
            System.out.println("        Initial value:  " + initialValue);
            System.out.println("        Proposed value: " + proposedValue);

            return initialValue >= proposedValue ? initialValue : proposedValue;
        };
        // 1. perform a remapping on the values of the existing (k,v) pairs
        System.out.println("Compute for Jenny");
        favoritesNumbers.merge("Jenny", 12, mapper);
        System.out.println("Compute for Tom");
        favoritesNumbers.merge("Tom", 44, mapper);

        // 2. add a (k,v) pair from the map in the case of Alexander and update the value from null to 99 in the case of Michael
        System.out.println("Compute for Alex");
        favoritesNumbers.merge("Alex", 99, mapper);
        System.out.println("Compute for Michael");
        favoritesNumbers.merge("Michael", 99, mapper);

        System.out.println(favoritesNumbers);

        // 3. removing a (k,v) pair from the map
        BiFunction<Integer, Integer, Integer> removerByKey = (initialValue, proposedValue) -> null;
        favoritesNumbers.merge("Jenny", 1066659, removerByKey);

        System.out.println(favoritesNumbers);
    }

    // 1. If map.get(key) returns a value, then the computation is performed on the existing value
    //
    // 2. If the value of the specified key is null -> basically if map.get(key) returns null
    //      -> the key might be present but the value null -> the proposed value is inserted for the mentioned key
    //      -> the key does not exist at all -> a new (k,v) pair is added to the map (the mentioned key with the proposed value)
    //
    // 3. If map.get(key) returns a value and if the mapper returns a null value after the computation, then the initial (k,v) pair is removed from the map
    private static void compute() {
        Map<String, Integer> nameCounts = new HashMap<>();
        nameCounts.put("Jenny", 1);
        nameCounts.put("Michael", null);
        System.out.println(nameCounts);

        BiFunction<String, Integer, Integer> bif = (key, value) -> {
            if (value == null) {
                return 1;
            } else {
                return value + 1;
            }
        };

        // 1. perform a computation on the values of the the existing (k,v) pairs
        nameCounts.compute("Jenny", bif);

        // 2. add a (k,v) pair from the map in the case of Sam and update the value from null to 1 in the case of Michael
        nameCounts.compute("Sam", bif);
        nameCounts.compute("Michael", bif);

        // 3. removing a (k,v) pair from the map
        BiFunction<String, Integer, Integer> removerByKey = (key, value) -> null;
        nameCounts.compute("Jenny", removerByKey);

        System.out.println(nameCounts);
    }

    // If the value is null or the key doesn't exist, nothing happens
    private static void computeIfPresent() {
        Map<String, Integer> nameCounts = new HashMap<>();
        nameCounts.put("Jenny", 1);
        nameCounts.put("Sam", null);
        System.out.println(nameCounts);

        // 1. perform a computation on the values of the the existing (k,v) pairs
        BiFunction<String, Integer, Integer> addOneForExisting = (key, value) -> value + 1;
        nameCounts.computeIfPresent("Jenny", addOneForExisting);

        // 2. if the value is null or the key doesn't exist, nothing happens
        nameCounts.computeIfPresent("Sam", addOneForExisting);
        nameCounts.computeIfPresent("Alex", addOneForExisting);
        System.out.println(nameCounts);

        // 3. removing a (k,v) pair from the map
        BiFunction<String, Integer, Integer> removerByKey = (key, value) -> null;
        nameCounts.computeIfPresent("Jenny", removerByKey);
        System.out.println(nameCounts);
    }

    // If the value is present, nothing happens
    private static void computeIfAbsent() {
        Map<String, Integer> nameCounts = new HashMap<>();
        nameCounts.put("Jenny", 1);
        nameCounts.put("Sam", null);
        System.out.println(nameCounts);

        Function<String, Integer> addOneForKey = key -> 1;
        // 1. if the value is present, nothing happens
        nameCounts.computeIfAbsent("Jenny", addOneForKey);

        // 2. add a (k,v) pair from the map in the case of Sam and update the value from null to 1 in the case of Michael
        nameCounts.computeIfAbsent("Sam", addOneForKey);
        nameCounts.computeIfAbsent("Alex", addOneForKey);

        // 3. removing a (k,v) pair from the map
        // nothing here because the computation is performed only on non-existing keys/values

        System.out.println(nameCounts);
    }
}
