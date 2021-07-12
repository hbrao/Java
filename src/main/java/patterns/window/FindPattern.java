package patterns.window;

import java.util.*;
import java.util.stream.*;
import org.junit.*;
import org.junit.runner.*;

public class FindPattern {
    public static void main(String[] args) {
        JUnitCore.main("patterns.window.FindPattern");
    }

    public Boolean hasPermutedPattern(String str, String pattern) {
        Integer windowStart = 0, matched = 0;
        //Calculate character frequencies.
        Map<Character,Integer> charFreq = pattern.chars()
                                                 .mapToObj(v -> (char) v)
                                                 .collect(Collectors.toMap(c -> c, c -> 1, (v1, v2) -> v1 + v2));

        for(Integer windowEnd = 0; windowEnd < str.length() ; windowEnd ++) {
            Character rightChar = str.charAt(windowEnd);
            if ( charFreq.containsKey(rightChar) ) {
                Integer newValue = charFreq.merge(rightChar, -1, (v1, v2) -> v1 + v2);
                if( newValue == 0 ) matched += 1;
            }
            if ( matched == charFreq.size() )
                return true;

            //Shrink the window when characters in window == number of characters in pattern.
            if ( windowEnd - windowStart + 1 >= pattern.length() ) {
                Character leftChar = str.charAt(windowStart);
                windowStart += 1;
                if ( charFreq.containsKey(leftChar) ) {
                    if( charFreq.get(leftChar) >= 0 ) matched += 1;
                    charFreq.merge(leftChar, 1, (v1, v2) -> v1 + v2);
                }
            }
        }

        return false;
    }

    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();
        Map<String,Integer> wordFreq = Arrays.stream(words).collect(Collectors.toMap( w -> w, w -> 1, (v1, v2) -> v1 + v2));
        Integer wl = words[0].length(), windowStart = 0, matched = 0;
        for(Integer windowEnd = 0; windowEnd < str.length(); windowEnd += wl) {
            String lastWord = str.substring(windowEnd, Math.min(str.length(), windowEnd + wl));
            if( wordFreq.containsKey(lastWord) ) {
                Integer newValue = wordFreq.merge(lastWord, -1, (v1, v2) -> v1 + v2);
                if( newValue == 0 ) matched += 1;
            }
            if( matched == wordFreq.size() ){
                resultIndices.add(windowStart);
            }
            if( windowEnd + wl - windowStart + 1 >= words.length * wl ) {
                String firstWord = str.substring(windowStart, Math.min(str.length(), windowStart + wl));
                if( wordFreq.containsKey(firstWord) ) {
                    if( wordFreq.get(firstWord) >= 0 ) matched -= 1;
                    wordFreq.merge(firstWord, 1, (v1, v2) -> v1 + v2);
                }
                windowStart += wl;
            }
        }
        return resultIndices;
    }

    @Test
    public void testAPIs() {
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("oidbcaf", "abc"));
        Assert.assertEquals("Incorrect output ", false, hasPermutedPattern("odicf", "dc"));
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("bcdxabcdy", "bcdyabcdx"));
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("aaacb", "abc"));
        Assert.assertEquals("Incorrect output ", false, hasPermutedPattern("aaadcb", "aabc"));
        Assert.assertEquals("Incorrect output ", true, hasPermutedPattern("aaacb", "aabc"));

        Assert.assertArrayEquals("Incorrect indices ", new Integer[]{0, 3}, findWordConcatenation("catfoxcat", new String[] { "cat", "fox" }).toArray());
        Assert.assertArrayEquals("Incorrect indices ", new Integer[]{3}, findWordConcatenation("catcatfoxfox", new String[] { "cat", "fox" }).toArray());
    }
}
