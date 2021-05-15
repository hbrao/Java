package language.collections;

import java.util.*;

public class Navigable {
    public static void main(String[] args) {
        //Set
        NavigableSet<Integer> s1 = new TreeSet<>();
        s1.addAll(List.of(4, 3, 2, 5));
        System.out.println(s1);

        //Sorted Set APIs
        System.out.println(s1.first());
        System.out.println(s1.last());
        System.out.println(s1.subSet(3, 5)); // >= 3 && < 5
        System.out.println(s1.headSet(4));   // < 4
        System.out.println(s1.tailSet(4));   // >= 4

        //Navigable Set  APIs
        System.out.println(s1.ceiling(2)); // >=2
        System.out.println(s1.higher(2));  // > 2
        System.out.println(s1.floor(4));   // <=4
        System.out.println(s1.lower(4));   // <4

        System.out.println(s1.descendingSet());
        System.out.println(s1.pollFirst());
        System.out.println(s1.pollLast());

        //Map
        NavigableMap<String, String> conn = new TreeMap<>( (key1, key2) -> {
            return key2.compareTo(key1); // Sort in reverse order of keys.
        });
        conn.put("mongo", "mongodb://mongodb0.example.com:27017");
        conn.put("oracle", "jdbc:oracle:thin:@db.server:1521:orcl");
        System.out.println(conn);
    }
}
