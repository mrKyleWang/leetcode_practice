package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月10日
 */
public class LowestCommonAncestorOfBinaryTree {

    /*
        给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

        例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]


        示例 1:
            输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
            输出: 3
            解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     */

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.left = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        System.out.println(lowestCommonAncestor(root, root.left.right, root.right.right).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return findCommonAncestor(root, p.val, q.val);

    }

    /**
     * 递归向下寻找p或q的值，然后从最下层向上找到第一次同时在cur/left/right中找到p/q两个节点，此节点就是公共祖先
     * 1：当本层有2个TreeNode结果时，即表示当前节点就是最近公共祖先，将cur返回，而在更上层的递归中，不可能再有多个结果，即直接将此结果返回
     * 2：本层只有一个TreeNode时，要么是公共祖先结果，要么是能找到p或q的最高层Node，不管怎么样，都直接将此Node返回
     */
    private TreeNode findCommonAncestor(TreeNode cur, int p, int q) {
        if (cur == null || cur.val == p || cur.val == q) {
            return cur;
        }
        TreeNode leftResult = findCommonAncestor(cur.left, p, q);
        TreeNode rightResult = findCommonAncestor(cur.right, p, q);
        return leftResult == null ? rightResult : rightResult == null ? leftResult : cur;
    }
}
