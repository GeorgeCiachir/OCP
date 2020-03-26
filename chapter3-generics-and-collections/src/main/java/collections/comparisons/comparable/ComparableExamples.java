package collections.comparisons.comparable;

import java.util.*;

public class ComparableExamples {

    public static void main(String[] args) {

        Comparator<ComparableAnimal> comparator = Comparator
                .comparing(ComparableAnimal::getId)
                .thenComparing(ComparableAnimal::getName)
                .thenComparingInt(ComparableAnimal::getAge);

        List<ComparableAnimal> comparableAnimals = new ArrayList<>();
        ComparableAnimal first =  new ComparableAnimal(15, "Andrei", 10);
        ComparableAnimal second =  new ComparableAnimal(8, "Mihai", 22);
        ComparableAnimal third =  new ComparableAnimal(3, "Gigel", 30);
        ComparableAnimal fourth =  new ComparableAnimal(3, "Alex", 30);
        ComparableAnimal fifth =  new ComparableAnimal(8, "Mihai", 19);
        ComparableAnimal sixth =  new ComparableAnimal(3, "Alex", 18);


        comparableAnimals.add(first);
        comparableAnimals.add(second);
        comparableAnimals.add(third);
        comparableAnimals.add(fourth);
        comparableAnimals.add(fifth);
        comparableAnimals.add(sixth);

        System.out.println("Before sorting");
        comparableAnimals.forEach(System.out::println);
        System.out.println();
        comparableAnimals.sort(comparator);
        System.out.println();
        System.out.println("After sorting");
        comparableAnimals.forEach(System.out::println);


        List<SimpleAnimal> animals = new ArrayList<>();
        animals.add(new SimpleAnimal());
        animals.add(new SimpleAnimal());
        // Collections.sort(animals); //Does not compile because SimpleAnimal does not implement Comparable
        //In this case, a Comparator should be passed
        Collections.sort(animals, Comparator.comparingInt(SimpleAnimal::hashCode));


    }
}
