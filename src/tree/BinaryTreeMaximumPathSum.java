package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 124. 二叉树中的最大路径和
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月21日
 */
public class BinaryTreeMaximumPathSum {

    /*
        给定一个非空二叉树，返回其最大路径和。
        本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

        示例 1:

        输入: [1,2,3]

               1
              / \
             2   3

        输出: 6
        示例 2:

        输入: [-10,9,20,null,null,15,7]

           -10
           / \
          9  20
            /  \
           15   7

        输出: 42

        [5,4,8,11,null,13,4,7,2,null,null,null,1]   48

            5
           / \
          4   8
         /   / \
        11  13  4
       /  \      \
      7    2      1

     */

    @Test
    public void test() {
        Assert.assertEquals(6, maxPathSum(TreeNode.deserialize("1,2,3")));
    }

    @Test
    public void test2() {
        Assert.assertEquals(42, maxPathSum(TreeNode.deserialize("-10,9,20,null,null,15,7")));
    }

    @Test
    public void test5() {
        Assert.assertEquals(48, maxPathSum(TreeNode.deserialize("5,4,8,11,null,13,4,7,2,null,null,null,1")));
    }


    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return res;
    }

    int res = Integer.MIN_VALUE;

    /**
     * 用于计算 包含当前node节点时，并可继续向上连接的最大路径和
     * 总共有4种路径情况：
     *     1. node
     *     2. node ~ node.left
     *     3. node ~ node.right
     *     4. node.left ~ node ~ node.right （只参与res的计算，不能提供给上层父节点连接）
     */
    private int findMaxPathSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = findMaxPathSum(node.left);
        int rightMax = findMaxPathSum(node.right);
        int curMaxPath = Math.max(node.val, Math.max(leftMax, rightMax) + node.val);
        res = Math.max(res, Math.max(curMaxPath, leftMax + rightMax + node.val));
        return curMaxPath;
    }


}
