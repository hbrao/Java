package scratch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveToArrayList {
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5};
        List l1 = IntStream.of(data).boxed().collect(Collectors.toCollection(ArrayList::new));
        l1.add(100);
        l1.forEach(s -> System.out.println(s));
    }
}
