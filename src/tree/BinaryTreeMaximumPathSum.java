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
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int pathSum = maxPathSum(root);
        Assert.assertEquals(6, pathSum);
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        int pathSum = maxPathSum(root);
        Assert.assertEquals(42, pathSum);
    }

    @Test
    public void test3() {
        TreeNode root = new TreeNode(-10);
        int pathSum = maxPathSum(root);
        Assert.assertEquals(-10, pathSum);
    }

    @Test
    public void test4() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);
        int pathSum = maxPathSum(root);
        Assert.assertEquals(4, pathSum);
    }

    @Test
    public void test5() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);
        int pathSum = maxPathSum(root);
        Assert.assertEquals(48, pathSum);
    }


    public int maxPathSum(TreeNode root) {
        findMaxPathSum(root);
        return maxPathSum;
    }

    int maxPathSum = Integer.MIN_VALUE;

    /**
     * 如果想从当前节点返回上一层节点，要么从左子节点经过，要么从右子节点经过，或者不经过子节点
     * @param node
     * @return
     */
    private int findMaxPathSum(TreeNode node) {
        int tempMax = node.val;
        int tempSum = node.val;
        if (node.left != null) {
            int leftMaxSum = findMaxPathSum(node.left);
            tempSum += leftMaxSum;
            tempMax = max(tempMax, node.val + leftMaxSum);
        }
        if (node.right != null) {
            int rightMaxSum = findMaxPathSum(node.right);
            tempSum += rightMaxSum;
            tempMax = max(tempMax, node.val + rightMaxSum);
        }
        maxPathSum = max(maxPathSum, tempMax, tempSum);
        return tempMax;
    }

    private int max(int... nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }
        return max;
    }

}
