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

        //LinkedList
        data.add(10);
        data.add(12);
        data.add(20);
        data.remove(12);

        System.out.println(data);
        Iterator<Integer> iter = data.descendingIterator();
        StringBuilder sb = new StringBuilder("[");
        while(iter.hasNext()) {
            sb.append(iter.next() + ", ");
        }
        sb.setLength(sb.length() - 2);
        System.out.println( sb + "]");

    }
}
