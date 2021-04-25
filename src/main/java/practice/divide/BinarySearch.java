package practice.divide;

import java.util.*;
import java.util.stream.*;

public class BinarySearch {
    public static void main(String[] args) {
        List<Integer>  data = IntStream.range(0,100).boxed().collect(Collectors.toList());
        System.out.println(data);
        Integer elem  = 99;
        System.out.println(binarySearch(data, elem));
    }

    public  static <T extends Comparable> Integer binarySearch(List<T> data, T elem) {
        int start = 0, end = data.size() - 1;
        while ( start <= end ) {
            int mid = ( start + end ) / 2 ;
            if ( elem.compareTo(data.get(mid)) == 0 ) {
                return mid;
            }

            if ( elem.compareTo(data.get(mid)) > 0 ) { // Search in right half
                start = mid + 1;
            } else { //Search in left half
                end = mid - 1;
            }
        }
        return  -1;
    }
}


