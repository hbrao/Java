package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Parenthesis {

    public static void main(String[] args) {
        Integer n = 3;
        List<String> result = new ArrayList<>();
        generateParenthesis(new char[ 2 * n], 0, result);
        result.forEach( c -> System.out.println(c));
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
