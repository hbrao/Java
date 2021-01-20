package com.examples;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamProcessing {
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 11).toArray();
        System.out.println(Arrays.toString(arr));

        int[] arr2 = IntStream.range(0, 11).map( e -> 0 ).toArray();
        System.out.println(Arrays.toString(arr2));

        List<Integer> arrList = IntStream.range(10, 21).boxed().collect(Collectors.toList());
        System.out.println(arrList.stream().map( e -> e.toString()).collect(Collectors.joining(",")));

        IntSummaryStatistics stats =  arrList.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println(stats.getSum() + ";" + stats.getAverage());
    }
}
