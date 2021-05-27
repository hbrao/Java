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
        // Arrays to collections
        //

        char[] chArray = new char[] {'h', 'e', 'l', 'l', 'o'};
        List<Character> charList = String.valueOf(chArray).chars().mapToObj( val -> (char) val).collect(Collectors.toList());
        System.out.println(charList);

        int[] data = {1,2,2,3,4,4,5};
        List<Integer> arrList1 = Arrays.stream(data).boxed().collect(Collectors.toList());
        arrList1.add(100);
        System.out.println("Array List = "+arrList1);

        Set<Integer> set = Arrays.stream(data).boxed().collect(Collectors.toSet());
        set.add(222);
        System.out.println("Set = "+set);

        Map<Integer,String> hm1 = Arrays.stream(data, 0, data.length / 2).boxed()
                                           .distinct()
                                           .collect(Collectors.toMap(
                                                              (i) -> i.intValue() //Key mapper
                                                            , (i) -> i.toString() //Value mapper
                                                            , (old_value, new_value) -> new_value //Merge function
                                                   )
                                           );
        System.out.println("Distinct keys= "+hm1);

        Map<Integer, Set<String>> hm4= Arrays.stream(data).boxed().collect(Collectors.groupingBy(
                (Integer i) -> i.intValue(), //Define key classifier
                Collectors.mapping( // Define value mapping logic
                    (Integer i) -> String.valueOf(i), // Value mapper
                    Collectors.toSet() //Values Collector
                )
        ));
        System.out.println("Group repeated values into HashMap = " + hm4);

        Map<Integer, Long> hm3= Arrays.stream(data).boxed().collect(Collectors.groupingBy( (Integer i) -> i.intValue(), Collectors.counting()));
        System.out.println("Group keys with their counts = " + hm3);

        //
        //Collect into a desired java collection.
        //

        List<Integer> linkedList = Arrays.stream(data).boxed().collect(Collectors.toCollection(() -> new LinkedList<>()));
        linkedList.add(111);
        System.out.println("Linked List = "+linkedList);

        SortedSet<Integer> sortedSet = Arrays.stream(data).boxed().collect(Collectors.toCollection(() ->  new TreeSet<>( Comparator.comparing( i -> i.intValue() ))));
        set.add(222);
        System.out.println("Navigable Set = "+sortedSet);

        Map<Integer,Set<String>> hm5 = Arrays.stream(data).boxed().collect(Collectors.groupingBy(
                (Integer i) -> i.intValue(), // Define key classifier
                () ->  new TreeMap<> (Comparator.comparing( i -> i.intValue() )), //Specify map factory returning desired concrete map instance.
                Collectors.mapping( // Define value mapping logic
                    (Integer i) -> String.valueOf(i), // Value mapper
                    Collectors.toSet() // Values Collector
                )
        ));
        System.out.println("Group repeated values into TreeMap");
        hm5.entrySet().forEach( (Map.Entry<Integer,Set<String>> e) -> System.out.println(e.getKey() + " : " + e.getValue()) );

        //
        // Statistics
        //

        List<Integer> arrList = IntStream.range(10, 21).boxed().collect(Collectors.toList());

        System.out.println("Sum =" + arrList.stream().collect(Collectors.summingInt( e -> e )));

        IntSummaryStatistics stats =  arrList.stream().collect(Collectors.summarizingInt( e -> e ));
        System.out.println("Sum = " + stats.getSum() + "; Average = " + stats.getAverage());

    }
}
