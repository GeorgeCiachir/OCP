package concurrentcollections;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrentCollectionsExamples {

    public static void main(String[] args) {

        Map<String, String> map = new ConcurrentSkipListMap<>();
        Set<String> set = new ConcurrentSkipListSet<>();

        List<String> list = new CopyOnWriteArrayList<>();
        Set<String> anotherSet = new CopyOnWriteArraySet<>();

        obtainingSynchronizedCollections();
    }

    private static void obtainingSynchronizedCollections() {
        List<Integer> unSyncedList = new ArrayList<>();
        unSyncedList.add(4);
        unSyncedList.add(3);
        unSyncedList.add(1);

        List<Integer> syncedList = Collections.synchronizedList(unSyncedList);
        syncedList.add(5);


        List<Integer> anotherUnSyncedList = Arrays.asList(1,5,9);
        syncedList = Collections.synchronizedList(anotherUnSyncedList);
        syncedList.add(14); // UnsupportedOperationException -> same problem
    }
}
