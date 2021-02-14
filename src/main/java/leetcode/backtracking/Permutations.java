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

        System.out.println("Start of backtracking:" + nums_lst);

        permuteHelperBacktrack(nums_lst, 0, result);

        System.out.println("End of backtracking:" + nums_lst);

        return result;
    }

    public void permuteHelperBacktrack(List<Integer> nums_list, Integer fromIdx, List<List<Integer>> collector) {
        if ( fromIdx == nums_list.size() - 1 ) {
            collector.add(nums_list.stream().collect(Collectors.toList()));
            return;
        } else  {
            for( Integer i = fromIdx; i < nums_list.size() ; i ++ ) {
                //Do swapping to avoid extra space needed for cloning.
                Collections.swap(nums_list, fromIdx, i);
                permuteHelperBacktrack(nums_list, fromIdx + 1, collector);
                //Undo the swapping
                Collections.swap(nums_list, i, fromIdx);
            }
        }
    }
}
