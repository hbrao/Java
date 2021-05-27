package language.collections;

import  java.util.*;

public class Indexed {
    public static void main(String[] args) {
        //
        // Arrays
        //

        int[] raw = new int[] {2, -1, 3, -2, 1, 9, 11, 23, 6, 2};

        //Convert int[] to Integer[]
        Integer[] arr = Arrays.stream(raw).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, Comparator.comparing((Integer i) -> i.intValue()));
        Collections.sort(Arrays.asList(arr), Comparator.comparing((Integer i) -> i.intValue()).reversed()); //No reverse API in Arrays
        System.out.println(Arrays.toString(arr));

        Collections.swap(Arrays.asList(arr), 0,1); //No swap api in Arrays
        System.out.println(Arrays.toString(arr));

        //Fill
        Arrays.fill(arr, 0);
        System.out.println(Arrays.toString(arr));

        //Unmodifiable list (backed by an array)
        List<Integer> immutableList = List.of(1, 2, 3);
        System.out.println(immutableList);

        //Modifiable fixed size list (backed by a fixed length array).
        List<String> statusCodes = Arrays.asList("OPEN","PROCESS", "CLOSED");
        statusCodes.set(1, "INPROCESS");
        System.out.println(statusCodes);

        //Mutable list (Backed by re-sizable array)
        List<String> statusList = new ArrayList<>(List.of("OPEN", "PROCESS", "CLOSED"));
        statusList.set(1, "INPROCESS");
        statusList.add("ERROR");
        System.out.println(statusList);

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
        //To Array
        Integer[] arr2 = data.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr2));
        System.out.println(data);
    }

    public static void swap(int[] arr, int left, int right) {
        arr[left] = (arr[left] + arr[right]) - (arr[right] = arr[left]);
    }
}
