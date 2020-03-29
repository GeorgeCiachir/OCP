package streams.intermediateoperations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortedExamples {

    public static void main(String[] args) {
        SortedExamples examples = new SortedExamples();
//        examples.firstMethodSignature();
//        examples.secondMethodSignatureTakesAComparator();
        examples.usingParallelStreamsAndSorted();
    }

    private void firstMethodSignature() {
        getListOfSomethingThatImplementsComparable().stream()
                .sorted()
                .forEach(System.out::print);

        getListOfSomethingThatDoesNotImplementComparable().stream()
                // ClassCastException: streams.intermediateoperations.SomethingThatDoesNotImplementComparable cannot be cast to java.lang.Comparable
                // This also happens on Collections.sort() if the elements do not implement Comparable or the Comparator is not provided
                .sorted()
                .forEach(System.out::print);
    }

    private void secondMethodSignatureTakesAComparator() {
        getListOfSomethingThatImplementsComparable().stream()
                .sorted((x, y) -> y.getI() - x.getI())
                .sorted(Comparator.comparingInt(SomethingThatImplementsComparable::getI).reversed())
                .sorted(Comparator.reverseOrder())
                .sorted(Collections.reverseOrder())
                .sorted(this.reverseOrderComparator())
//                .sorted(Comparator::reverseOrder)
                .forEach(System.out::print);
    }

    private void usingParallelStreamsAndSorted() {
        getListOfSomethingThatImplementsComparable().parallelStream()
                .sorted((x ,y) -> {
                    System.out.println("comparing " + x + " and " + y);
                    return x.compareTo(y);
                })
                .forEach(element -> System.out.print("element " + element + " ; "));

        System.out.println();

        getListOfSomethingThatImplementsComparable().stream()
                .sorted()
                .forEach(element -> System.out.print("element " + element + " ; "));
    }

    private List<SomethingThatImplementsComparable> getListOfSomethingThatImplementsComparable() {
        return Arrays.asList(new SomethingThatImplementsComparable(9), new SomethingThatImplementsComparable(15),
                new SomethingThatImplementsComparable(8), new SomethingThatImplementsComparable(1),
                new SomethingThatImplementsComparable(3), new SomethingThatImplementsComparable(2));
    }

    private List<SomethingThatDoesNotImplementComparable> getListOfSomethingThatDoesNotImplementComparable() {
        return Arrays.asList(new SomethingThatDoesNotImplementComparable(9), new SomethingThatDoesNotImplementComparable(15),
                new SomethingThatDoesNotImplementComparable(8), new SomethingThatDoesNotImplementComparable(1),
                new SomethingThatDoesNotImplementComparable(3), new SomethingThatDoesNotImplementComparable(2));
    }

    private <T extends Comparable<T>> Comparator<T> reverseOrderComparator() {

        return (x, y) -> y.compareTo(x);
    }
}


class SomethingThatImplementsComparable implements Comparable<SomethingThatImplementsComparable> {

    private int i;

    SomethingThatImplementsComparable(int i) {
        this.i = i;
    }

    @Override
    public int compareTo(SomethingThatImplementsComparable other) {
        return this.getI() - other.getI();
    }

    @Override
    public String toString() {
        return "" + i;
    }

    int getI() {
        return i;
    }
}

class SomethingThatDoesNotImplementComparable {

    private int i;

    SomethingThatDoesNotImplementComparable(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "" + i;
    }

    int getI() {
        return i;
    }
}
