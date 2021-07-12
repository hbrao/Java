package language.collections;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.*;

public class Indexed {
    public static void main(String[] args) {
        //
        // Array
        //

        int[] raw = new int[] {2, -1, 3, -2, 1, 9, 11, 23, 6, 2};
        Integer[] arr = Arrays.stream(raw).boxed().toArray(Integer[]::new);

        //Sum
        System.out.println(Arrays.stream(raw).sum());
        System.out.println(Arrays.stream(arr).mapToInt(e -> e).sum());

        //Sort
        Arrays.sort(raw); // Can't use comparator on primitive arrays.
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));

        //Swap NOTE: No swap api in Arrays
        Collections.swap(Arrays.asList(arr), 0,1);
        System.out.println(Arrays.asList(arr));

        //Fill
        Arrays.fill(arr, 0);
        System.out.println(Arrays.toString(arr));

        //
        // Array to List
        //

        List<Integer> immutableList = List.of(1, 2, 3);
        System.out.println(immutableList);

        List<Integer> mutableList = Arrays.asList(1, 3, 4, 5);

        //
        // ArrayList | Vector
        //

        Integer n = 9;
        //Create list of size N and fill with value
        List<Integer> data = new ArrayList<>(Collections.nCopies(n, 0));
        Collections.fill(data, 1);
        //Get
        data.get(0);
        //Get Sub list
        List<Integer> subList = data.subList(0, n / 2);
        //Sort
        data.sort(Comparator.naturalOrder());
        //Add
        data.add(9);
        data.add( n / 2 , - 10);
        //Remove
        System.out.println(data);
        data.remove( 0 );
        data.removeIf( val -> val < 0 );
        data.remove(Integer.valueOf(9));
        System.out.println(data);
        //Set
        data.set(0 , 0);
        //Replace all
        data.replaceAll( val -> val  == 0 ? 2 : val );
        //Reverse
        Collections.reverse(data);
        //Shuffle
        Collections.shuffle(data);
        System.out.println(data);

        //Clone into array.
        Integer[] arr2 = data.toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr2));
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }
}
