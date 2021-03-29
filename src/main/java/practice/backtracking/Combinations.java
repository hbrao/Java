package practice.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Combinations {

    public static void main(String[] args) {
        //Set k = 0 to generate power sets
        subsets(IntStream.range(1, 5).toArray(), 0).forEach( lst -> {
            System.out.println(lst);
        });
    }

    public static List<List<Integer>> subsets(int[] nums, Integer k) {
        List<Integer> nums_lst = IntStream.of(nums).boxed().collect(Collectors.toList());

        List<List<Integer>> result = new LinkedList<>();

        //Add empty set
        if ( k == 0 )  result.add(new ArrayList<>());

        subsetHelper(nums_lst, new ArrayList<Ineger>(), 0, result, k);
        return result;
    }

    public static void subsetHelper(List<Integer> nums_lst, List<Integer> buffer, Integer first,  List<List<Integer>> collector, Integer k) {
        if ( k != 0 && buffer.size() == k ) {
            return;
        }

        // Total number branches = size of of input
        for( Integer i = first; i < nums_lst.size() ; i ++ ) {
            //Collect subset
            buffer.add(nums_lst.get(i));
            if ( buffer.size() == k || k == 0) collector.add(new ArrayList<>(buffer));

            //Recursive call to collect higher sized subsets.
            subsetHelper(nums_lst, buffer, i + 1,  collector, k);

            //Backtrack to re-use the buffer in next branch.
            buffer.remove(buffer.size() - 1);
        }
    }
}
