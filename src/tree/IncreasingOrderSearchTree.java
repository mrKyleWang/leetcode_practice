package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 897. 递增顺序搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月25日
 */
public class IncreasingOrderSearchTree {

    /*
        给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。

           2         1
          / \    ->   \
         1   3         2
                        \
                         3
     */

    @Test
    public void test() {
        TreeNode node = TreeNode.deserialize("2,1,4,null,null,3");
        increasingBST(node).print();
    }

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(0);
        pre = dummy;
        inOrder(root);

        pre.left = null;
        return dummy.right;
    }

    TreeNode pre = null;

    private void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);

            pre.right = node;
            pre.left = null;
            pre = node;

            inOrder(node.right);
        }
    }
}
