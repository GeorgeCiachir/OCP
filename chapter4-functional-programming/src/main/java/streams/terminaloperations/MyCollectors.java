package streams.terminaloperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MyCollectors {

    public static <T> Collector<T, ?, List<T>> toListUsingMyCollector() {
        Supplier<List<T>> listSupplier = ArrayList::new;
        BiConsumer<List<T>, T> howToAccumulate = List::add;
        BinaryOperator<List<T>> howToCombineForParallelStreams = (firstList, secondList) -> {
            firstList.addAll(secondList);
            return firstList;
        };

        return new CustomCollector<>(listSupplier, howToAccumulate, howToCombineForParallelStreams);
    }

}


class CustomCollector<T, A, R> implements Collector<T, A, R> {

    private final Supplier<A> whereToCollect;
    private final BiConsumer<A, T> howToAccumulate;
    private final BinaryOperator<A> howToCombineForParallelStreams;

    CustomCollector(Supplier<A> whereToCollect,
                            BiConsumer<A, T> howToAccumulate,
                            BinaryOperator<A> howToCombineForParallelStreams) {
        this.whereToCollect = whereToCollect;
        this.howToAccumulate = howToAccumulate;
        this.howToCombineForParallelStreams = howToCombineForParallelStreams;
    }


    @Override
    public Supplier<A> supplier() {
        return whereToCollect;
    }

    @Override
    public BiConsumer<A, T> accumulator() {
        return howToAccumulate;
    }

    @Override
    public BinaryOperator<A> combiner() {
        return howToCombineForParallelStreams;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Function<A, R> finisher() {
        return i -> (R) i;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return new HashSet<>();
    }
}
