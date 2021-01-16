package features;

import java.util.*;

public class Comparators<T extends Comparable, S> {
    private List<T> data;
    private S metaObj;

    public Comparators(S metaObj) {
        this.data = new ArrayList<>();
        this.metaObj = metaObj;
    }

    public static void main(String[] args) {
        Comparators<MyTuple<Integer,String>,String> obj = new Comparators<>("BinaryHeap");
        obj.data.add(new MyTuple<>(1, "One"));
        obj.data.add(new MyTuple<>(2, "Two"));
        obj.data.add(new MyTuple<>(3, "Three"));
        obj.data.add(new MyTuple<>(4, "Four"));

        System.out.println("Default sort ....");
        obj.data.sort(Comparator.naturalOrder());
        obj.data.forEach((e) -> System.out.println(e));

        obj.data.add(null);

        System.out.println("Nulls first default sort ....");
        obj.data.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Nulls last default reverse order sort ....");
        obj.data.sort(Comparator.nullsLast(Comparator.reverseOrder()));
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Sorting by field Y ....");
        obj.data.sort(Comparator.nullsFirst(Comparator.comparing(MyTuple::getY)));
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Sorting by field Y and X descending (default)");
        Comparator<MyTuple> byY = Comparator.comparing(MyTuple::getY);
        Comparator<MyTuple> byX = Comparator.comparing(MyTuple::getX);
        obj.data.sort(Comparator.nullsFirst(byY).thenComparing(byX.reversed()));
        obj.data.forEach((e) -> System.out.println(e));
    }

    static class MyTuple<X extends  Comparable,Y extends  Comparable> implements Comparable {
        X x;
        Y y;
        public MyTuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Object o) {
            return x.compareTo(((MyTuple<X,Y>) o).x);
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }

        public X getX() {
            return x;
        }

        public Y getY() {
            return y;
        }
    }
}
