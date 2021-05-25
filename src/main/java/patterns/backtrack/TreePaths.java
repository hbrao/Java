package patterns.backtrack;

import patterns.TreeNode;
import java.util.*;

class TreePaths {

    public static List<List<Integer>> collectAllPaths(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>();
        collectAllPaths(root, buffer, allPaths);
        return allPaths;
    }

    public static void collectAllPaths(TreeNode root, List<Integer> buffer, List<List<Integer>> collector) {
        if( root == null ) return;

        buffer.add(root.val);

        if( root.left == null && root.right == null ) {
            collector.add(new ArrayList<>(buffer));
        }

        if( root.left != null ) collectAllPaths(root.left, buffer, collector);
        if( root.right != null ) collectAllPaths(root.right, buffer, collector);

        //Backtrack
        buffer.remove(buffer.size() - 1);
    }

    public static boolean findPath(TreeNode root, Integer[] sequence) {
        return findPath(root, 0, sequence);
    }

    public static Boolean findPath(TreeNode n, Integer d, Integer[] sequence) {
        if( n == null ) return false;

        if( n.left == null && n.right == null ) {
            if( d == sequence.length - 1 && sequence[d] == n.val ) {
                return true;
            } else {
                return false;
            }
        }

        if( d < sequence.length && n.val == sequence[d] ) {
            return findPath(n.left, d + 1, sequence) || findPath(n.right, d + 1, sequence);
        } else {
            return false;
        }
    }

    public static int findSumOfPathNumbers(TreeNode node, Integer sum) {
        if( node == null ) return 0;

        sum = 10 * sum + node.val;

        if( node.left == null && node.right == null ) {
            return sum;
        }

        return findSumOfPathNumbers(node.left, sum) + findSumOfPathNumbers(node.right, sum);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);

        List<List<Integer>> result = TreePaths.collectAllPaths(root);

        System.out.println("Tree paths: " + result);
        System.out.println("Path " + result.get(0) + " exists ? " + findPath(root, result.get(0).toArray(new Integer[]{})));
        System.out.println("Tree paths total sum: " + findSumOfPathNumbers(root, 0));
    }
}

