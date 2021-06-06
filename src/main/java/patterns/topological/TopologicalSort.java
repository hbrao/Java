package patterns.topological;

import java.util.*;
import java.util.stream.*;

class TopologicalSort {
    public static List<Integer> bfs(int vertices, int[][] edges) {
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
            adjList.get(u).forEach( v -> {
                    Integer newDegree = inDegree.merge(v, -1, (v1, v2) -> v1 + v2);
                    if ( newDegree == 0 ) {
                        sources.add(v);
                    }
                }
            );
            sortedOrder.add(0, u);
        }

        if( sortedOrder.size() != vertices ) {
            System.out.println("Graph has cycle !");
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static List<Integer> dfs(int vertices, int[][] edges) {
        List<Integer> sortedOrder = new ArrayList<>();
        Map<Integer,List<Integer>> adjList = IntStream.range(0, vertices).boxed().collect(Collectors.toMap( v -> v, v -> new ArrayList<>()));
        Map<Integer,Integer> inDegree = IntStream.range(0, vertices).boxed().collect(Collectors.toMap( v -> v, v -> 0));
        Arrays.stream(edges).forEach( row -> {
                adjList.get(row[0]).add(row[1]);
                inDegree.merge(row[1], 1, (v1, v2) -> v1 + v2);
            }
        );

        Deque<Integer> stk = new LinkedList<>();
        List<String> color = IntStream.range(0, vertices).boxed().map( v -> "WHITE").collect(Collectors.toList());
        List<Integer> sources = inDegree.entrySet().stream().filter( e -> e.getValue() == 0).map(e -> e.getKey()).collect(Collectors.toList());
        sources.forEach( u -> {
                dfs_helper(adjList, color, u, sortedOrder, stk);
            }
        );

        if( vertices != sortedOrder.size() ) {
            return new ArrayList<>();
        }

        return sortedOrder;
    }

    public static void dfs_helper(Map<Integer,List<Integer>> adjList, List<String> color, Integer u, List<Integer> collector, Deque<Integer> stk) {
        if ( color.get(u).equals("WHITE") ) {
            color.set(u, "GRAY");
            stk.push(u);
            adjList.get(u).forEach(v -> {
                    if( color.get(v).equals("WHITE") ) {
                        dfs_helper(adjList, color, v, collector, stk);
                    } else if ( stk.contains(v) ) { // Back edge
                        System.out.print("Cycle: ");
                        Deque<Integer> tmp = new LinkedList<>();
                        while( stk.peek() != v ) {
                           tmp.push(stk.pop());
                        }
                        tmp.push(stk.pop());
                        System.out.println(tmp.stream().map(i -> i.toString()).collect(Collectors.joining(" -> ")));
                        while( ! tmp.isEmpty() ) {
                            stk.push(tmp.pop());
                        }
                    }
                }
            );
            stk.pop();
            color.set(u, "BLACK");
            collector.add(u);
        }
    }

    public static void main(String[] args) {

        int[][] graph =  new int[][]{ new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 3},new int[]{0, 5}, new int[]{1, 2}, new int[]{2, 4}, new int[]{3, 2}, new int[]{3, 5}, new int[]{5, 4} };
        List<Integer> result = TopologicalSort.bfs(6, graph);

        System.out.println("Using BFS -> " + result);
        result = TopologicalSort.dfs(6, graph);
        System.out.println("Using DFS -> " + result);

        System.out.println("Graph w/ cycles :");

        int[][] cycleGraph = Arrays.copyOf(graph, graph.length + 1);
        cycleGraph[cycleGraph.length - 1] = new int[]{4, 3};
        result = TopologicalSort.dfs(6, cycleGraph);
        System.out.println(result);
    }
}