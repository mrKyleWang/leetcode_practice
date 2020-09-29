package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月29日
 */
public class BinaryTreePostorderTraversal {


    /*
        给定一个二叉树，返回它的 后序 遍历。

        示例:

        输入: [1,null,2,3]
           1
            \
             2
            /
           3

        输出: [3,2,1]
        进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2");
        System.out.println(postorderTraversal2(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7,null,null,null,8");
        System.out.println(postorderTraversal2(root));
    }

    /**
     * 递归
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            traversal(result, root);
        }
        return result;
    }

    private void traversal(List<Integer> result, TreeNode root) {
        if (root.left != null) {
            traversal(result, root.left);
        }
        if (root.right != null) {
            traversal(result, root.right);
        }
        result.add(root.val);
    }

    /**
     * 迭代
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode pre = root;
            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                if (node.left != null && node.left != pre && node.right != pre) {
                    stack.push(node.left);
                }
                if (node.right != null && (node.left == pre || node.left == null && node.right != pre)) {
                    stack.push(node.right);
                }
                if ((node.left == null && node.right == null) || node.right == pre || (node.left == pre && node.right == null)) {
                    result.add(stack.pop().val);
                }
                pre = node;
            }
        }
        return result;
    }
}
