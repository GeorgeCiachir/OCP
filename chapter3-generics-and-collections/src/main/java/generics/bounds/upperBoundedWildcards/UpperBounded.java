package generics.bounds.upperBoundedWildcards;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class UpperBounded {

    public static void main(String[] args) {

        List<Bird> birds = asList(new Bird("Alex"), new Sparrow("a sparrow"), new Stork("a stork"));
        List<Sparrow> sparrows = singletonList(new Sparrow("a sparrow"));
        List<Stork> storks = singletonList(new Stork("a stork"));
        printBirdsAndDescendents(birds);
        printBirdsAndDescendents(sparrows);
        printBirdsAndDescendents(storks);

        List<Integer> numbers = asList(1, 2);
        calculateTotal(numbers);

        logicallyImmutableList();
    }

    private static void logicallyImmutableList() {
        List<? extends Bird> logicallyImmutableBirds = new ArrayList<>();
        // The following lines do not compile because from Java's perspective, List<? extends Bird> could be List<Sparrow> and we can't add
        // a random Bird (that could actually be an instance of a Stork), to the Sparrow list
        // Basically, Java prevents a ClassCastException
        // At this point, the birds list has become logically immutable (elements cannot be added and there are no elements to remove),
        // unless we pass a collection of birds at creation, using the constructor (this would mean that we could now at least remove elements)
//		logicallyImmutableBirds.add(new Bird());
//		logicallyImmutableBirds.add(new Sparrow());
//		logicallyImmutableBirds.add(new Stork());

        // This is a bad idea. It is in fact exactly what Java tries to prevent by making the list logically immutable
        List<Bird> randomBirds = new ArrayList<>();
        randomBirds.add(new Bird("random bird"));
        randomBirds.add(new Sparrow("random sparrow"));
        randomBirds.add(new Stork("random stork"));
        randomBirds.addAll(singletonList(new Sparrow("a sparrow")));
        randomBirds.addAll(singletonList(new Stork("a stork")));

        logicallyImmutableBirds = new ArrayList<>(randomBirds);
        System.out.println(logicallyImmutableBirds.get(4).getClass());
        Sparrow failingSparrowCast = (Sparrow) logicallyImmutableBirds.get(4); //This throws ClassCastException: Stork cannot be cast to Sparrow
    }

    private static void printBirdsAndDescendents(List<? extends Bird> birds) {
        for (Bird bi : birds) {
            System.out.println(bi);
        }
    }

    private static void calculateTotal(List<? extends Number> numbers) {
        long sum = numbers.stream()
                .mapToLong(Number::longValue)
                .sum();

        System.out.println(sum);
    }
}
