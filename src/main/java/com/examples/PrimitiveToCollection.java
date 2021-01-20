package com.examples;

import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class PrimitiveToCollection {
    public static void main(String[] args) {
        //Method 1
        int[] data = {1,2,2,3,4,4,5};

        List arrList = IntStream.of(data).boxed().collect(toList());
        arrList.add(100);
        System.out.println("Array List = "+arrList);

        List linkedList = IntStream.of(data).boxed().collect(toCollection(LinkedList::new));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        Set set = IntStream.of(data).boxed().collect(toSet());
        set.add(222);
        System.out.println("Set = "+set);

        SortedSet sortedSet = IntStream.of(data).boxed().collect(toCollection(TreeSet::new));
        set.add(222);
        System.out.println("Sorted Set = "+sortedSet);

        Map hm1 = IntStream.of(data).boxed().distinct().collect(toMap((i) -> i.intValue(), (i) -> i.toString()));
        System.out.println("Map 1 Distinct keys= "+hm1);

        Map<Integer, Long> hm2= IntStream.of(data).boxed().collect(groupingBy(Integer::intValue,counting()));
        System.out.println("Map 2 Count occurrences of each element= "+hm2);

        Map<Integer, List<Integer>> hm3= IntStream.of(data).boxed().collect(groupingBy(Integer::intValue));
        System.out.println("Map 3 Group repeated values into List= "+hm3);

        Map<Integer, Set<String>> hm4= IntStream.of(data).boxed().collect(groupingBy(
                Integer::intValue,
                mapping( (i) -> "'" +i.toString() + "'", toSet())
        ));
        System.out.println("Map 4 Group repeated values into Set= "+hm4);

        Map<Integer,Set<Integer>> hm5 = IntStream.of(data).boxed().collect(groupingBy(
                Integer::intValue,
                () ->  new TreeMap<> (Comparator.reverseOrder()),
                toSet()
        ));
        System.out.println("Map 5 (sorted reverse) Group repeated values into Set");
        hm5.entrySet().forEach( (Map.Entry<Integer,Set<Integer>> e) -> System.out.println(e.getKey() + " : " + e.getValue()) );

        //Method 2
        List<Integer> aL1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        System.out.println("List contents: " + aL1);
        List<Integer> aL1sub1 = aL1.subList(0, 3);
        aL1sub1.set(0, 1000);
        System.out.println("List contents: " + aL1);
        System.out.println("Sub list contents: " + aL1sub1);
    }
}
