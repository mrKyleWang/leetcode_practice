package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 111. 二叉树的最小深度
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月21日
 */
public class MinimumDepthOfBinaryTree {

    /*
        给定一个二叉树，找出其最小深度。
        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
        说明: 叶子节点是指没有子节点的节点。
        示例:
            给定二叉树 [3,9,20,null,null,15,7],
                3
               / \
              9  20
                /  \
               15   7
        返回它的最小深度  2.
     */

    @Test
    public void test() {
        TreeNode node = TreeNode.deserialize("3,9,20,null,null,15,7");
        Assert.assertEquals(2, minDepth(node));
    }

    @Test
    public void test2() {
        TreeNode node = TreeNode.deserialize("1,2");
        Assert.assertEquals(2, minDepth(node));
    }

    public int minDepth(TreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                return 1;
            } else {
                int leftDepth = root.left != null ? minDepth(root.left) + 1 : Integer.MAX_VALUE;
                int rightDepth = root.right != null ? minDepth(root.right) + 1 : Integer.MAX_VALUE;
                return Math.min(leftDepth, rightDepth);
            }
        } else {
            return 0;
        }
    }
}
