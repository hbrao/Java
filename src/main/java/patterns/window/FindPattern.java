package patterns.window;

import java.util.*;
import java.util.stream.*;
import org.junit.*;
import org.junit.runner.*;

public class FindPattern {
    public static void main(String[] args) {
        JUnitCore.main("practice.window.FindPattern");
    }

    public Boolean hasPermutedPattern(String str, String pattern) {
        Integer windowStart = 0, matched = 0;
        //Calculate character frequencies.
        Map<Character,Integer> charFreq = pattern.chars()
                                                 .mapToObj(v -> (char) v)
                                                 .collect(Collectors.toMap(c -> c, c -> 0, (v1, v2) -> v1 + v2));

        for(Integer windowEnd = 0; windowEnd < str.length() ; windowEnd ++) {
            Character rightChar = str.charAt(windowEnd);
            if ( charFreq.containsKey(rightChar) ) {
                Integer newValue = charFreq.merge(rightChar, 1, (v1, v2) -> v1 + v2);
                if ( newValue == 1 ) matched += 1;
            }
            if ( matched == charFreq.size() )
                return true;
            //Check if window size became larger than pattern.length()
            if ( windowEnd >= pattern.length() - 1 ) {
                //Shrink the window
                Character leftChar = str.charAt(windowStart);
                windowStart += 1;
                if ( charFreq.containsKey(leftChar) ) {
                    Integer newValue = charFreq.merge(leftChar, -1, (v1, v2) -> v1 + v2);
                    if( newValue == 0 ) matched -= 1;
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
