package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 114. 二叉树展开为链表
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月02日
 */
public class FlattenBinaryTreeToLinkedList {

    /*
    给定一个二叉树，原地将它展开为一个单链表。
        例如，给定二叉树

            1
           / \
          2   5
         / \   \
        3   4   6
        将其展开为：

        1
         \
          2
           \
            3
             \
              4
               \
                5
                 \
                  6
     */


    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);
        flatten(root);
        System.out.println();
    }

    /**
     * 递归：left插入到root和right中间，找到right的最后一个节点作为上层root原right的父节点
     */
    public void flatten(TreeNode root) {
        if (root != null) {
            move(root);
        }
    }

    private TreeNode move(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode last = null;
        if (root.left != null) {
            TreeNode newRight = move(root.left);
            newRight.right = root.right;
            root.right = root.left;
            root.left = null;
            last = newRight;
        }
        if (root.right != null) {
            last = move(root.right);
        }
        return last;
    }
}
