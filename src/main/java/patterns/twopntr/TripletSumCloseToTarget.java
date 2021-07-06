package patterns.twopntr;

import java.util.*;
import org.junit.*;
import org.junit.runner.*;

public class TripletSumCloseToTarget {

    public static void main(String[] args) {
        JUnitCore.main("practice.twopntr.TripletSumCloseToTarget");
    }

    public static List<List<Integer>> searchQuadruplets(Integer[] arr, Integer target) {
        //TODO Test the code.
        List<List<Integer>> quadruplets = new ArrayList<>();
        Arrays.sort(arr);

        for(Integer i = 0; i < arr.length - 3 ; i ++ )
           for(Integer j = i + 1; j < arr.length - 2; j ++)
              searchPairs(arr, target, i, j, quadruplets);

        return quadruplets;
    }

    public static void searchPairs(Integer[] arr, Integer targetSum, Integer first, Integer second, List<List<Integer>> quadruplets) {
        Integer left = second + 1;
        Integer right = arr.length - 1;
        while( left < right ) {
            Integer sum = arr[first] + arr[second] + arr[left] + arr[right];
            if( sum == targetSum ) {
                quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));
            } else if ( sum < targetSum) {
                left += 1;
            } else {
                right -= 1;
            }
        }
    }

    public int searchTripletSumCloseToTarget(int[] arr, int targetSum) {
        Arrays.sort(arr);
        Integer low = Integer.MIN_VALUE, high = Integer.MAX_VALUE;
        for(Integer i =0; i < arr.length - 2; i ++) {
            Integer left = i + 1, right = arr.length - 1;
            while (left < right) {
                Integer sum = arr[i] + arr[left] + arr[right];
                if ( targetSum == sum ) {
                    return targetSum;
                } else if ( sum > targetSum ) {
                    right -= 1;
                    high = Math.min(high, sum);
                } else {
                    left += 1;
                    low = Math.max(low, sum);
                }
            }
        }

        if( Math.abs(targetSum - low) <= Math.abs(high - targetSum)) {
            return low;
        } else {
            return high;
        }
    }

    @Test
    public  void testSearchTriplet() {
        Assert.assertEquals(1, searchTripletSumCloseToTarget(new int[] { -2, 0, 1, 2 }, 2));
        Assert.assertEquals(0, searchTripletSumCloseToTarget(new int[] { -3, -1, 1, 2 }, 1));
        Assert.assertEquals(3, searchTripletSumCloseToTarget(new int[] { 1, 0, 1, 1 }, 100));
    }
}
