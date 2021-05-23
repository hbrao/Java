package practice.intervals;

import java.util.*;

public class MergeIntervals {
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
        Integer ms = newInterval.start;
        Integer me = newInterval.end;
        Boolean overlap = false;
        for(Interval i : intervals) {
            if ( i.start < ms && i.end > ms || i.start < me && i.end > me || i.start > ms && i.end < me ) {
                ms = Math.min(i.start, ms);
                me = Math.max(i.end, me);
                overlap = true;
            } else {
                if ( overlap ) {
                    mergedIntervals.add(new Interval(ms, me));
                    overlap = false;
                }
                mergedIntervals.add(i);
            }
        }

        if ( overlap ) {
            mergedIntervals.add(new Interval(ms, me));
            overlap = false;
        }
        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
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
