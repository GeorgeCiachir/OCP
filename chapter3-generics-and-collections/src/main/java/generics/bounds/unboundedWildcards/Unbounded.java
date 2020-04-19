package generics.bounds.unboundedWildcards;

import java.util.ArrayList;
import java.util.List;

public class Unbounded {

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Alex"));
        animals.add(new Animal("Michael"));

        takesUnboundedList(animals);
        takesRawList(animals);
        logicallyImmutableList();
    }

    private static void takesUnboundedList(List<?> objects) {
        objects.forEach(System.out::println);
    }

    private static void takesRawList(List objects) {
        objects.forEach(System.out::println);
    }

    private static void logicallyImmutableList() {
        List<?> elements = new ArrayList<>();
        // elements.add(new Object());//does not compile

        // The elements list is logically immutable -> same explanation as for the upper bounds
        // It's like List<? extends Object> -> could be anything
    }
}
