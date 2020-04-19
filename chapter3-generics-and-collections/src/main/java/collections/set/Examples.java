package collections.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Examples {

    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<>();
        numbers.add(66);
        numbers.add(10);
        numbers.add(8);
        numbers.forEach(System.out::println);

        System.out.println("*************************************");

        numbers = new TreeSet<>();
        numbers.add(66);
        numbers.add(10);
        numbers.add(8);
        numbers.forEach(System.out::println);
        System.out.println(numbers);

        System.out.println("*************************************");

        numbers = new LinkedHashSet<>();
        numbers.add(66);
        numbers.add(10);
        numbers.add(8);
        numbers.forEach(System.out::println);
        System.out.println(numbers);

        System.out.println("*************************************");

        // In the case of TreeSet:
        //      - The objects in the set must either implement Comparable or be naturally comparable
        //      - or a Comparator must provided on the Set's constructor
        // Otherwise, a ClassCastException is thrown: java.lang.Object cannot be cast to java.lang.Comparable
        Set<Object> elements = new TreeSet<>();
        elements.add(new Object());
        elements.add(new Object());
        elements.add(new Object());
        elements.forEach(System.out::println);
        System.out.println(elements);

    }
}
