package language.collections;

import java.util.*;

public class Prioritized {
    public static void main(String[] args) {
        //PriorityQueue | PriorityBlockingQueue
        Queue<Edge> queue = new PriorityQueue<>((e1, e2) -> {
            return e2.w.compareTo(e1.w); //Max heap
        });

        //Add
        queue.add(new Edge(1,2,2));
        queue.add(new Edge(1,3,3));
        queue.add(new Edge(1,4,5));
        queue.add(new Edge(2,4,3));
        queue.add(new Edge(2,3,1));
        queue.add(new Edge(2,5,4));

        //Peek
        System.out.println(queue.peek());

        //Descending order
        while ( ! queue.isEmpty() ) System.out.print(queue.remove().w +" ");
        System.out.println();
    }

    static class Edge {
        Integer s, e , w;

        public Edge(Integer s, Integer e) {
            this(s, e, 1);
        }

        public Edge(Integer s, Integer e, Integer w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }
    }
}
