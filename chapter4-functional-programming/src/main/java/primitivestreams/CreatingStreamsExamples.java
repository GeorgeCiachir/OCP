package primitivestreams;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreatingStreamsExamples {

    public static void main(String[] args) {
//        foo();

        Stream<Integer> ints = Stream.of(1);
        IntStream is = ints.mapToInt(i -> i);
        DoubleStream ds = is.mapToDouble(i -> i);

//        Stream<Integer> backToInt = ds.mapToInt(i -> (int) i);

    }


    private static void foo() {
        DoubleStream.builder()
                .add(2)
                .build()
                .forEach(System.out::println);

        DoubleStream doubleStream = DoubleStream.empty();
        DoubleStream ds = DoubleStream.generate(Math::random);

        Stream.of("ccc", "ddd")
                .flatMapToInt(e -> IntStream.of(e.length()))
                .forEach(System.out::println);
    }


}
