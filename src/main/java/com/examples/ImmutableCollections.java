package com.examples;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Map.*;

public class ImmutableCollections {
    public static void main(String[] args) {
        List<String> fruits = List.of("Apples", "Oranges", "Bananas");
        Set<String> stores = Set.of("Safeway", "Amazon", "Target");
        Map<String, Float> prices = ofEntries(
                entry("Apple", 1.2f),
                entry("Orange", .8f)
        );
    }
}
