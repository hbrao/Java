package practice.twopntr;

import java.util.*;
import org.junit.*;
import org.junit.runner.*;

public class TripletSumCloseToTarget {

    public static void main(String[] args) {
        JUnitCore.main("practice.twopntr.TripletSumCloseToTarget");
    }

    public int searchTriplet(int[] arr, int targetSum) {
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
        Assert.assertEquals(1, searchTriplet(new int[] { -2, 0, 1, 2 }, 2));
        Assert.assertEquals(0, searchTriplet(new int[] { -3, -1, 1, 2 }, 1));
        Assert.assertEquals(3, searchTriplet(new int[] { 1, 0, 1, 1 }, 100));
    }
}
