package language.threading;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

public class Contention {

    public static void main(String[] args) {
        //Code with contention due to synchronized method in Counter class.
        Map<Integer,Counter> partition1 = new ConcurrentHashMap<>();
        Long start = System.nanoTime();
        IntStream.range(0, 100_000_000)
                 .parallel()
                 .forEach( i -> {
                    partition1.computeIfAbsent(
                    i % 2  // Key
                        , key -> new Counter() // Value if key is NOT found
                    ).increment() ;
                 }
        );
        System.out.println(partition1);
        System.out.printf("Took %d ms\n", (System.nanoTime() - start) / 1_000_000);

        //Contention removed with AtomicInteger
        start = System.nanoTime();
        Map<Integer, AtomicInteger> partition2 = new ConcurrentHashMap<>();
        IntStream.range(0, 1_000_000)
                 .parallel()
                 .forEach( i -> {
                     partition2.computeIfAbsent(
                      i % 2,
                           key -> new AtomicInteger()
                     ).incrementAndGet();
                 });
        System.out.println(partition2);
        System.out.printf("Took %d ms\n", (System.nanoTime() - start) / 1_000_000);

        //Further improvement with LongAdder
        Map<Integer,LongAdder> partition3 = new ConcurrentHashMap<>();
        start = System.nanoTime();
        IntStream.range(0, 1_000_000)
                 .parallel()
                 .forEach( i -> {
                     partition3.computeIfAbsent(
                      i % 2,
                           key -> new LongAdder()
                     ).increment();
                 });
        System.out.println(partition3);
        System.out.printf("Took %d ms\n", (System.nanoTime() - start) / 1_000_000);
    }

    private static class Counter {
        private int value;
        public synchronized void increment() {
            value++;
        }
        @Override
        public String toString() {
            return "" + value;
        }
    }
}
