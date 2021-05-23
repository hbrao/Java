package language.comparator;

import java.util.*;

public class GenericComparator<T extends Comparable> {
    private List<T> data;

    public GenericComparator() {
        this.data = new ArrayList<>();
    }

    public static void main(String[] args) {
        GenericComparator<GenericPoint<Integer,String>> obj = new GenericComparator<>();

        obj.data.add(new GenericPoint<>(1, "One"));
        obj.data.add(new GenericPoint<>(2, "Two"));
        obj.data.add(new GenericPoint<>(3, "Three"));
        obj.data.add(new GenericPoint<>(4, "Four"));

        System.out.println("Default sort ....");
        obj.data.sort(Comparator.naturalOrder());
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Reversed sort ....");
        obj.data.sort(Comparator.reverseOrder());
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Sorting by field Y ....");
        obj.data.sort(Comparator.comparing( (GenericPoint p) ->  { // Key extractor. Key should implement Comparable.
                return p.getY();
            }
        ));
        obj.data.forEach((e) -> System.out.println(e));

        //If collections contains null, we must wrap Comparators using nullsFirst / nullsLast.
        obj.data.add(null);

        System.out.println("Nulls first default sort ....");
        obj.data.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Nulls last default reverse order sort ....");
        obj.data.sort(Comparator.nullsLast(Comparator.reverseOrder()));
        obj.data.forEach((e) -> System.out.println(e));

        //Implementing order by field1, field2 ....
        System.out.println("Sorting by field Y and X descending (default)");
        Comparator<GenericPoint> byY = Comparator.comparing((GenericPoint p) -> p.getY());
        Comparator<GenericPoint> byX = Comparator.comparing((GenericPoint p) -> p.getX());
        obj.data.sort(Comparator.nullsFirst(byY).thenComparing(byX.reversed()));
        obj.data.forEach((e) -> System.out.println(e));
    }

    public static class GenericPoint<X extends Comparable, Y extends Comparable> implements Comparable {
        X x;
        Y y;

        public GenericPoint(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            return x.compareTo(((GenericPoint<X, Y>) o).x);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }

        public X getX() {
            return x;
        }

        public Y getY() {
            return y;
        }
    }
}
