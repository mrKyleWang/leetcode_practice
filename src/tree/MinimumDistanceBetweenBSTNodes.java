package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 783. 二叉搜索树节点最小距离
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月13日
 */
public class MinimumDistanceBetweenBSTNodes {

    /*
        给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("90,69,null,49,89,null,52");
        Assert.assertEquals(1, minDiffInBST(root));
    }

    /**
     * 基于中序遍历的思路，保存前一个节点pre，每次进行比较
     */
    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return res;
    }

    TreeNode pre = null;
    int res = Integer.MAX_VALUE;

    private void dfs(TreeNode root) {
        if (root != null) {
            dfs(root.left);
            if (pre != null) {
                res = Math.min(res, Math.abs(pre.val - root.val));
            }
            pre = root;
            dfs(root.right);
        }
    }
}
