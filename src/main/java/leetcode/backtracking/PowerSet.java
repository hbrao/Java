package leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PowerSet {

    public static void main(String[] args) {
        new PowerSet().subsets(IntStream.range(1, 5).toArray()).forEach( lst -> {
            System.out.println(lst);
        });
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> nums_lst = IntStream.of(nums).boxed().collect(Collectors.toList());

        List<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<>());
        List<Integer> buffer = new LinkedList<>();
        subsetHelper(nums_lst, buffer, 0, result);
        return result;
    }

    public void subsetHelper(List<Integer> nums_lst, List<Integer> buffer, Integer first,  List<List<Integer>> collector) {
        if ( first == nums_lst.size() ) {
            return;
        }

        for( Integer i = first; i < nums_lst.size() ; i ++ ) {
            buffer.add(nums_lst.get(i));
            collector.add(new ArrayList<>(buffer));
            subsetHelper(nums_lst, buffer, i + 1,  collector);

            //Backtrack
            buffer.remove(buffer.size() - 1);
        }
    }
}
