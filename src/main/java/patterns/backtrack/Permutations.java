package patterns.backtrack;

import java.util.*;
import java.util.stream.*;

public class Permutations {

    public static void main(String[] args) {
        permute(IntStream.range(1, 4).toArray()).forEach( permutation -> System.out.println(permutation) );

        System.out.println();

        perumteIterative(IntStream.range(1, 4).toArray()).forEach( permutation -> System.out.println(permutation) );
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
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(new ArrayList<>());
        for(Integer num : nums) {
            Integer qsize = queue.size();
            while( qsize > 0 ) {
                List<Integer> prevPermutation = queue.remove();
                Integer prevPermutationSize = prevPermutation.size();
                for(Integer i = 0; i <= prevPermutationSize ;  i ++) {
                    List<Integer> newPermutation = new ArrayList<>(prevPermutation);
                    newPermutation.add(i, num);
                    queue.add(newPermutation);
                }
                qsize -=1 ;
            }
        }

        return new ArrayList<>(queue);
    }
}
