package patterns.topk;

import java.util.*;
import java.util.stream.*;

public class SortedLists {
    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = SortedLists.findKthSmallest(lists, 5);
        System.out.println("Kth smallest number is: " + result);

        Integer[] smallestRange = SortedLists.findSmallestRange(lists);
        System.out.println("Smallest range is: "+ Arrays.toString(smallestRange));
    }

    public static Integer findKthSmallest(List<Integer[]> lists, Integer k) {
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> lists.get(n.arrayIndex)[n.elemIndex]));
        IntStream.range(0, lists.size()).forEach( idx -> {
            minHeap.add(new Node(idx, 0));
        });

        Integer numElements = 0 , result = 0;
        while( ! minHeap.isEmpty() ) {
            Node n = minHeap.remove();
            result = lists.get(n.arrayIndex)[n.elemIndex];
            numElements += 1;
            if (numElements == k) break;

            n.elemIndex += 1;
            if( lists.get(n.arrayIndex).length > n.elemIndex ) {
                minHeap.add(n);
            }
        }
        return result;
    }

    public static Integer[] findSmallestRange(List<Integer[]> lists) {
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(n -> lists.get(n.arrayIndex)[n.elemIndex]));
        Integer rangeStart = 0 , rangeEnd = Integer.MAX_VALUE, currentMax = Integer.MIN_VALUE;
        for(Integer i = 0 ; i < lists.size() ; i ++ ) {
            minHeap.add(new Node(i, 0));
            currentMax = Math.max(currentMax, lists.get(i)[0]);
        }

        while( minHeap.size() == lists.size() ) {
            Node n = minHeap.remove();
            if( currentMax - lists.get(n.arrayIndex)[n.elemIndex] < rangeEnd - rangeStart ) {
                rangeStart = lists.get(n.arrayIndex)[n.elemIndex];
                rangeEnd = currentMax;
            }

            n.elemIndex += 1;
            if( n.elemIndex < lists.get(n.arrayIndex).length ) {
                minHeap.add(n);
                currentMax = Math.max(currentMax, lists.get(n.arrayIndex)[n.elemIndex]);
            }
        }

        return new Integer[]{rangeStart, rangeEnd};
    }

    public static class Node {
        Integer arrayIndex;
        Integer elemIndex;
        public  Node(Integer aIdx, Integer eIdx) {
            this.arrayIndex  = aIdx;
            this.elemIndex = eIdx;
        }
    }
}
