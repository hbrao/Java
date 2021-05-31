package patterns.dynamic;

import java.util.*;
import java.util.stream.Collectors;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Parameterized.class)
public class Knapsack {

    Integer[] itemWeights;
    Integer[] itemProfits;
    Integer capacity;
    Integer maxProfitExpected;

    //Must have matching constructor to pass data returned by getTestData() API.
    public Knapsack(Integer[] itemWeights, Integer[] itemProfits, int capacity, int maxProfitExpected) {
        this.itemWeights = itemWeights;
        this.itemProfits = itemProfits;
        this.capacity = capacity;
        this.maxProfitExpected = maxProfitExpected;
    }

    //Must be static
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return  new Object[][] {
           {new Integer[]{1, 2, 3, 5}, new Integer[]{1, 6, 10, 16},  7, 22},
           {new Integer[]{1, 2, 3, 5}, new Integer[]{1, 6, 10, 16},  6, 17}
        };
    }

    public Integer getMaxProfitRecursive(Integer[] itemWeights, Integer[] itemProfits, Integer capacity, Integer start, Integer totalWeight, Integer totalProfit) {
        if ( start == itemWeights.length ) {
            return capacity >= totalWeight ? totalProfit : 0 ;
        }

        Integer itemIncludeProfit = getMaxProfitRecursive(itemWeights, itemProfits, capacity, start + 1, totalWeight + itemWeights[start], totalProfit + itemProfits[start]);

        Integer itemExcludeProfit = getMaxProfitRecursive(itemWeights, itemProfits, capacity, start + 1, totalWeight, totalProfit);

        return Math.max(itemIncludeProfit, itemExcludeProfit);
    }

    public Integer getMaxProfitDynamicProgramming(Integer[] itemWeights, Integer[] itemProfits, Integer capacity) {
        int[][] dp = new int[itemWeights.length + 1][capacity + 1];

        for(Integer i = 1 ; i <= itemWeights.length ; i ++ ) {
            Integer currItemWeight = itemWeights[i - 1];
            for(Integer c = 1; c <= capacity ; c ++ ) {
                Integer prevProfit = dp[i - 1][c];
                if ( c >= currItemWeight ) {
                    Integer newProfit = itemProfits[i - 1] + dp[i - 1][c - currItemWeight];
                    dp[i][c] = Math.max(prevProfit, newProfit);
                } else {
                    dp[i][c] = dp[i - 1][c];
                }
            }
        }

        printMatrix(dp);
        printMaxProfitItems(itemWeights, itemProfits, dp);

        return dp[itemWeights.length][capacity];
    }

    public void printMaxProfitItems(Integer[] itemWeights, Integer[] itemProfits, int[][] dp) {
        Integer itemIdx = dp.length - 1;
        Integer capacityIdx = dp[0].length - 1;
        Integer totalProfit = dp[itemIdx][capacityIdx];
        List<Integer> selectedItemWeights = new ArrayList<>();
        while ( itemIdx > 0 ) {
            Integer prevProfit = dp[itemIdx - 1][capacityIdx];
            if ( totalProfit != prevProfit ) {
                selectedItemWeights.add(itemWeights[itemIdx - 1]);
                totalProfit -= itemProfits[itemIdx - 1];
                capacityIdx -= itemWeights[itemIdx - 1];
            }
            itemIdx -= 1;
        }

        System.out.println(selectedItemWeights.stream().map( idx -> idx.toString() ).collect(Collectors.joining(" ")));
        System.out.println();
    }

    @Test
    public void testMaxProfit() {
        System.out.println("Item weights : " + Arrays.toString(itemWeights));
        System.out.println("Item profits : " + Arrays.toString(itemProfits));
        System.out.println("Capacity     : " + capacity);

        Integer maxProfitActual = getMaxProfitRecursive(itemWeights, itemProfits, capacity, 0, 0, 0);

        System.out.println("Total profit : " + maxProfitActual);

        Assert.assertEquals(String.format("Expected %s != Actual %s ", maxProfitActual , maxProfitExpected) , maxProfitActual , maxProfitExpected);

        maxProfitActual = getMaxProfitDynamicProgramming(itemWeights, itemProfits, capacity);

        System.out.println("Total profit : " + maxProfitActual);

        Assert.assertEquals(String.format("Expected %s != Actual %s ", maxProfitActual , maxProfitExpected) , maxProfitActual , maxProfitExpected);
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println();
        Arrays.stream(matrix)
              .forEach( (int[] row ) -> {
                  System.out.print("[");
                  System.out.print(Arrays.stream(row).mapToObj(e -> e).map(e -> String.format("%02d", e)).collect(Collectors.joining(" ")));
                  System.out.println("]");
              });
        System.out.println();
    }
}
