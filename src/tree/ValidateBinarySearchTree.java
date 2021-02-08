package tree;

import tree.entity.TreeNode;

/**
 * 98. 验证二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月28日
 */
public class ValidateBinarySearchTree {

	/*
	给定一个二叉树，判断其是否是一个有效的二叉搜索树。
	假设一个二叉搜索树具有如下特征：
	节点的左子树只包含小于当前节点的数。
	节点的右子树只包含大于当前节点的数。
	所有左子树和右子树自身必须也是二叉搜索树。
	示例 1:

	输入:
		2
	   / \
	  1   3
	输出: true
	示例 2:

	输入:
		5
	   / \
	  1   4
		 / \
		3   6
	输出: false
	解释: 输入为: [5,1,4,null,null,3,6]。
		 根节点的值为 5 ，但是其右子节点值为 4 。
	 */

    public static void main(String[] args) {
        TreeNode head = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(15);
        right.left = new TreeNode(11);
        right.right = new TreeNode(20);
        head.left = left;
        head.right = right;
        System.out.println(isValidBST(head));
        System.out.println(isValidBST2(head));
    }

    /**
     * 解法1：中序遍历，判断是否升序
     */
    public static boolean isValidBST(TreeNode root) {
        return inOrder(root);
    }

    private static long pre = Long.MIN_VALUE;

    /**
     * 中序遍历
     */
    private static boolean inOrder(TreeNode node) {
        if (node == null) {
            return true;
        }
        if (!inOrder(node.left)){
            return false;
        }
        if (node.val<=pre){
            return false;
        }
        pre = node.val;
        return (inOrder(node.right));
    }

    /**
     * 解法2：递归判断左右子树大小
     */
    public static boolean isValidBST2(TreeNode root) {
        return checkChildren(root) != null;
    }

    private static int[] checkChildren(TreeNode node) {
        int currentMin = node.val;
        int currentMax = node.val;
        if (node.left != null) {
            int[] checkResult = checkChildren(node.left);
            if (checkResult == null || (checkResult[0] > node.val)) {
                return null;
            } else {
                currentMin = checkResult[0];
            }
        }
        if (node.right != null) {
            int[] checkResult = checkChildren(node.right);
            if (checkResult == null || (checkResult[0] < node.val)) {
                return null;
            } else {
                currentMax = checkResult[1];
            }
        }
        int[] currentResult = new int[2];
        currentResult[0] = currentMin;
        currentResult[1] = currentMax;
        return currentResult;
    }
}
