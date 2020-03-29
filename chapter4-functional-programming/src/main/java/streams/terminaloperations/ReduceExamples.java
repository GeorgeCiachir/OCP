package streams.terminaloperations;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class ReduceExamples {

    public static void main(String[] args) {
        ReduceExamples examples = new ReduceExamples();
//        examples.firstMethodSignature();
//        examples.secondMethodSignature();
//        examples.thirdMethodSignature();
        examples.thirdMethodSignatureSimulateListCollector();
    }


    /**
     * because the accumulation starts from the identity, a return value is always guaranteed
     * <p>
     * T reduce(T identity, BinaryOperator<T> accumulator)
     */
    private void firstMethodSignature() {
        Integer sum = Stream.iterate(1, i -> i + 1)
                .peek(i -> System.out.println(i))
                .limit(3)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        String concatenatedString = Stream.of("this", " ", "has", " ", "been", " ", "concatenated")
                .reduce("", String::concat);
        System.out.println(concatenatedString);

    }

    /**
     * because the identity is no longer present, a return value is no longer
     * guaranteed, therefore an Optional is returned
     * <p>
     * Optional<T> reduce(BinaryOperator<T> accumulator)
     */
    private void secondMethodSignature() {
        Stream.iterate(1, i -> i + 1)
                .limit(3)
                .mapToInt(e -> e)
                .reduce((first, second) -> first + second)
                .ifPresent(value -> System.out.println(value));

        Stream.of("this", " ", "has", " ", "been", " ", "concatenated")
                .reduce(String::concat)
                .ifPresent(value -> System.out.println(value));
    }

    /**
     * good for parallel streams
     * the accumulator is used to tell how to accumulate the all the elements of a substream
     * the combiner is used to tell how to combine the results of each stream
     * <p>
     * T<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)
     */
    private void thirdMethodSignature() {
        BiFunction<Integer, Integer, Integer> multiplyingAccumulator = (first, second) -> first * second;
        BinaryOperator<Integer> multiplyingCombiner = (first, second) -> first * second;

        Integer multiplyAll = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .reduce(1, multiplyingAccumulator, multiplyingCombiner);
        System.out.println(multiplyAll);


        BiFunction<String, String, String> simulateAddAccumulator = (firstValue, secondValue) -> "(" + firstValue + " + " + secondValue + ")";
        BinaryOperator<String> simulateMultiplicationCombiner = (firstStream, secondStream) -> "\n\t[" + firstStream + " combine with " + secondStream + "]\n\t";
        String value = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .map(i -> "" + i)
                .reduce("0", simulateAddAccumulator, simulateMultiplicationCombiner);

        System.out.println(value);
    }

    private void thirdMethodSignatureSimulateListCollector() {

        ArrayList<Integer> reduce = Stream.iterate(1, i -> i + 1)
                .parallel()
                .limit(10)
                .reduce(new ArrayList<>(),
                        (list, integer) -> {
                            ArrayList<Integer> copy = new ArrayList<>(list);
                            copy.add(integer);
                            System.out.println("accumulator " + copy);
                            return copy;
                        },
                        (list, secondList) -> {
                            list.addAll(secondList);
                            System.out.println("combiner " + list);
                            return list;
                        }
                );
        System.out.println(reduce);
    }

}
