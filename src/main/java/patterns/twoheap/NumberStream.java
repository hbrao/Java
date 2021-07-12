package patterns.twoheap;

import java.util.*;

public class NumberStream {
    private Queue<Integer> leftMaxHeap = new PriorityQueue<>((e1, e2) -> e2.compareTo(e1));
    private Queue<Integer> rightMinHeap = new PriorityQueue<>();

    public void insertNum(Integer num) {
        if( leftMaxHeap.isEmpty() || leftMaxHeap.peek() >= num ) {
            leftMaxHeap.add(num);
        } else {
            rightMinHeap.add(num);
        }

        //Balance heaps.
        if ( leftMaxHeap.size() > rightMinHeap.size() + 1 ) {
            rightMinHeap.add(leftMaxHeap.poll());
        } else if ( leftMaxHeap.size() < rightMinHeap.size() ) {
            leftMaxHeap.add(rightMinHeap.poll());
        }
    }

    public  Double findMedian() {
        if ( leftMaxHeap.size() == rightMinHeap.size() ) {
            return ( leftMaxHeap.peek() + rightMinHeap.peek() ) / 2.0 ;
        } else {
            return  Double.valueOf(leftMaxHeap.peek());
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
