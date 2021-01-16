package features;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Collections {
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5};

        List arrList = IntStream.of(data).boxed().collect(Collectors.toCollection(ArrayList::new));
        arrList.add(100);
        arrList.forEach(s -> System.out.println(s));

        List linkedList = (List) arrList.stream().collect(Collectors.toCollection(LinkedList::new));
        linkedList.add(111);
        linkedList.forEach(s -> System.out.println(s));

        Set set = (Set) linkedList.stream().collect(Collectors.toCollection(TreeSet::new));
        set.add(222);
        set.forEach(s -> System.out.println(s));
    }
}
