package patterns.twopntr;

public class ShortestWindowSort {
    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 5, 3, 7, 10, 9, 12 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 3, 2, 0, -1, 7, 10 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 1, 2, 3 }));
        System.out.println(ShortestWindowSort.sort(new int[] { 3, 2, 1 }));
    }

    public static int sort(int[] arr) {
        Integer low = 0, high = arr.length - 1;

        //Find candidate sub array
        while(low < arr.length - 1 && arr[low] < arr[low + 1]) low += 1;
        if(low == high) return 0;
        while(high > low && arr[high] > arr[high - 1]) high -= 1;

        //Find min and max values simultaneously
        Integer min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(Integer i = low; i <= high ; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        //Expand window when left element greater than min
        while(low > 0 && arr[low - 1] > min)
            low -= 1;
        //Expand window when right element less than max
        while(high < arr.length - 1 && arr[high + 1] < max)
            high += 1;

        //Return window length
        return high - low + 1;
    }
}
