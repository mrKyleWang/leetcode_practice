package tree;

import tree.entity.TreeNode;

import java.util.LinkedList;

/**
 * 101. 对称二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月28日
 */
public class SymmetricTree {

	/*
	给定一个二叉树，检查它是否是镜像对称的。

	例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

		1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

		1
	   / \
	  2   2
	   \   \
	   2    2
	 */

	public static void main(String[] args) {

		TreeNode head = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		//left.left = new TreeNode(3);
		left.right = new TreeNode(2);
		right.left = new TreeNode(2);
		//right.right = new TreeNode(2);
		head.left = left;
		head.right = right;
		System.out.println(isSymmetric(head));

	}


	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		LinkedList<TreeNode> list = new LinkedList();
		list.push(root.left);
		list.push(root.right);

		while (list.size() != 0) {
			TreeNode left = list.pop();
			TreeNode right = list.pop();
			if (left == null && right == null) {
				continue;
			}
			if (left == null || right == null) {
				return false;
			}
			if (left.val != right.val) {
				return false;
			}
			// 关键在此，添加进队列时，以对称的为一组添加
			list.push(left.left);
			list.push(right.right);
			list.push(right.left);
			list.push(left.right);
		}
		return true;
	}

}
