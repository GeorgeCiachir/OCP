package collections.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Examples {

    public static void main(String[] args) {
        Queue<String> queue;

        // LinkedList implementation allows null values
        queue = new LinkedList<>();
        queue.add(null);

        // ArrayDeque implementation does not allow null values
        // because the poll(), peek() from the Queue/Deque interfaces return null to signal there is not element to peek/poll
        queue = new ArrayDeque<>();
        queue.add(null);


        Deque<String> dequeue;

        dequeue = new LinkedList<>();
        dequeue.add(null);

        dequeue = new ArrayDeque<>();
        dequeue.add(null);
    }
}
