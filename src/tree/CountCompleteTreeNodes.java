package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月24日
 */
public class CountCompleteTreeNodes {

    /*
        给出一个完全二叉树，求出该树的节点个数。
        说明：
            完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
            并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
        示例:
            输入:
                1
               / \
              2   3
             / \  /
            4  5 6

            输出: 6
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2,3");
        Assert.assertEquals(6, countNodes(root));
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLevel = 1;
        int cur = 1;
        TreeNode node = root;
        while (node.left != null) {
            count += cur;
            maxLevel++;
            cur *= 2;
            node = node.left;
        }
        count(root, 1, maxLevel);


        return count;
    }

    int count = 0;

    private boolean count(TreeNode node, int level, int maxLevel) {
        if (level < maxLevel) {
            if (!count(node.left, level + 1, maxLevel)) {
                return false;
            }
            return count(node.right, level + 1, maxLevel);
        } else {
            if (node != null) {
                count++;
                return true;
            }
            return false;
        }
    }
}

