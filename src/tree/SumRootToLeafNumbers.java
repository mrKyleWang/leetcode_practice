package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 129. 求根到叶子节点数字之和
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月29日
 */
public class SumRootToLeafNumbers {

    /*
        给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
        例如，从根到叶子节点路径 1->2->3 代表数字 123。
        计算从根到叶子节点生成的所有数字之和。

        示例 1:
            输入: [1,2,3]
                    1
                   / \
                  2   3
            输出: 25
            解释:
                从根到叶子节点路径 1->2 代表数字 12.
                从根到叶子节点路径 1->3 代表数字 13.
                因此，数字总和 = 12 + 13 = 25.
        示例 2:
            输入: [4,9,0,5,1]
                    4
                   / \
                  9   0
                 / \
                5   1
            输出: 1026
            解释:
                从根到叶子节点路径 4->9->5 代表数字 495.
                从根到叶子节点路径 4->9->1 代表数字 491.
                从根到叶子节点路径 4->0 代表数字 40.
                因此，数字总和 = 495 + 491 + 40 = 1026.
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2,3");
        Assert.assertEquals(25, sumNumbers(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("4,9,0,5,1");
        Assert.assertEquals(1026, sumNumbers(root));
    }

    int result = 0;

    public int sumNumbers(TreeNode root) {
        sum(0, root);
        return result;
    }

    private void sum(int preVal, TreeNode node) {
        if (node == null) {
            return;
        }
        int cur = preVal * 10 + node.val;
        if (node.left == null && node.right == null) {
            result += cur;
        } else {
            sum(cur, node.left);
            sum(cur, node.right);
        }
    }
}
