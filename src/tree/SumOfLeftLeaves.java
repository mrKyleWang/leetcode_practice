package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 404. 左叶子之和
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月19日
 */
public class SumOfLeftLeaves {


    /*
        计算给定二叉树的所有左叶子之和。
        示例：

            3
           / \
          9  20
            /  \
           15   7

        在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
        Assert.assertEquals(24, sumOfLeftLeaves(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5");
        Assert.assertEquals(4, sumOfLeftLeaves(root));
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }

    public int sumOfLeftLeaves(TreeNode root, boolean left) {
        if (root == null) {
            return 0;
        }
        int sum = 0;

        if (root.left == null && root.right == null && left) {
            sum += root.val;
        }
        sum += sumOfLeftLeaves(root.left, true);
        sum += sumOfLeftLeaves(root.right, false);
        return sum;
    }
}
