package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 112. 路径总和
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月07日
 */
public class PathSum {

    /*
        给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
        说明: 叶子节点是指没有子节点的节点。
        示例: 
            给定如下二叉树，以及目标和 sum = 22，

                          5
                         / \
                        4   8
                       /   / \
                      11  13  4
                     /  \      \
                    7    2      1
            返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("5,4,8,11,null,13,4,7,2,null,null,null,1");
        Assert.assertTrue(hasPathSum(root, 22));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2");
        Assert.assertFalse(hasPathSum(root, 1));
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return root != null && hasPathSum(root, 0, sum);
    }

    public boolean hasPathSum(TreeNode node, int cur, int sum) {
        if (node == null) {
            return cur == sum;
        }
        if (node.left == null) {
            return hasPathSum(node.right, cur + node.val, sum);
        } else if (node.right == null) {
            return hasPathSum(node.left, cur + node.val, sum);
        }
        return hasPathSum(node.left, cur + node.val, sum) || hasPathSum(node.right, cur + node.val, sum);
    }
}
