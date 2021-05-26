package language.collections;

import java.util.*;
import java.util.stream.IntStream;

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

        //Priority queue using arrays.
        int[] capital = new int[] {2, 1, 4,  6, 8};
        int[] profit  = new int[] {3, 4, 3, 12, 10};
        PriorityQueue<Integer> minCapitalHeap = new PriorityQueue<>(capital.length, (idx1, idx2) -> {
           return Integer.compare(capital[idx1], capital[idx2]);
        });
        PriorityQueue<Integer> maxProfitHeap  = new PriorityQueue<>(capital.length, (idx1, idx2) -> {
           return Integer.compare(profit[idx2], profit[idx1]);
        });
        IntStream.range(0, capital.length).forEach( idx -> minCapitalHeap.add(idx) );
        IntStream.range(0, profit.length).forEach( idx -> maxProfitHeap.add(idx));
        System.out.println("MinCapitalHeap: " + capital[minCapitalHeap.peek()] + " ; MaxProfitHeap: " + profit[maxProfitHeap.peek()]);
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
