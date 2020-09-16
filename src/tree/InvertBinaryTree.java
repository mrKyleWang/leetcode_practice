package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 226. 翻转二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月16日
 */
public class InvertBinaryTree {


    /*
           翻转一棵二叉树。

            示例：

            输入：

                 4
               /   \
              2     7
             / \   / \
            1   3 6   9
            输出：

                 4
               /   \
              7     2
             / \   / \
            9   6 3   1
     */


    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("4,2,7,1,3,6,9");
        System.out.println(invertTree(root).serialize());
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null){
            invert(root);
        }
        return root;
    }

    public void invert(TreeNode root) {
        if (root.left != null) {
            invert(root.left);
        }

        if (root.right != null) {
            invert(root.right);
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
}
