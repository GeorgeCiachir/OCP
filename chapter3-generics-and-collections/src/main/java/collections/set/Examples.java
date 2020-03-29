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

        //ClassCastException at runtime
        Set<Object> elements = new TreeSet<>();
        elements.add(new Object());
        elements.add(new Object());
        elements.add(new Object());
        elements.forEach(System.out::println);
        System.out.println(elements);

    }
}
