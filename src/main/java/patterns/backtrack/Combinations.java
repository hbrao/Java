package patterns.backtrack;

import java.util.*;
import java.util.stream.*;

public class Combinations {

    public static void main(String[] args) {
        //Set k = 0 to generate power sets
        subsets(IntStream.range(1, 5).toArray(), 0).forEach( lst -> {
            System.out.println(lst);
        });
        
        System.out.println();

        subsetsIterative(IntStream.range(1, 5).toArray()).forEach( lst -> {
            System.out.println(lst);
        });
    }

    public static List<List<Integer>> subsets(int[] nums, Integer k) {
        List<List<Integer>> result = new LinkedList<>();

        //Add empty set
        if ( k == 0 )  result.add(new ArrayList<>());

        subsetHelper(Arrays.stream(nums).boxed().toArray(Integer[]::new),0, result, new ArrayList<Integer>(), k);

        return result;
    }

    public static void subsetHelper(Integer[] nums, Integer start,  List<List<Integer>> collector,  List<Integer> buffer,  Integer k) {
        if ( k != 0 && buffer.size() == k ) { // Stop at k combinations.
            return;
        }

        // Total number branches = size of of input
        for( Integer i = start; i < nums.length ; i ++ ) {
            //Collect subset
            buffer.add(nums[i]);
            if ( buffer.size() == k || k == 0) collector.add(new ArrayList<>(buffer));

            //Recursive call to collect higher sized subsets.
            subsetHelper(nums,i + 1, collector, buffer,  k);

            //Backtrack to re-use the buffer in next branch.
            buffer.remove(buffer.size() - 1);
        }
    }

    //Iterative implementation of power set.
    public static List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> subSets = new ArrayList<>();
        subSets.add(new ArrayList<>());
        for(Integer num : nums) {
            Integer size = subSets.size();
            for(Integer i = 0; i < size  ; i ++) {
                List<Integer> l1 = new ArrayList<>(subSets.get(i));
                l1.add(num);
                subSets.add(l1);
            }
        }
        return subSets;
    }
}
