package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历
 * @author KyleWang
 * @version 1.0
 * @date 2020/01/03
 */
public class InorderTraversal {

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println(inorderTraversal(root));
        System.out.println(inorderTraversal2(root));
    }

    /**
     * 递归解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            traversal(root, result);
        }
        return result;
    }

    public void traversal(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            traversal(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            traversal(root.right, list);
        }
    }

    /**
     * 使用栈解法
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }
}
