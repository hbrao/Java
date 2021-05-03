
import java.util.*;

import org.junit.*;
import org.junit.runner.*;

public class Solution {
    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    public void reverseString(char[] in) {

        if ( in == null || in.length <= 1 ) return;

        Integer i = 0;
        Integer j = in.length - 1;
        while ( i <= j ){
            //Swap
            swap(in, i, j);

            i += 1;
            j -= 1;
        }
    }

    public void swap(char[] in, Integer i, Integer j) {
        char tmp = in[i];
        in[i] = in[j];
        in[j] = tmp;
    }

    @Test
    public void testReverseString() {
        List<String> input = List.of("hello", "united state", "abba");
        for (String s : input) {
            char[] in = s.toCharArray();
            String expected = new StringBuilder(s).reverse().toString();

            reverseString(in);
            String reversed = new String(in);

            Assert.assertTrue(reversed, expected.equals(reversed));
        }
    }
}
