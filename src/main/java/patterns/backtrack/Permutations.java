package patterns.backtrack;

import java.util.*;
import java.util.stream.*;

public class Permutations {

    public static void main(String[] args) {
        permute(IntStream.range(1, 4).toArray()).forEach( permutation -> System.out.println(permutation));

        System.out.println();

        perumteIterative(IntStream.range(1, 4).toArray()).forEach( permutation -> System.out.println(permutation));
    }

    //This API does not use any buffer / extra storage instead swaps elements of source.
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(IntStream.of(nums).boxed().toArray(Integer[]::new), 0, result);
        return result;
    }

    public static void permuteHelper(Integer[] nums, Integer start, List<List<Integer>> collector) {
        if ( start == nums.length ) {
            collector.add(new ArrayList<>(Arrays.asList(nums)));
            return;
        } else  {
            for( Integer i = start; i < nums.length ; i ++ ) {
                Collections.swap(Arrays.asList(nums), start, i);
                permuteHelper(nums, start + 1, collector);

                //Backtrack (undo swap)
                Collections.swap(Arrays.asList(nums), i, start);
            }
        }
    }

    //Iterative implementation of permutations.
    public static List<List<Integer>> perumteIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if ( nums.length == 0 ) {
            result.add(new ArrayList<>());
        } else {
            Integer fromIdx = result.size();
            for(Integer i = 0; i < nums.length ; i ++ ) {
                if ( result.isEmpty() ) {
                    List<Integer> l = new ArrayList<>();
                    l.add(nums[i]);
                    result.add(l);
                } else {
                    List<List<Integer>> previousPermutations = result.subList(fromIdx, result.size());
                    Integer permutationSize = previousPermutations.get(0).size();
                    Integer numPreviousPermutations = previousPermutations.size();
                    fromIdx = result.size();
                    for( Integer k =0 ; k < numPreviousPermutations ; k ++ ) {
                       for( Integer j = 0; j <= permutationSize ; j ++ ) {
                           List<Integer> l = new ArrayList<>(previousPermutations.get(k));
                           l.add(j, nums[i]);
                           previousPermutations.add(l);
                       }
                    }
                }
            }
        }

        return result.stream().filter( l -> l.size() == nums.length ).collect(Collectors.toList());
    }
}
