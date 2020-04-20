package streams.intermediateoperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedExamples {

    public static void main(String[] args) {
        simpleSort();
        sortUsingComparators();
        sortingParallelStreams();
    }

    private static void simpleSort() {
        getComparableObjects().stream()
                .sorted()
                .forEach(System.out::print);

        getNonComparableObjects().stream()
                // ClassCastException: streams.intermediateoperations.SomethingThatDoesNotImplementComparable cannot be cast to java.lang.Comparable
                // This also happens on Collections.sort() if the elements do not implement Comparable or the Comparator is not provided
                .sorted()
                .forEach(System.out::print);
    }

    private static void sortUsingComparators() {
        getComparableObjects().stream()
                .sorted((x, y) -> y.getId() - x.getId())
                .sorted(Comparator.comparingInt(ComparableObject::getId).reversed())
                .sorted(Comparator.reverseOrder())
                .sorted(Collections.reverseOrder())
                .sorted(reverseOrderComparator())
//                .sorted(Comparator::reverseOrder)
                .forEach(System.out::print);
    }

    private static void sortingParallelStreams() {
        getComparableObjects()
                .parallelStream()
                .sorted()
                .forEach(element -> System.out.print("element " + element + " ; "));
    }

    private static List<ComparableObject> getComparableObjects() {
        return Arrays.asList(
                new ComparableObject(9), new ComparableObject(15),
                new ComparableObject(8), new ComparableObject(1),
                new ComparableObject(3), new ComparableObject(2));
    }

    private static List<NonComparableObject> getNonComparableObjects() {
        return Arrays.asList(
                new NonComparableObject(9), new NonComparableObject(15),
                new NonComparableObject(8), new NonComparableObject(1),
                new NonComparableObject(3), new NonComparableObject(2));
    }

    private static <T extends Comparable<T>> Comparator<T> reverseOrderComparator() {

        return (x, y) -> y.compareTo(x);
    }
}

class ComparableObject implements Comparable<ComparableObject> {

    private final int id;

    ComparableObject(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(ComparableObject other) {
        return this.getId() - other.getId();
    }

    @Override
    public String toString() {
        return "" + id;
    }

    int getId() {
        return id;
    }
}

class NonComparableObject {

    private final int id;

    NonComparableObject(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    int getId() {
        return id;
    }
}
