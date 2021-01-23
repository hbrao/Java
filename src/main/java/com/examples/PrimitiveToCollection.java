package com.examples;

import java.util.*;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.*;

public class PrimitiveToCollection {
    public static void main(String[] args) {

        //Method 1
        //NOTE: List.of Map.of Set.of etc. returns an immutable list.
        List<Integer> aL1 = new ArrayList<>(List.of(1, 2, 2, 3, 4, 4, 5));
        System.out.println("List contents: " + aL1);
        List<Integer> aL1sub1 = aL1.subList(0, 3);
        aL1sub1.set(0, 1000);
        System.out.println("List contents: " + aL1);
        System.out.println("Sub list contents: " + aL1sub1);

        //Method 2
        int[] data = {1,2,2,3,4,4,5};

        List<Integer> arrList1 = IntStream.of(data).boxed().collect(toList());
        arrList1.add(100);
        System.out.println("Array List = "+arrList1);

        List<Integer> linkedList = IntStream.of(data).boxed().collect(toCollection(LinkedList::new));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        Set<Integer> set = IntStream.of(data).boxed().collect(toSet());
        set.add(222);
        System.out.println("Set = "+set);

        SortedSet<Integer> sortedSet = IntStream.of(data).boxed().collect(toCollection(TreeSet::new));
        set.add(222);
        System.out.println("Sorted Set = "+sortedSet);

        Map<Integer,String> hm1 = IntStream.of(data).boxed().distinct().collect(toMap((i) -> i.intValue(), (i) -> i.toString()));
        System.out.println("Map 1 Distinct keys= "+hm1);

        Map<Integer, List<Integer>> hm2= IntStream.of(data).boxed().collect(groupingBy(Integer::intValue));
        System.out.println("Map 2 Group repeated values into List= "+hm2);

        Map<Integer, Long> hm3= IntStream.of(data).boxed().collect(groupingBy(Integer::intValue,counting()));
        System.out.println("Map 3 Count occurrences of each element= "+hm3);


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
    }
}
