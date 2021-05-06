package language.collections;

import java.util.*;

public class Grouped {
    public static void main(String[] args) {
        SortedSet<Integer> s1 = new TreeSet<>();
        SortedSet<Integer> s2 = new TreeSet<>();

        s1.addAll(List.of(4, 3, 2, 5));
        s2.addAll(List.of(2, 3, 4, 5));

        System.out.println(s1);
        System.out.println(s2);

        System.out.println(s1.headSet(4)); // < 4
        System.out.println(s2.tailSet(4)); // >= 4
    }
}
