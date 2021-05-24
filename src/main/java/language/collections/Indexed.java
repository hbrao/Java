package language.collections;

import  java.util.*;

public class Indexed {
    public static void main(String[] args) {
        //
        // Arrays
        //
        Integer[] arr = new Integer[] {2, -1, 3, -2, 1, 9, 11, 23, 6, 2};
        Arrays.sort(arr, Comparator.comparing((Integer i) -> i.intValue()));
        Collections.sort(Arrays.asList(arr), Comparator.comparing((Integer i) -> i.intValue()).reversed()); //No reverse API in Arrays
        System.out.println(Arrays.toString(arr));

        Collections.swap(Arrays.asList(arr), 0,1); //No swap api in Arrays
        System.out.println(Arrays.toString(arr));

        //Fill
        Arrays.fill(arr, 0);
        System.out.println(Arrays.toString(arr));

        //Immutable list (backed by an array)
        List<Integer> immutableList = List.of(1, 2, 3);
        System.out.println(immutableList);

        //Mutable list (backed by an array)
        List<String> statusCodes = Arrays.asList("OPEN","PROCESS", "CLOSED");
        statusCodes.set(1, "PROGRESS");
        System.out.println(statusCodes);

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

    public static void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }
}
