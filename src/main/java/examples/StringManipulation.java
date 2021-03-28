package examples;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringManipulation {

    public static void main(String[] args) {
        String s = "Daily Problem Solving";
        //Get character at index
        System.out.println(s.charAt(3));

        //Get first index of a given character if present. Otherwise return -1
        System.out.println(s.indexOf('P') + "; " + s.indexOf("Z"));

        //Get sub string [s, e]
        System.out.println(s.substring(6,13));

        //Get sub string [s, ]
        System.out.println(s.substring(1));

        //Convert to list of Characters
        List<Character> chrList = s.chars().mapToObj( val -> (char) val).collect(Collectors.toList());
        System.out.println(chrList.subList(6,13));

        //Remove vowels.
        String vowels = "aAeEiIoOuU";
        StringBuilder sb1 = new StringBuilder(s);
        for(int i = 0 ; i < sb1.length() ; i ++) {
            if ( vowels.indexOf(sb1.charAt(i)) != -1 ) {
                sb1.deleteCharAt(i);
                i -= 1;
            }
        }
        System.out.println(sb1.toString());

        //Reverse string.
        System.out.println(new StringBuilder(s).reverse());

        //Check if all characters are a letter (a-zA-Z) or not (ignore spaces)
        Boolean result = true;
        for(char c : s.toCharArray()) {
            if( ! Character.isSpaceChar(c) && ! Character.isLetter(c) ) {
                result = false;
            }
        }
        System.out.format("Result = %s \n", result);

        //Extract ASCII value associated with each character
        String ascii = "09AZaz漢字";
        for ( int i =0; i < ascii.length() ; i ++ ) {
            System.out.format("Result = %d  %d \n", (int) ascii.charAt(i), Character.codePointAt(ascii, i));
        }

        //Get words or numbers from a string
        String text = "Hi, There ! You can reach me at hello@example.com or +91 9701790048.";
        Matcher m = Pattern.compile("[a-zA-Z|0-9]+").matcher(text);
        while ( m.find() ) System.out.print(m.group() + ", ");
        System.out.println();
    }
}
