package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 938. 二叉搜索树的范围和
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月27日
 */
public class RangeSumOfBST {

    /*
        给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
        提示：
            树中节点数目在范围 [1, 2 * 104] 内
            1 <= Node.val <= 105
            1 <= low <= high <= 105
            所有 Node.val 互不相同
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("10,5,15,3,7,null,18");
        Assert.assertEquals(32, rangeSumBST(root, 7, 15));
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        inOrder(root, low, high);
        return sum;
    }

    private int sum = 0;

    private void inOrder(TreeNode node, int low, int high) {
        if (node != null) {
            if (node.val >= low) {
                inOrder(node.left, low, high);
                if (node.val <= high) {
                    sum += node.val;
                    inOrder(node.right, low, high);
                }
            } else {
                inOrder(node.right, low, high);
            }

        }
    }
}
