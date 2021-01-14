package coderpad.io;
/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class Solution {
    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    public void insertionSort(int[] data) {
        for (int i = 0; i < data.length ; i ++) {
            for (int j = i ; j > 0 ; j--) {
                if (data[j-1] > data[j]) {
                    int temp = data[j-1];
                    data[j-1] = data[j];
                    data[j] = temp;
                } else {
                    break;
                }
            }
        }
    }

    @Test
    public void testSortedArray() {
        int[] data = new int[]{1, 10, 2, 10, 11, 8, -1};
        new Solution().insertionSort(data);
        for (int i = 1 ; i < data.length ; i ++ ){
            Assert.assertTrue(data[i] >= data[i-1]);
        }
    }

}
