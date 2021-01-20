package com.examples;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class StreamProcessing {
    public static void main(String[] args) {
        int[] arr = IntStream.range(0, 11).toArray();
        System.out.println(Arrays.toString(arr));

        int[] arr2 = IntStream.range(0, 11).map( e -> 0 ).toArray();
        System.out.println(Arrays.toString(arr2));

        List<Integer> arrList = IntStream.range(10, 21).boxed().collect(toList());
        System.out.println(arrList.stream().map( e -> e.toString()).collect(joining(",")));

        IntSummaryStatistics stats =  arrList.stream().collect(summarizingInt(Integer::intValue));
        System.out.println(stats.getSum() + ";" + stats.getAverage());
    }
}
