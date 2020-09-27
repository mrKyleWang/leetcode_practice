package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月27日
 */
public class LowestCommonAncestorOfABinarySearchTree {


    /*
        给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
        例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
                6
               / \
              2   8
             / \ / \
            0  4 7  9
              / \
             3  5

        示例 1:
            输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
            输出: 6
            解释: 节点 2 和节点 8 的最近公共祖先是 6。
        示例 2:
            输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
            输出: 2
            解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

        说明:
            所有节点的值都是唯一的。
            p、q 为不同节点且均存在于给定的二叉搜索树中。

     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("6,2,8,0,4,7,9,null,null,3,5");
        System.out.println(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8)).val);
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("6,2,8,0,4,7,9,null,null,3,5");
        System.out.println(lowestCommonAncestor(root, new TreeNode(2), new TreeNode(4)).val);
    }

    /**
     * 三种情况下可确定公共祖先：
     * 对于node，p、q分别在左右子节点，则node是最近公共祖先
     * q在p的左、右子节点，则p是最近公共祖先
     * p在q的左、右子节点，则q是最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int m = Math.min(p.val, q.val);
        int n = Math.max(p.val, q.val);
        return lowestCommonAncestor(root, m, n);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int m, int n) {
        if (root.val < m) {
            return lowestCommonAncestor(root.right, m, n);
        }
        if (root.val > n) {
            return lowestCommonAncestor(root.left, m, n);
        }
        return root;
    }
}
