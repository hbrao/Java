package com.examples;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveToCollection {
    public static void main(String[] args) {
        //Method 1
        int[] data = {1,2,2,3,4,4,5};

        List arrList = IntStream.of(data).boxed().collect(Collectors.toList());
        arrList.add(100);
        System.out.println("Array List = "+arrList);

        List linkedList = IntStream.of(data).boxed().collect(Collectors.toCollection(LinkedList::new));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        Set set = IntStream.of(data).boxed().collect(Collectors.toSet());
        set.add(222);
        System.out.println("Set = "+set);

        SortedSet sortedSet = IntStream.of(data).boxed().collect(Collectors.toCollection(TreeSet::new));
        set.add(222);
        System.out.println("Sorted Set = "+sortedSet);

        Map hm1 = IntStream.of(data).boxed().distinct().collect(Collectors.toMap((i) -> i.intValue(), (i) -> i.toString()));
        System.out.println("Map 1 = "+hm1);

        Map<Integer, Long> hm2= IntStream.of(data).boxed().collect(Collectors.groupingBy(Integer::intValue,Collectors.counting()));
        System.out.println("Map 2= "+hm2);

        Map<Integer, List<Integer>> hm3= IntStream.of(data).boxed().collect(Collectors.groupingBy(Integer::intValue));
        System.out.println("Map 3 = "+hm3);

        //Method 2
        List<Integer> aL1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println("List contents: " + aL1);
        List<Integer> aL1sub1 = aL1.subList(0, 3);
        aL1sub1.set(0, 1000);
        System.out.println("List contents: " + aL1);
        System.out.println("Sub list contents: " + aL1sub1);
    }
}
