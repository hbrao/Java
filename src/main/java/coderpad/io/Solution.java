package coderpad.io;

import java.util.*;
import org.junit.*;
import org.junit.runner.*;

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

    public List<Integer> librarySort(int[] data) {
        List<Integer> l1 = new ArrayList<Integer>();
        Collections.addAll(Arrays.asList(data));
        l1.sort(Comparator.naturalOrder());
        return l1;
    }

    @Test
    public void testInsertionSort() {
        int[] data = new int[]{1, 10, 2, 10, 11, 8, -1};
        new Solution().insertionSort(data);
        for (int i = 1 ; i < data.length ; i ++ ){
            Assert.assertTrue(data[i] >= data[i-1]);
        }
    }

    @Test
    public void testLibrarySort() {
        int[] data = new int[]{1, 10, 2, 10, 11, 8, -1};
        List<Integer> l1 = new Solution().librarySort(data);
        ListIterator<Integer> itr = l1.listIterator();
        while ( itr.hasNext() ) {
            Assert.assertTrue(itr.next() >= itr.previous());
        }
    }
}
