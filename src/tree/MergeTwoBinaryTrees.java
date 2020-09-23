package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 617. 合并二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月23日
 */
public class MergeTwoBinaryTrees {


    /*
        给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
        你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

        示例 1:
            输入:
                Tree 1                     Tree 2
                      1                         2
                     / \                       / \
                    3   2                     1   3
                   /                           \   \
                  5                             4   7
            输出:
            合并后的树:
                     3
                    / \
                   4   5
                  / \   \
                 5   4   7
        注意: 合并必须从两个树的根节点开始。
     */

    @Test
    public void test() {
        TreeNode t1 = TreeNode.deserialize("1,3,2,5,null");
        TreeNode t2 = TreeNode.deserialize("2,1,3,null,4,null,7");
        TreeNode mergeTrees = mergeTrees(t1, t2);
        System.out.println(mergeTrees.serialize());
    }

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        int val = 0;
        TreeNode l1 = null;
        TreeNode r1 = null;
        TreeNode l2 = null;
        TreeNode r2 = null;
        if (t1 != null) {
            val += t1.val;
            l1 = t1.left;
            r1 = t1.right;
        }
        if (t2 != null) {
            val += t2.val;
            l2 = t2.left;
            r2 = t2.right;
        }
        TreeNode merge = new TreeNode(val);
        merge.left = mergeTrees(l1, l2);
        merge.right = mergeTrees(r1, r2);
        return merge;
    }
}
