package patterns.iterative;

import org.junit.*;
import patterns.BuildBinaryTree;
import patterns.TreeNode;

import java.util.*;

public class InOrderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if ( root.left == null && root.right == null ) return null;
        if ( p.right != null ) {
            return findLeftMostNode(p.right);
        } else {
            Deque<TreeNode> stk = new LinkedList<>();
            TreeNode n  = root;
            TreeNode prev = null;
            while ( ! stk.isEmpty() || n != null  ) {
                while ( n != null ) {
                    stk.push(n);
                    n = n.left;
                }
                n = stk.pop();
                if ( prev != null && prev.val == p.val ) {
                    return n;
                }
                prev = n;
                n = n.right;
            }
            return null;
        }
    }

    public TreeNode findLeftMostNode(TreeNode n) {
        while ( n.left != null ) {
            n = n.left;
        }
        return n;
    }

    @Test
    public void testInOrderSuccessor() {
        TreeNode root = new BuildBinaryTree(List.of(new int[]{2,1,3}, new int[]{1,2,3})).buildTree();
        TreeNode p = root.left;
        TreeNode successor = inorderSuccessor(root, p);
        Assert.assertTrue(root.val == successor.val);
    }
}