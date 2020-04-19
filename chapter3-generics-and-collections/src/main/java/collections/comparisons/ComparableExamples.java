package collections.comparisons;

import java.util.*;

public class ComparableExamples {

    public static void main(String[] args) {
        List<ComparableAnimal> comparableAnimals = getTheComparableAnimals();

        usingTreeSetWithComparable(comparableAnimals);
        usingManuallyDefinedAdvancedComparator(comparableAnimals);
        usingStandardComparator();
    }

    private static void usingStandardComparator() {
        List<SimpleAnimal> animals = new ArrayList<>();
        animals.add(new SimpleAnimal());
        animals.add(new SimpleAnimal());
        // Collections.sort(animals); //Does not compile because SimpleAnimal does not implement Comparable
        //In this case, a Comparator should be passed
        Collections.sort(animals, Comparator.comparingInt(SimpleAnimal::hashCode));
        animals.sort(Comparator.comparing(SimpleAnimal::hashCode));

    }

    private static void usingTreeSetWithComparable(List<ComparableAnimal> animals) {
        System.out.println("*********************** usingTreeSetWithComparable ********************");
        System.out.println("The initial order");
        animals.forEach(System.out::println);

        System.out.println("After using a TreeSet -> it also removes the duplicates");
        TreeSet<ComparableAnimal> comparableAnimals = new TreeSet<>(animals);
        comparableAnimals.forEach(System.out::println);
    }

    private static void usingManuallyDefinedAdvancedComparator(List<ComparableAnimal> animals) {
        System.out.println("*********************** usingManuallyDefinedAdvancedComparator ********************");

        Comparator<ComparableAnimal> comparator = Comparator
                .comparing(ComparableAnimal::getId)
                .thenComparing(ComparableAnimal::getName)
                .thenComparingInt(ComparableAnimal::getAge);

        List<ComparableAnimal> comparableAnimals = new ArrayList<>(animals);

        System.out.println("The initial order");
        comparableAnimals.forEach(System.out::println);

        System.out.println("After sorting");
        comparableAnimals.sort(comparator);
        comparableAnimals.forEach(System.out::println);
    }

    private static List<ComparableAnimal> getTheComparableAnimals() {
        List<ComparableAnimal> comparableAnimals = new ArrayList<>();
        comparableAnimals.add(new ComparableAnimal(15, "Andrei", 10));
        comparableAnimals.add(new ComparableAnimal(8, "Mihai", 22));
        comparableAnimals.add(new ComparableAnimal(3, "Gigel", 30));
        comparableAnimals.add(new ComparableAnimal(3, "Alex", 30));
        comparableAnimals.add(new ComparableAnimal(8, "Mihai", 19));
        comparableAnimals.add(new ComparableAnimal(3, "Alex", 18));
        return comparableAnimals;
    }
}
