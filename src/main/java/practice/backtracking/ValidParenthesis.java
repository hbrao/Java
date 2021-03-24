package practice.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ValidParenthesis {

    public static void main(String[] args) {
        Integer n = 3;
        List<String> result = new ArrayList<>();
        generateParenthesisPermutations(new char[2 * n], 0, result );
        result.forEach( c -> System.out.println(c));

        System.out.println("------------------------");

        result = new ArrayList<>();
        generateParenthesisBacktrack(result, "", 0, 0, n);
        result.forEach( c -> System.out.println(c));
    }

    //This generates all possible permutations and discards invalid ones.
    public static void generateParenthesisPermutations(char[] buffer, Integer pos, List<String> collector) {
        if ( buffer.length == pos ) {
            String c = new String(buffer);
            //Discard invalid permutation
            if ( isValidParenthesis(c))
                collector.add(c);
        } else {
            buffer[pos] = '(';
            generateParenthesisPermutations(buffer, pos + 1, collector);
            buffer[pos] = ')';
            generateParenthesisPermutations(buffer, pos + 1, collector);
        }
    }

    public static Boolean isValidParenthesis(String input) {
        Integer balance = 0;
        for ( Character c : input.toCharArray() ) {
            if ( c == '(' ) {
               balance += 1 ;
            } else {
               balance -= 1 ;
            }
            if ( balance < 0 ) return false ;
        }
        return balance == 0;
    }

    //This does NOT generate any invalid permutations
    public static void generateParenthesisBacktrack(List<String> collector, String buffer, Integer open, Integer close, Integer n) {
        if ( buffer.length() == 2 * n) {
            collector.add(buffer);
            return ;
        }
        if ( open < n ) {
            generateParenthesisBacktrack(collector, buffer + "(", open + 1, close, n);
        }
        if ( close < open ) {
            generateParenthesisBacktrack(collector, buffer + ")", open , close + 1, n);
        }
    }
}
