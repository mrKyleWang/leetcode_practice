package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月27日
 */
public class BinaryTreePreorderTraversal {

    /*
        给定一个二叉树，返回它的 前序 遍历。
         示例:

        输入: [1,null,2,3]
           1
            \
             2
            /
           3
        输出: [1,2,3]
        进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,null,2,3");
        System.out.println(preorderTraversal(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7,null,null,null,8");
        System.out.println(preorderTraversal(root));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            traversal(result, root);
        }
        return result;
    }

    private void traversal(List<Integer> result, TreeNode root) {
        result.add(root.val);
        if (root.left != null) {
            traversal(result, root.left);
        }
        if (root.right != null) {
            traversal(result, root.right);
        }
    }

}
