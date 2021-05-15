package practice.window;

import java.util.*;
import java.util.stream.*;
import org.junit.*;
import org.junit.runner.*;

public class FindPattern {
    public static void main(String[] args) {
        JUnitCore.main("practice.window.FindPattern");
    }

    public Boolean hasPermutedPattern(String str, String pattern) {
        Integer windowStart = 0, counter = 0;
        //Calculate character frequencies.
        Map<Character,Integer> charFreq = pattern.chars()
                                                 .mapToObj(v -> (char) v)
                                                 .collect(Collectors.toMap(c -> c, c -> 1, (v1, v2) -> v1 + v2));

        for(Integer windowEnd = 0; windowEnd < str.length() ; windowEnd ++) {
            Character rightChar = str.charAt(windowEnd);
            if ( charFreq.containsKey(rightChar) ) {
                charFreq.merge(rightChar, -1, (v1, v2) -> v1 + v2);
                if ( charFreq.get(rightChar) == 0 )
                    counter += 1;
            }
            if ( counter == charFreq.size() )
                return true;
            //Check if window size became larger than pattern.length()
            if ( windowEnd >= pattern.length() - 1 ) {
                //Shrink the window
                Character leftChar = str.charAt(windowStart);
                windowStart += 1;
                if ( charFreq.containsKey(leftChar) ) {
                    if ( charFreq.get(leftChar) == 0 )
                        counter -= 1;
                    charFreq.merge(leftChar, 1, (v1, v2) -> v1 + v2);
                }
            }
        }

        return false;
    }

    @Test
    public void testAPIs() {
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("oidbcaf", "abc"));
        Assert.assertEquals("Incorrect output ", false, hasPermutedPattern("odicf", "dc"));
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("bcdxabcdy", "bcdyabcdx"));
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("aaacb", "abc"));
    }
}
