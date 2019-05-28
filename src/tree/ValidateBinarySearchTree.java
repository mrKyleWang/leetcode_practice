package tree;

import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
	}

	public static boolean isValidBST(TreeNode root) {
		// 先中序遍历，然后判断是否升序
		List<Integer> list = new ArrayList<>();
		if (root != null) {
			addToList(root, list);
		}
		for (int i = 0; i < list.size(); i++) {
			if (i > 0 && list.get(i) <= list.get(i - 1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 中序遍历
	 * @param node
	 * @param list
	 */
	private static void addToList(TreeNode node, List<Integer> list) {
		if (node != null) {
			addToList(node.left, list);
			list.add(node.val);
			addToList(node.right, list);
		}
	}
}
