package patterns.backtracking;

import patterns.TreeNode;

import java.util.*;

class FindAllTreePaths {
    public static List<List<Integer>> findPaths(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> buffer = new ArrayList<>();
        findPathsRecursive(root, buffer, allPaths);
        return allPaths;
    }

    public static void findPathsRecursive(TreeNode root, List<Integer> buffer, List<List<Integer>> collector) {
        if( root == null ) return;

        buffer.add(root.val);

        if( root.left == null && root.right == null ) {
            collector.add(new ArrayList<>(buffer));
        }

        if( root.left != null ) findPathsRecursive(root.left, buffer, collector);
        if( root.right != null ) findPathsRecursive(root.right, buffer, collector);

        //Backtrack
        buffer.remove(buffer.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(12);
        root.left = new TreeNode(7);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(5);
        List<List<Integer>> result = FindAllTreePaths.findPaths(root);
        System.out.println("Tree paths : " + result);
    }
}

