package leetcode.backtracking;

import java.util.ArrayList;
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
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> nums_lst = IntStream.of(nums).boxed().collect(Collectors.toList());
        Integer n  = nums_lst.size();
        result.add(new ArrayList<>());
        for (Integer i = 0 ; i < nums_lst.size() ; i ++ ) {
            List<Integer> prev_lst = new ArrayList<>(List.of(nums_lst.get(i)));
            result.add(prev_lst);
            subsetHelper(nums_lst.subList(i, n), prev_lst, 0, result);
        }
        return result;
    }

    public void subsetHelper(List<Integer> nums_lst, List<Integer> prev_list, Integer first,  List<List<Integer>> collector) {
        if ( first >= nums_lst.size() - 1 ){
            return;
        }

        for( Integer i = first + 1; i < nums_lst.size() ; i ++ ) {
           List<Integer> sub1 = new ArrayList<>(prev_list);
           sub1.add(nums_lst.get(i));
           collector.add(sub1);
           subsetHelper(nums_lst, sub1, i, collector);
        }
    }
}
