package language.collections;

import java.util.*;

public class Linked {
    public static void main(String[] args) {
        //LinkedList | ConcurrentLinkedDeque

        Deque<Integer> data = new LinkedList<>();

        //May throw NoSuchElementException when extracting element out.

        //Deque
        data.addLast(1);
        data.addFirst(0);
        data.peekFirst();
        data.peekLast();
        data.removeLast();
        data.removeFirst();

        //Stack
        data.push(2); // addFirst
        data.push(1);
        data.peek(); // peekFirst
        data.pop(); // removeFirst

        System.out.println(data);
    }
}
