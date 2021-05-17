package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 993. 二叉树的堂兄弟节点
 * @author KyleWang
 * @version 1.0
 * @date 2021年05月17日
 */
public class CousinsInBinaryTree {
    
    /*
        在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
        如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
        我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
        只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2,3,4,5,6,7");
        Assert.assertTrue(isCousins(root, 5, 7));
        Assert.assertFalse(isCousins(root, 3, 6));
    }

    private int depX = 0, depY = 0, pX = 0, pY = 0;

    public boolean isCousins(TreeNode root, int x, int y) {
        dfs(root, 1, x, y, 0);
        return depX == depY && pX != pY;
    }

    private void dfs(TreeNode node, int dep, int x, int y, int p) {
        if (node != null) {
            if (node.val == x) {
                depX = dep;
                pX = p;
            } else if (node.val == y) {
                depY = dep;
                pY = p;
            } else {
                dfs(node.left, dep + 1, x, y, node.val);
                dfs(node.right, dep + 1, x, y, node.val);
            }
        }
    }
}
