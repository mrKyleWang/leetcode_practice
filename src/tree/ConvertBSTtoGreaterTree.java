package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月21日
 */
public class ConvertBSTtoGreaterTree {

    /*
        给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
        例如：
        输入: 原始二叉搜索树:
                      5
                    /   \
                   2     13
        输出: 转换为累加树:
                     18
                    /   \
                  20     13
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("5,2,13");
        System.out.println(convertBST(root).serialize());
    }

    int sum = 0;

    /**
     * 思路：从大到小遍历，然后逐步累加
     * 然后BST本身中序遍历是从小到大的，那么从右节点开始的中序遍历即为从大到小
     */
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
