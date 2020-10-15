package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 530. 二叉搜索树的最小绝对差
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月15日
 */
public class MinimumAbsoluteDifferenceInBST {

    /*
        给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
        示例：
            输入：

               1
                \
                 3
                /
               2

            输出：
            1

            解释：
            最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

        提示：
        树中至少有 2 个节点。

     */


    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,null,3,2");
        Assert.assertEquals(1, getMinimumDifference(root));
    }

    int min = Integer.MAX_VALUE;
    int pre = -1;

    /**
     * 二叉搜索树可转换为一个升序数组，有序数组中差的绝对值最小一定是相邻节点
     */
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return min;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (pre != -1) {
            min = Math.min(min, node.val - pre);
        }
        pre = node.val;
        dfs(node.right);
    }
}
