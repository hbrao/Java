package problems.project01;

/*
Write a function that takes two parameters, a string and an integer.
The function will return another string that is similar to the input string, but with certain characters removed.
It's going to remove characters from consecutive runs of the same character when the run length
is greater than the input integer
"aaab", 2 => "aab" (run length =2 so the third 'a' is removed)
"aabb", 1 => "ab"
"aabbaa", 1 => "aba"
String stripConsectiveChar(String target, int limit) {}
*/
public class StripeConsecutiveChar {
    public static void main(String[] args) {
        System.out.println(stripConsecutiveChar("aabbbbbbaaaaaa", 3));
    }

    public static String stripConsecutiveChar(String str, int limit) {
        StringBuilder target = new StringBuilder(str);
        Integer counter = 1;
        for ( Integer j = 1 ; j < target.length() ; j ++ ) {
            //Increment counter if previous character is same as current.
            if ( target.charAt(j) == target.charAt(j-1) ) {
                counter += 1 ;
                if ( counter > limit ) {
                    target.deleteCharAt(j);
                    j -= 1;
                }
            } else {
                counter = 1;
            }
        }
        return target.toString();
    }
}
