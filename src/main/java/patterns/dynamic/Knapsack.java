package patterns.dynamic;

import java.util.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Parameterized.class)
public class Knapsack {

    int[] weights;
    int[] profits;
    int capacity;
    int maxProfitExpected;

    //Must have matching constructor to pass data returned by getTestData() API.
    public Knapsack(int[] weights, int[] profits, int capacity, int maxProfitExpected) {
        this.weights = weights;
        this.profits = profits;
        this.capacity = capacity;
        this.maxProfitExpected = maxProfitExpected;
    }

    //Must be static
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return  new Object[][] {
           {new int[]{1, 2, 3, 5}, new int[]{1, 6, 10, 16},  7, 7}
        };
    }

    public int getMaxProfitRecursive() {
        return  0;
    }

    @Test
    public void testMaxProfit() {
        System.out.println("Item weights : " + Arrays.toString(weights));
        System.out.println("Item profits : " + Arrays.toString(profits));
        System.out.println("Capacity     : " + capacity);

        int maxProfitActual = getMaxProfitRecursive();

        Assert.assertEquals(String.format("Expected %s != Actual %s ", maxProfitActual , maxProfitExpected) , maxProfitActual , maxProfitExpected);
    }
}
