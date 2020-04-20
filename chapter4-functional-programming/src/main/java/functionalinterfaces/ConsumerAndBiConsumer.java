package functionalinterfaces;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class ConsumerAndBiConsumer {

    public static void main(String[] args) {
        ConsumerAndBiConsumer consumerAndBiConsumer = new ConsumerAndBiConsumer();

        Map<Integer, Integer> initial = new HashMap<>();
        initial.put(1, 1);
        initial.put(2, 1);
        initial.put(3, 1);
        initial.put(4, 1);

        Map<Integer, Integer> newMap = new HashMap<>();
        BiConsumer<Integer, Integer> biConsumer = (k, v) -> newMap.put(k, v);

        System.out.println(newMap);
        initial.forEach(biConsumer);
        System.out.println(newMap);


        Map<Integer, Integer> anotherNewMap = new HashMap<>();
        BiConsumer<Integer, Integer> anotherBiConsumer = (k, v) -> anotherNewMap.put(k, v);

        System.out.println(anotherNewMap);
        initial.forEach((k, v) -> consumerAndBiConsumer.add(k, v, anotherBiConsumer));
        System.out.println(anotherNewMap);

    }

    private <K, V> void add(K k, V v, BiConsumer<K, V> biConsumer) {
        biConsumer.accept(k, v);
    }
}
