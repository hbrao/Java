package language.collections;

import java.util.*;

public class Linked {
    public static void main(String[] args) {
        Deque<Integer> data = new LinkedList<>();
        //Stack
        data.addFirst(2); //Push
        data.addFirst(1);
        data.getFirst();     //Peek
        data.removeFirst();  //Pop

        //Queue
        data.addLast(1);  //Enqueue
        data.addLast(0);
        data.removeFirst();  //Dequeue

        System.out.println(data);
    }
}
