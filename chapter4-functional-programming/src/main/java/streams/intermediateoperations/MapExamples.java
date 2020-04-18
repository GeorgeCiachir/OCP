package streams.intermediateoperations;

import java.util.function.Function;
import java.util.stream.Stream;

public class MapExamples {

    public static void main(String[] args) {

    }



    /*
     * The function must accept <? super T> because of lower bounds
     *      if it was to accept <? extends T> a RuntimeException
     *      because the received object could be on a different inheritance branch then the expected one
     *
     *      for example -> Function<? extends Object, ? extends R> function means that the function
     *      could take an Integer and a String
     */
    private static <T, R> Stream<R> simulateMappingFunction(T t, Function<? super T, ? extends R> function) {
        R r = function.apply(t);
        return Stream.of(r);
    }
}
