package language.collections;

import  java.util.*;

public class Indexed {
    public static void main(String[] args) {
        // Arrays
        int[] arr = new int[] {2, -1, 3, -2, 1, 9, 11, 23, 6, 2};
        Arrays.sort(arr);
        Collections.reverse(Arrays.asList(arr)); //No reverse API in Arrays
        System.out.println(Arrays.toString(arr));
        //Fill
        Arrays.fill(arr, 0);
        System.out.println(Arrays.toString(arr));

        // ArrayList | Vector
        //Immutable list creation (Only up to 10 elements)
        List<Integer> immutableList = List.of(1, 2, 3);
        System.out.println(immutableList);

        Integer n = 9;
        //Create list of size N and fill with value
        List<Integer> data = new ArrayList<>(Collections.nCopies(n, 0));
        Collections.fill(data, 1);
        //Get
        data.get(0);
        //Get Sub list
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
        Integer[] arr2 = data.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr2));
        System.out.println(data);
    }
}
