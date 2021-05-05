package language.collections;

import  java.util.*;

public class Indexed {
    public static void main(String[] args) {
        Integer n = 9;
        //Define
        List<Integer> data = new ArrayList<>(Collections.nCopies(n, 0));
        //Fill
        Collections.fill(data, 1);
        //Sub list
        List<Integer> subList = data.subList(0, n / 2);
        //Sort
        data.sort(Comparator.reverseOrder());
        //Add
        data.add(9);
        data.add( n / 2 , - 10);
        //Remove
        data.remove( 0 );
        data.removeIf( val -> val < 0 );
        //Set
        data.set(0 , 0);
        //Replace all
        data.replaceAll( val -> val  == 0 ? 2 : val );
        //Reverse
        Collections.reverse(data);
        //Shuffle
        Collections.shuffle(data);
        //To Array
        Integer[] arr = data.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr));
        //Print
        System.out.println(data);
    }
}
