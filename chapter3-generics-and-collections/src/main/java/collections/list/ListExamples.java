package collections.list;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ListExamples {

    public static void main(String[] args) {
        binarySearch();
    }

    private static void binarySearch() {
        int[] intArray = {1, 4, 7, 9};
        System.out.println(Arrays.binarySearch(intArray, 1));
        System.out.println(Arrays.binarySearch(intArray, 2));

        List<Integer> integers = Arrays.stream(intArray).boxed().collect(toList());
        System.out.println(Collections.binarySearch(integers, -2));

        SomeClass[] elements = {new SomeClass(2), new SomeClass(1), new SomeClass(3)};
        SomeClass someClass = new SomeClass(2);
        System.out.println(Arrays.binarySearch(elements, someClass)); //ClassCastException: collections.list.SomeClass cannot be cast to java.lang.Comparable
    }

}

class SomeClass {

    private int value;

    SomeClass(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SomeClass someClass = (SomeClass) o;

        return value == someClass.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
