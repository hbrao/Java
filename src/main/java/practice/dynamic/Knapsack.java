package practice.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Knapsack {
    public static void main(String[] args) {
        //NOTE: Adding 0 as the first element in all collections This is to represent 0 capacity results in 0 profit
        //Item weights & profits
        List<Integer> wt = List.of(0, 1, 2, 3, 4);
        List<Integer> pr = List.of(0, 3, 4, 5, 7);

        //Capacity distribution for knapsack size of 5
        Integer knapsackSize = 5 ;
        List<Integer> cp = IntStream.range(0, knapsackSize + 1).boxed().collect(Collectors.toList());

        //Initialize matrix
        List<Integer> zeros = IntStream.range(0, 6).map(x -> 0).boxed().collect(Collectors.toList());
        Map<Integer,List<Integer>> dp = new HashMap<>();
        for(Integer i = 0 ; i < wt.size() ; i ++ ) {
            dp.put(i, new ArrayList<>(zeros));
        }

        //For each item
        for( Integer i = 1 ; i < wt.size() ; i ++ ) {
            Integer itemWeight = wt.get(i);
            Integer itemProfit = pr.get(i);
            //For each capacity
            for ( Integer capacity : cp ) {
                if ( capacity >= itemWeight ) {
                    Integer p1 = itemProfit + dp.get(i - 1).get(capacity - itemWeight);
                    Integer p2 = dp.get(i - 1).get(capacity);

                    dp.get(i).set(capacity, Math.max(p1, p2));
                } else {
                    dp.get(i).set(capacity, dp.get(i -1).get(capacity));
                }
            }
        }

        //Print the result
        System.out.println("Maximum profit = " + dp.get(wt.size() - 1).get(cp.size() - 1));
    }
}
