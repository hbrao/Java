package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {

    public static void main(String[] args) {
        Integer n = 3;
        List<String> result = new ArrayList<>();
        generateParenthesisBacktrack(result, "", 0, 0, n);
        result.forEach( c -> System.out.println(c));
    }

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

    public static void generateParenthesis(char[] buffer, Integer pos, List<String> collector) {
        if ( buffer.length == pos ) {
            String c = new String(buffer);
            if ( isValidParenthesis(c))
                collector.add(c);
        } else {
            buffer[pos] = '(';
            generateParenthesis(buffer, pos + 1, collector);
            buffer[pos] = ')';
            generateParenthesis(buffer, pos + 1, collector);
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
}
