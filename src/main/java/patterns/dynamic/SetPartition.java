package patterns.dynamic;

import language.matrix.Utils;

import java.util.*;

import static language.matrix.Utils.*;

class SetPartition {

    public static int canPartitionRecursive(int[] num, Integer start, Integer s1_sum , Integer s2_sum) {
        if ( start == num.length ) {
            return Math.abs( s1_sum - s2_sum );
        }

        Integer diff1 = canPartitionRecursive(num, start + 1, s1_sum + num[start], s2_sum);
        Integer diff2 = canPartitionRecursive(num, start + 1, s1_sum, s2_sum + num[start]);

        return Math.min(diff1, diff2);
    }

    public static int canPartitionDynamicProgramming(int[] num) {
        Integer totalSum = Arrays.stream(num).sum();
        Integer capacity = totalSum / 2 ;
        Boolean[][] dp = new Boolean[num.length + 1][capacity + 1];
        fillMatrix(dp, false);
        fillMatrixCol(dp, 0, true);

        for(Integer i = 1; i <= num.length; i ++) {
            Integer elem = num[i - 1];
            for( Integer c = 1; c <= capacity ; c ++ ) {
                if( dp[i - 1][c]) {
                    dp[i][c] = true;
                } else if ( elem <= c ) {
                    dp[i][c] = dp[i-1][c - elem];
                }
            }
        }

        for(Integer c = totalSum / 2 ; c >= 0 ; c -- ) {
            if (dp[dp.length  - 1][c]) {
                return Math.abs(c - (totalSum - c));
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] num = {1, 2, 3, 9};
        System.out.println(String.format("%1$3s  %2$3s", canPartitionRecursive(num, 0, 0, 0), canPartitionDynamicProgramming(num)));
        num = new int[]{1, 2, 7, 1, 5};
        System.out.println(String.format("%1$3s  %2$3s", canPartitionRecursive(num, 0, 0, 0), canPartitionDynamicProgramming(num)));
        num = new int[]{1, 3, 100, 4};
        System.out.println(String.format("%1$3s  %2$3s", canPartitionRecursive(num, 0, 0, 0), canPartitionDynamicProgramming(num)));
        num = new int[]{2, 4, 8, 12};
        System.out.println(String.format("%1$3s  %2$3s", canPartitionRecursive(num, 0, 0, 0), canPartitionDynamicProgramming(num)));
    }
}