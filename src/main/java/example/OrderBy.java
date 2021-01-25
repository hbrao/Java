package example;

import java.util.*;

public class OrderBy<T extends Comparable> {
    private List<T> data;

    public OrderBy() {
        this.data = new ArrayList<>();
    }

    public OrderBy(List<T> data) {
        this.data = data;
    }

    public static void main(String[] args) {
        OrderBy<GenericPoint<Integer,String>> obj = new OrderBy<>();
        obj.data.add(new GenericPoint<>(1, "One"));
        obj.data.add(new GenericPoint<>(2, "Two"));
        obj.data.add(new GenericPoint<>(3, "Three"));
        obj.data.add(new GenericPoint<>(4, "Four"));

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
        obj.data.sort(Comparator.nullsFirst(Comparator.comparing(GenericPoint::getY)));
        obj.data.forEach((e) -> System.out.println(e));

        System.out.println("Sorting by field Y and X descending (default)");
        Comparator<GenericPoint> byY = Comparator.comparing(GenericPoint::getY);
        Comparator<GenericPoint> byX = Comparator.comparing(GenericPoint::getX);
        obj.data.sort(Comparator.nullsFirst(byY).thenComparing(byX.reversed()));
        obj.data.forEach((e) -> System.out.println(e));

        OrderBy<Integer> obj2 = new OrderBy<>(arrayToList(new Integer[] {11, 2, 33}));
        System.out.println("Sorted integers");
        obj2.data.sort(Comparator.naturalOrder());
        obj2.data.forEach( e-> System.out.println(e));
    }

    public static <X> List<X> arrayToList(X[] elements) {
        List<X> list = new ArrayList<>();
        for (X  e: elements ) {
            list.add(e);
        }
        return list;
    }

}
