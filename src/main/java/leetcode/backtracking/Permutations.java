package leetcode.backtracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {

    public static void main(String[] args) {
        new Permutations().permute(IntStream.range(1,4).toArray()).forEach( l -> System.out.println(l));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> nums_lst = IntStream.of(nums).boxed().collect(Collectors.toList());
        permuteHelper(nums_lst, 0, result);
        return result;
    }

    //NOTE: This API does not use any buffer / extra storage instead swaps elements of source.
    public void permuteHelper(List<Integer> nums_list, Integer first, List<List<Integer>> collector) {
        if ( first == nums_list.size() - 1 ) {
            collector.add(nums_list.stream().collect(Collectors.toList()));
            return;
        } else  {
            for( Integer i = first; i < nums_list.size() ; i ++ ) {
                Collections.swap(nums_list, first, i);
                permuteHelper(nums_list, first + 1, collector);

                //Backtrack (undo swap)
                Collections.swap(nums_list, i, first);
            }
        }
    }
}
