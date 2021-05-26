package patterns.twoheap;

import java.util.*;

public class NumberStream {
    private Queue<Integer> minHeap = new PriorityQueue<>();
    private Queue<Integer> maxHeap = new PriorityQueue<>(( e1, e2) -> e2.compareTo(e1));

    public void insertNum(Integer num) {
        if( maxHeap.isEmpty() || maxHeap.peek() >= num ) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        //Balance heaps.
        if ( maxHeap.size() > minHeap.size() + 1 ) {
            minHeap.add(maxHeap.poll());
        } else if ( maxHeap.size() < minHeap.size() ) {
            maxHeap.add(minHeap.poll());
        }
    }

    public  Double findMedian() {
        if ( maxHeap.size() == minHeap.size() ) {
            return ( maxHeap.peek() + minHeap.peek() ) / 2.0 ;
        } else {
            return  Double.valueOf(maxHeap.peek());
        }
    }

    public static void main(String[] args) {
        NumberStream medianOfAStream = new NumberStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}
