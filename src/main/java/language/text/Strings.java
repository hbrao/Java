package language.text;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Strings {

    public static void main(String[] args) {
        char[] chArr = new char[] {'D', 'a', 'i', 'l', 'y', '\u0020', 'p', 'r', 'o', 'b', 'l', 'e', 'm', '\u0020', 's', 'o', 'l', 'v', 'i', 'n', 'g'};
        String s = String.valueOf(chArr);
        System.out.println("String : " + s);
        System.out.println("char array : " + Arrays.toString(s.toCharArray()));

        //Get character at index
        System.out.println(s.charAt(3));

        //Get first index of a given character if present. Otherwise return -1
        System.out.println(s.indexOf('P') + "; " + s.indexOf("Z"));

        //Contains
        System.out.println(s.contains(String.valueOf('a')) + " ; " + ( s.indexOf('a') != -1 ) );

        //Get sub string [s, e]
        System.out.println(s.substring(6,13));

        //Get sub string [s, ]
        System.out.println(s.substring(1));

        //Convert String to list of Characters and back
        List<Character> chrList = s.chars()
                                   .mapToObj( val -> (char) val)
                                   .collect(Collectors.toList());
        List<Character> charSubList  = chrList.subList(6,13);
        System.out.println(charSubList);
        String originalStr = chrList.stream().map(ch -> String.valueOf(Character.toUpperCase(ch))).collect(Collectors.joining(""));
        System.out.println("Original = " +  originalStr);


        //Shuffle list of characters
        Collections.shuffle(charSubList);
        System.out.println(charSubList);

        //Remove vowels.
        String vowels = "aAeEiIoOuU";
        StringBuilder sb1 = new StringBuilder();
        s.chars().forEach( val -> sb1.append(val) );
        for(int i = 0 ; i < sb1.length() ; i ++) {
            if ( vowels.indexOf(sb1.charAt(i)) != -1 ) {
                sb1.deleteCharAt(i);
                i -= 1;
            }
        }
        System.out.println(sb1);

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

        //Get character frequencies.
        String pattern = "aabc";
        Map<Character,Integer> charFreq = pattern.chars()
                                                    .mapToObj(v -> (char) v)
                                                    .collect(Collectors.toMap(
                                                                k -> k, //Key mapper
                                                                k-> 1, //Value mapper
                                                                ( v1 , v2 ) -> v1 + v2 //Merge function
                                                            )
                                                    );
        System.out.println(charFreq);

        //Formatting
        System.out.println(String.format("[%1$5s %2$5s %3$5s]", 1440, 9,  13231));


    }
}
