package patterns;

import java.util.*;
import java.util.stream.*;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

@RunWith(Parameterized.class)
public class BuildBinaryTree {

    int[] preorder;
    int[] inorder;

    //Constructor used by JUnit to set test data.
    public BuildBinaryTree(List<int []> input) {
        this.preorder  = input.get(0);
        this.inorder = input.get(1);
    }

    //API to construct a Binary tree from pre-order and in-order array.
    public TreeNode buildTree() {
        return buildTree(this.preorder, this.inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        List<Integer> pre = Arrays.stream(preorder).boxed().collect(Collectors.toList());
        List<Integer> ino = Arrays.stream(inorder).boxed().collect(Collectors.toList());

        System.out.println(pre.toString());
        System.out.println(ino.toString());

        return buildTreeHelper(pre, ino);
    }

    public TreeNode buildTreeHelper(List<Integer> preorder, List<Integer> inorder) {
        //Base Case 1
        if ( preorder.isEmpty() && inorder.isEmpty()) {
            return null;
        }
        //Base Case 2
        if ( preorder.size() == 1 && inorder.size() == 1 ) {
            return new TreeNode(preorder.get(0));
        }

        //Solve sub problems.
        TreeNode rootNode = new TreeNode(preorder.get(0));

        Integer j = inorder.indexOf(rootNode.val);
        List<Integer> inOrderLeftSub = inorder.subList(0,j);
        List<Integer> inOrderRightSub = inorder.subList(j + 1, inorder.size());
        if ( inOrderLeftSub.size() > 0 ) {
            //Build left subtree
            List<Integer> preOrderLeftSub = preorder.subList(1,inOrderLeftSub.size() + 1);
            rootNode.left = buildTreeHelper(preOrderLeftSub, inOrderLeftSub);
        }

        if ( inOrderRightSub.size() > 0 ) {
            //Build right subtree
            List<Integer> preOrderRightSub = preorder.subList( 1 + inOrderLeftSub.size(), preorder.size());
            rootNode.right = buildTreeHelper(preOrderRightSub, inOrderRightSub);
        }

        return rootNode;
    }

    //Return inorder traversal of a binary tree given its root node.
    private List<Integer> inOrder(TreeNode root) {
        Deque<TreeNode> stk = new LinkedList<>();
        List<Integer> data = new ArrayList<>();
        TreeNode n  = root;
        while ( ! stk.isEmpty()  || n != null ) {
            while ( n != null ) {
                stk.push(n);
                n = n.left;
            }
            n = stk.pop();
            data.add(n.val);
            n = n.right;
        }
        return data;
    }

    //Test Data
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][] {
                { Arrays.asList(new int[] {3,9,20,15,7}, new int[] {9,3,15,20,7}) },
                { Arrays.asList(new int[] {1,2,3}, new int[]{3, 2, 1}) }
        });
    }

    //Tests
    @Test
    public void testBinaryTree() {
        TreeNode root  = buildTree(this.preorder, this.inorder);
        Assert.assertTrue(IntStream.of(inorder).boxed().collect(Collectors.toList()).equals(inOrder(root)));
    }
}
