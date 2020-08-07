package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 100. 相同的树
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月07日
 */
public class SameTree {

    /*
        给定两个二叉树，编写一个函数来检验它们是否相同。
        如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

        示例 1:
            输入:       1         1
                      / \       / \
                     2   3     2   3

                    [1,2,3],   [1,2,3]
            输出: true
        示例 2:
            输入:      1          1
                      /           \
                     2             2

                    [1,2],     [1,null,2]
            输出: false
        示例 3:
            输入:       1         1
                      / \       / \
                     2   1     1   2

                    [1,2,1],   [1,1,2]
            输出: false
     */

    @Test
    public void test() {
        TreeNode p = TreeNode.deserialize("1,2,3");
        TreeNode q = TreeNode.deserialize("1,2,3");
        Assert.assertEquals(true, isSameTree(p, q));
    }

    @Test
    public void test2() {
        TreeNode p = TreeNode.deserialize("1,2");
        TreeNode q = TreeNode.deserialize("1,null,3");
        Assert.assertEquals(false, isSameTree(p, q));
    }

    @Test
    public void test3() {
        TreeNode p = TreeNode.deserialize("1,2,1");
        TreeNode q = TreeNode.deserialize("1,1,3");
        Assert.assertEquals(false, isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }


}
