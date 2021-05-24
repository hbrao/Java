package language.collections;

import java.util.*;

public class Linked {
    public static void main(String[] args) {
        //LinkedList | ConcurrentLinkedDeque

        LinkedList<Integer> data = new LinkedList<>(List.of(1, 2, 3, 4));

        //Deque
        Deque<Integer> deque = data;
        deque.addLast(1);
        deque.addFirst(0);
        deque.peekFirst();
        deque.peekLast();
        deque.removeLast();
        deque.removeFirst();

        //Queue
        Queue<Integer> queue = data;
        queue.add(10); // addLast
        queue.peek(); // peekFirst
        queue.remove(); // removeFirst

        //Stack
        deque.push(2); // addFirst
        deque.peek(); // peekFirst
        deque.pop(); // removeFirst

        //List
        List<Integer> list = data;
        list.add(10); // addLast
        list.add(0, 12); // addFirst
        list.remove(0); //removeFirst
        list.remove(list.size() - 1); // removeLast


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
