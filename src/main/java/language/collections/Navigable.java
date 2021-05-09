package language.collections;

import java.util.*;

public class Navigable {
    public static void main(String[] args) {
        //TreeSet, TreeMap
        SortedSet<Integer> s1 = new TreeSet<>();
        s1.addAll(List.of(4, 3, 2, 5));
        System.out.println(s1);

        //Navigable APIs
        System.out.println(s1.first());
        System.out.println(s1.last());
        System.out.println(s1.subSet(3, 5)); // >= 3 && < 5
        System.out.println(s1.headSet(4));   // < 4
        System.out.println(s1.tailSet(4));   // >= 4

        //Navigable APIs
        NavigableSet<Integer> s2 = (NavigableSet<Integer>) s1;
        System.out.println(s2.ceiling(2)); // >=2
        System.out.println(s2.higher(2));  // > 2
        System.out.println(s2.floor(4));   // <=4
        System.out.println(s2.lower(4));   // <4

        System.out.println(s2.descendingSet());
        System.out.println(s2.pollFirst());
        System.out.println(s2.pollLast());

        //NOTE: In case of map replace Set with Map & append Key, Entry, Value where appropriate.
    }
}
