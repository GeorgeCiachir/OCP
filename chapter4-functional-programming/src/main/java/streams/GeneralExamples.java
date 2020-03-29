package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GeneralExamples {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("ccc");

        Stream<String> stream = strings.stream();

        strings.add("ddd");
        stream.forEach(System.out::println);

        strings.add("e");

    }
}
