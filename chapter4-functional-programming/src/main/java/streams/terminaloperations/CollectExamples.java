package streams.terminaloperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static streams.terminaloperations.MyCollectors.toListUsingMyCollector;

public class CollectExamples {

    public static void main(String[] args) {
        CollectExamples examples = new CollectExamples();
//        examples.firstMethodSignature();
//        examples.secondMethodSignatureWithMyOwnCollectorAnonClass();
        examples.secondMethodSignatureWithMyOwnCustomCollector();

    }

    /**
     * <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
     */
    private void firstMethodSignature() {
        // When the stream is simple (not parallel) the combiner is no longer executed
        // just comment the .parallel() line
        ArrayList<Integer> collectedValues = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .collect(() -> new ArrayList<>(),
                        (list, integer) -> {
                            list.add(integer);
                            System.out.println("accumulated " + list);
                        },
                        (firstList, secondList) -> {
                            firstList.addAll(secondList);
                            System.out.println("combined " + firstList);
                        });
        System.out.println(collectedValues);

        System.out.println();
        System.out.println("Using method ref ***************");
        System.out.println();


        ArrayList<Integer> collected = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(collected);
    }

    /**
     * T the type of input elements to the reduction operation
     * A the mutable accumulation type of the reduction operation (often hidden as an implementation detail)
     * R the result type of the reduction operation
     * <R, A> R collect(Collector<? super T, A, R> collector)
     */
    private void secondMethodSignatureWithMyOwnCollectorAnonClass() {
        Collector<Integer, List<Integer>, List<Integer>> collector = new Collector<Integer, List<Integer>, List<Integer>>() {


            @Override
            public Supplier<List<Integer>> supplier() {
                return ArrayList::new;
            }

            @Override
            public BiConsumer<List<Integer>, Integer> accumulator() {
                return (list, integer) -> {
                    list.add(integer);
                    System.out.println("accumulated " + list);
                };
            }

            @Override
            public BinaryOperator<List<Integer>> combiner() {
                return (first, second) -> {
                    first.addAll(second);
                    System.out.println("combined " + first);
                    return first;
                };
            }

            @Override
            public Function<List<Integer>, List<Integer>> finisher() {
                return Function.identity();
            }

            @Override
            public Set<Characteristics> characteristics() {
                return Collections.emptySet();
            }
        };


        List<Integer> collect = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .collect(collector);

        System.out.println(collect);
    }

    private void secondMethodSignatureWithExistingCollectors() {
        Collector<Integer, ?, List<Integer>> objectArrayListCollector = Collectors.toCollection(ArrayList::new);


    }

    private void secondMethodSignatureWithMyOwnCustomCollector() {
        List<Integer> collect = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .collect(toListUsingMyCollector());
        System.out.println(collect);
    }


}

