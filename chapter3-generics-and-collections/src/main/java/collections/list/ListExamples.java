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
    }
}
