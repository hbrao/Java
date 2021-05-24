package patterns.intervals;

import java.util.*;

public class Basics {
    public static List<Interval> merge(List<Interval> intervals) {
        //Order intervals by start and then end time.
        //Method Old
        intervals.sort( (i1, i2) -> {
            Integer r1 = Integer.compare(i1.start, i2.start);
            if ( r1 == 0 )
                return Integer.compare(i1.end, i2.end);
            else
                return r1;
            }
        );

        //Method New
        intervals.sort(Comparator.comparing((Interval i) -> i.start ).thenComparing((Interval i) -> i.end));

        List<Interval> mergedIntervals = new LinkedList<Interval>();
        Interval prev = null;
        for(Integer idx = 0 ; idx < intervals.size(); idx ++) {
            if ( prev == null ) prev = intervals.get(idx);
            Interval curr = intervals.get(idx);
            if ( prev.end > curr.start ) {
                prev.start = Math.min(prev.start, curr.start);
                prev.end = Math.max(prev.end, curr.end);
            } else {
                mergedIntervals.add(prev);
                prev = curr;
            }
        }
        mergedIntervals.add(prev);
        return mergedIntervals;
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedIntervals = new ArrayList<>();
        Integer i =0;

        //Skip all intervals those come before new interval.
        while ( i < intervals.size() && newInterval.start > intervals.get(i).end ) {
            mergedIntervals.add(intervals.get(i));
            i += 1;
        }

        //Merge all overlapping intervals into newInterval
        //A close observation will tell us that whenever the two intervals overlap, one of the interval’s start time lies within the other interval.
        while ( i < intervals.size()
                && ( newInterval.start >= intervals.get(i).start && newInterval.start <= intervals.get(i).end
                     || intervals.get(i).start >= newInterval.start && intervals.get(i).start <= newInterval.end
                )
        ) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i += 1;
        }
        mergedIntervals.add(newInterval);

        //Add remaining intervals.
        while ( i < intervals.size() ) {
            mergedIntervals.add( intervals.get(i) );
            i += 1;
        }

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : Basics.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : Basics.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : Basics.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();


        input = new ArrayList<Interval>();
        input.add(new Interval(1, 2));
        input.add(new Interval(7, 9));
        input.add(new Interval(8, 10));
        List<Interval> merged = Basics.merge(input);
        System.out.print("Merged intervals: ");
        merged.forEach( interval -> System.out.print("[" + interval.start + "," + interval.end + "] ") );
        System.out.println();
        merged = insert(merged, new Interval(5, 6));
        merged = insert(merged, new Interval(9, 12));
        merged = insert(merged, new Interval(80,99));
        System.out.print("Merged intervals: ");
        merged.forEach( interval -> System.out.print("[" + interval.start + "," + interval.end + "] ") );
        System.out.println();
    }

    static class Interval {
        Integer start;
        Integer end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    };
}
