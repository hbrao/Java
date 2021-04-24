package examples.stream;

import java.util.*;
import java.util.stream.*;

public class Streams {
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 11).toArray();
        System.out.println(Arrays.toString(arr));

        int[] arr2 = IntStream.range(0, 11).map( e -> 0 ).toArray();
        System.out.println(Arrays.toString(arr2));

        List<Integer> arrList = IntStream.range(10, 21).boxed().collect(Collectors.toList());
        System.out.println(arrList.stream().map( e -> e.toString()).collect(Collectors.joining(",")));

        IntSummaryStatistics stats =  arrList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats.getSum() + ";" + stats.getAverage());

        List<Integer> aL1 = new ArrayList<>(List.of(1, 2, 2, 3, 4, 4, 5));
        System.out.println("List contents: " + aL1);
        List<Integer> aL1sub1 = aL1.subList(0, 3);
        aL1sub1.set(0, 1000);
        System.out.println("List contents: " + aL1);
        System.out.println("Sub list contents: " + aL1sub1);

        int[] data = {1,2,2,3,4,4,5};

        List<Integer> arrList1 = IntStream.of(data).boxed().collect(Collectors.toList());
        arrList1.add(100);
        System.out.println("Array List = "+arrList1);

        List<Integer> linkedList = IntStream.of(data).boxed().collect(Collectors.toCollection(LinkedList::new));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        Set<Integer> set = IntStream.of(data).boxed().collect(Collectors.toSet());
        set.add(222);
        System.out.println("Set = "+set);

        SortedSet<Integer> sortedSet = IntStream.of(data).boxed().collect(Collectors.toCollection(TreeSet::new));
        set.add(222);
        System.out.println("Sorted Set = "+sortedSet);

        Map<Integer,String> hm1 = IntStream.of(data).boxed().distinct().collect(Collectors.toMap((i) -> i.intValue(), (i) -> i.toString()));
        System.out.println("Map 1 Distinct keys= "+hm1);

        Map<Integer, List<Integer>> hm2= IntStream.of(data).boxed().collect(Collectors.groupingBy(Integer::intValue));
        System.out.println("Map 2 Group repeated values into List= "+hm2);

        Map<Integer, Long> hm3= IntStream.of(data).boxed().collect(Collectors.groupingBy(Integer::intValue,Collectors.counting()));
        System.out.println("Map 3 Count occurrences of each element= "+hm3);


        Map<Integer, Set<String>> hm4= IntStream.of(data).boxed().collect(Collectors.groupingBy(
                Integer::intValue, //Classifier
                Collectors.mapping( (i) -> "'" +i.toString() + "'", Collectors.toSet()) //Collector
        ));
        System.out.println("Map 4 Group repeated values into Set= "+hm4);

        Map<Integer,Set<Integer>> hm5 = IntStream.of(data).boxed().collect(Collectors.groupingBy(
                Integer::intValue, //Classifier
                () ->  new TreeMap<> (Comparator.reverseOrder()), // Supplier
                Collectors.toSet() //Collector
        ));
        System.out.println("Map 5 (sorted reverse) Group repeated values into Set");
        hm5.entrySet().forEach( (Map.Entry<Integer,Set<Integer>> e) -> System.out.println(e.getKey() + " : " + e.getValue()) );
    }
}
