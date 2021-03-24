import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.*;
import org.junit.runner.*;

public class Solution {
    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    public String foo() {
        return "Foo";
    }

    @Test
    public void testFoo() {
        Assert.assertTrue(this.foo() == "Foo");
    }
}
