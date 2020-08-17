package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 110. 平衡二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月17日
 */
public class BalancedBinaryTree {

    /*
        给定一个二叉树，判断它是否是高度平衡的二叉树。
        本题中，一棵高度平衡二叉树定义为：
        一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

        示例 1:
            给定二叉树 [3,9,20,null,null,15,7]

                3
               / \
              9  20
                /  \
               15   7
            返回 true 。

        示例 2:
            给定二叉树 [1,2,2,3,3,null,null,4,4]

                   1
                  / \
                 2   2
                / \
               3   3
              / \
             4   4
            返回 false 。
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
        Assert.assertEquals(true, isBalanced(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2,2,3,3,null,null,4,4");
        Assert.assertEquals(false, isBalanced(root));
    }

    /**
     * 深度优先，递归每个节点的高度
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return root != null && getHeight(root) > 0;
    }

    private int getHeight(TreeNode node) {
        int left = 0;
        int right = 0;
        if (node.left != null) {
            left = getHeight(node.left);
            if (left < 0) {
                return -1;
            }
        }
        if (node.right != null) {
            right = getHeight(node.right);
            if (right < 0) {
                return -1;
            }
        }
        int min = Math.min(left, right);
        int max = Math.max(left, right);
        if (max > min + 1) {
            return -1;
        }
        return max + 1;
    }
}
