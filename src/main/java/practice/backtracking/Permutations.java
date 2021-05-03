package practice.backtracking;

import java.util.*;
import java.util.stream.*;

public class Permutations {

    public static void main(String[] args) {
        permute("ABC").forEach( val -> System.out.println(val));

        permute(IntStream.range(1,4).toArray()).forEach( l -> System.out.println(l));
    }

    //NOTE: This API uses intermediate storage to generate all permutations
    public static List<String> permute(String s) {
        List<String> result = new ArrayList<String>();
        //Base case
        if ( s.length() == 1 ) {
            result.add(s);
        }

        for(Integer i = 0; i < s.length() ; i ++) {
            Character c = s.charAt(i);
            List<String> subResult = permute(new StringBuilder(s).deleteCharAt(i).toString());
            for ( String subStr : subResult ) {
                result.add(c + subStr);
            }
        }
        return result;
    }

    //NOTE: This API does not use any buffer / extra storage instead swaps elements of source.
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> nums_lst = IntStream.of(nums).boxed().collect(Collectors.toList());
        permuteHelper(nums_lst, 0, result);
        return result;
    }

    public static void permuteHelper(List<Integer> nums_list, Integer first, List<List<Integer>> collector) {
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
