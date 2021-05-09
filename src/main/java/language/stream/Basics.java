package language.stream;

import java.util.*;
import java.util.stream.*;

public class Basics {
    public static void main(String[] args) {
        //
        // IntStream
        //

        //0...n-1
        int[] arr = IntStream.range(0, 11).toArray();
        System.out.println(Arrays.toString(arr));

        //All 0's
        int[] arr2 = IntStream.range(0, 11).map( e -> 0 ).toArray();
        System.out.println(Arrays.toString(arr2));

        //
        //Primitive to collections
        //

        int[] data = {1,2,2,3,4,4,5};
        List<Integer> arrList1 = IntStream.of(data).boxed().collect(Collectors.toList());
        arrList1.add(100);
        System.out.println("Array List = "+arrList1);

        List<Integer> linkedList = IntStream.of(data).boxed().collect(Collectors.toCollection(() -> new LinkedList<>()));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        Set<Integer> set = IntStream.of(data).boxed().collect(Collectors.toSet());
        set.add(222);
        System.out.println("Set = "+set);

        SortedSet<Integer> sortedSet = IntStream.of(data).boxed().collect(Collectors.toCollection(() ->  new TreeSet<>()));
        set.add(222);
        System.out.println("Navigable Set = "+sortedSet);

        Map<Integer,String> hm1 = IntStream.of(data).boxed()
                                           .distinct()
                                           .collect(Collectors.toMap(
                                                              (i) -> i.intValue() //Key mapper
                                                            , (i) -> i.toString() //Value mapper
                                                            , (old_value, new_value) -> new_value //Merge function
                                                   )
                                           );
        System.out.println("Map 1 Distinct keys= "+hm1);

        //
        //Collectors.groupingBy (Default output structure = Map; Collector = List)
        //

        Map<Integer, List<Integer>> hm2= IntStream.of(data).boxed().collect(Collectors.groupingBy(e -> e.intValue()));
        System.out.println("Map 2 Group repeated values into List= "+hm2);

        Map<Integer, Set<String>> hm4= IntStream.of(data).boxed().collect(Collectors.groupingBy(
                e -> e.intValue(), //Define classifier (Key)
                Collectors.mapping( (i) -> "'" +i.toString() + "'", Collectors.toSet()) //Specify collector (Values).
        ));
        System.out.println("Map 4 Group repeated values into Set= "+hm4);

        Map<Integer,Set<Integer>> hm5 = IntStream.of(data).boxed().collect(Collectors.groupingBy(
                Integer::intValue, // Define classifier (Key) //TODO Why can't I use e -> e.intValue()
                () ->  new TreeMap<> (Comparator.reverseOrder()), //Change output Structure
                Collectors.toSet() //Specify collector (Values)
        ));
        System.out.println("Map 5 (sorted reverse) Group repeated values into Set");
        hm5.entrySet().forEach( (Map.Entry<Integer,Set<Integer>> e) -> System.out.println(e.getKey() + " : " + e.getValue()) );

        Map<Integer, Long> hm3= IntStream.of(data).boxed().collect(Collectors.groupingBy( e -> e.intValue(), Collectors.counting()));
        System.out.println("Map 3 Count occurrences of each element= "+hm3);

        //
        // Statistics
        //

        List<Integer> arrList = IntStream.range(10, 21).boxed().collect(Collectors.toList());

        System.out.println("Sum =" + arrList.stream().collect(Collectors.summingInt( e -> e )));

        IntSummaryStatistics stats =  arrList.stream().collect(Collectors.summarizingInt( e -> e ));
        System.out.println("Sum = " + stats.getSum() + "; Average = " + stats.getAverage());

    }
}
