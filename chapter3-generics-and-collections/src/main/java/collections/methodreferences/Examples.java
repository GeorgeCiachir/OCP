package collections.methodreferences;

import java.util.function.Predicate;

public class Examples {

    public static void main(String[] args) {
        Predicate<String> predicate;

        String str = "Gogu";
        predicate = str::startsWith;
        System.out.println(predicate.test("G"));

        predicate = String::isEmpty;
        System.out.println(predicate.test(""));

    }

}
