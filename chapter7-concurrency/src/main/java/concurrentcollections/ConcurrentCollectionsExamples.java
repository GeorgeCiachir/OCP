package concurrentcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentCollectionsExamples {

    public static void main(String[] args) throws InterruptedException {

//        concurrentSkipListSetAndMap();
//        copyOnWriteArrayListAndSet();
//        linkedBlockingQue();
        obtainingSynchronizedCollections();
    }

    /*
     * Basically, these are concurrent TreeSet and TreeMap
     */
    private static void concurrentSkipListSetAndMap() {
        Map<Integer, String> map = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
        map.put(3, "b");
        map.put(1, "d");
        map.put(10, "b");
        System.out.println(map);

        Set<String> strings = new ConcurrentSkipListSet<>(Set.of("d", "a", "e", "q"));
        System.out.println(strings);

        System.out.println("*************");

        for (int i = 0; i < strings.size(); i++) {
            System.out.println("size is: " + strings.size());
            if (i == 1) {
                strings.remove("e");
//                strings.add("z");
            }
        }

        System.out.println(strings);
    }

    private static void copyOnWriteArrayListAndSet() {
        List<String> strings = new CopyOnWriteArrayList<>(List.of("d", "a", "e", "q"));
        for (int i = 0; i < strings.size(); i++) {
            System.out.println("current element is: " + strings.get(i));
            if (i == 1) {
//                strings.remove("e");
                strings.add("z");
            }
        }
        System.out.println(strings);

        System.out.println("**********");

        List<Integer> integers = new CopyOnWriteArrayList<>(List.of(15, 60, 22));
        // because we use an iterator, it will iterate only on the initial list, even though
        // a new element is added on each iteration
        // so, it uses only the initial iterator -> only 3 iterations, not 6
        for (var v : integers) {
            integers.add(9);
            System.out.println("Current element is: " + v);
        }
        System.out.println(integers);
        System.out.println(integers.size());

        System.out.println("**********");
        // start again
        // this time, because instead of the list iterator, a counter is used and increased until
        // the max size of the list it will iterate over the newly added elements
        integers = new CopyOnWriteArrayList<>(List.of(15, 60, 22));
        for (int i = 0; i < integers.size(); i++) {
            if (integers.size() < 6) {
                integers.add(9);
            }
            System.out.println("Current element is: " + integers.get(i));
        }

        Set<String> set = new CopyOnWriteArraySet<>(Set.of("e"));
    }

    private static void linkedBlockingQue() throws InterruptedException {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(1);
        blockingQueue.offer(9);
        boolean offered = blockingQueue.offer(3, 1, TimeUnit.SECONDS);
        System.out.println("offered: " + offered);

        System.out.println("**********");

        blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.offer(9);
        blockingQueue.offer(3, 4, TimeUnit.SECONDS); //it hsa the needed capacity, so it won't wait
        System.out.println("First poll: " + blockingQueue.poll());
        System.out.println("Second poll: " + blockingQueue.poll(10, TimeUnit.MILLISECONDS));
        System.out.println("Third poll: " + blockingQueue.poll(10, TimeUnit.MILLISECONDS)); // no more elements
    }

    /*
     * Synchronized collections throw the same ConcurrentModificationException
     * if modified while iterating through them, using the Iterator
     */
    private static void obtainingSynchronizedCollections() {
        List<Integer> unSyncedList1 = new ArrayList<>(List.of(4, 3, 1));
        System.out.println("before: " + unSyncedList1);

        List<Integer> syncedList = Collections.synchronizedList(unSyncedList1);
        syncedList.add(5);
        System.out.println("after: " + syncedList);


        List<Integer> unSyncedList2 = List.of(4, 3, 1);
        syncedList = Collections.synchronizedList(unSyncedList2);
        try {
            syncedList.add(14); // UnsupportedOperationException -> same problem
        } catch (UnsupportedOperationException e) {
            System.out.println("The underlying collection is immutable: List.of");
        }

        List<Integer> unSyncedList3 = Arrays.asList(1, 5, 9);
        syncedList = Collections.synchronizedList(unSyncedList3);
        try {
            syncedList.add(14); // UnsupportedOperationException -> same problem
        } catch (UnsupportedOperationException e) {
            System.out.println("The underlying collection is immutable: Arrays.asList");
        }
    }
}
