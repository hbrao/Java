package patterns.graph;

import java.util.*;
import java.util.stream.*;

class TopologicalSort {
    public static List<Integer> sort(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();

        Map<Integer, Integer> inDegree = IntStream.range(0, vertices).boxed().collect(Collectors.toMap(v -> v, v -> 0));
        Map<Integer, List<Integer>> adjList = IntStream.range(0, vertices).boxed().collect(Collectors.toMap(
                v -> v, //Key = vertex number
                v -> new ArrayList<Integer>() //Value
            )
        );
        Arrays.stream(edges).forEach(edge -> {
                adjList.get(edge[0]).add(edge[1]);
                inDegree.merge(edge[1], 1, (v1, v2) -> v1 + v2);
            }
        );

        Queue<Integer> sources = inDegree.entrySet()
                .stream()
                .filter(e -> e.getValue() == 0)
                .map(entry -> entry.getKey())
                .collect(Collectors.toCollection(() -> new LinkedList<>()));

        while ( ! sources.isEmpty() ) {
            Integer u = sources.remove();
            adjList.get(u).forEach(v -> {
                Integer newDegree = inDegree.merge(v, -1, (v1, v2) -> v1 + v2);
                if (newDegree == 0) {
                    sources.add(v);
                }
            });
            sortedOrder.add(u);
        }

        if( sortedOrder.size() != vertices ) {
            System.out.println("Graph has cycle !");
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][]{new int[]{3, 2}, new int[]{3, 0}, new int[]{2, 0}, new int[]{2, 1}});
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][]{new int[]{4, 2}, new int[]{4, 3}, new int[]{2, 0},
                new int[]{2, 1}, new int[]{3, 1}});
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][]{new int[]{6, 4}, new int[]{6, 2}, new int[]{5, 3},
                new int[]{5, 4}, new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{4, 1}});
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][]{new int[]{6, 4}, new int[]{6, 2}, new int[]{5, 3}, new int[]{3, 5},
                new int[]{5, 4}, new int[]{3, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{4, 1}});
        System.out.println(result);
    }
}