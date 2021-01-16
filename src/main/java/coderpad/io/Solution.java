package coderpad.io;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> l1 = IntStream.of(data).boxed().collect(Collectors.toList());
        System.out.println(l1.getClass());
        l1.sort(Comparator.naturalOrder());
        l1.forEach(x -> System.out.println(x));
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
        Integer prev = null ;
        while( itr.hasNext() ) {
            Integer cur = itr.next();
            if ( itr.previousIndex() > 0 ) {
                Assert.assertTrue(cur >= prev);
            }
            prev = cur;
        }
    }
}
