package tree;

import tree.entity.TreeNode;

/**
 * 二叉树的最大深度（104）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月24日
 */
public class MaximumDepthOfBinaryTree {

	/*
	 * 给定一个二叉树，找出其最大深度。
	 *
	 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 *
	 * 说明: 叶子节点是指没有子节点的节点。
	 * 示例： 给定二叉树 [3,9,20,null,null,15,7]，
	 *
	 *   3
	 *  / \
	 * 9  20
	 *	 /  \
	 *  15   7
	 * 返回它的最大深度 3 。
	 */

	public static void main(String[] args) {
		TreeNode head = new TreeNode(3);
		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
		head.left = left;
		head.right = right;
		System.out.println(maxDepth(head));
	}

	public static int maxDepth(TreeNode root) {
		int[] depthData = { 0, 0 };
		return readNode(depthData, root)[1];
	}

	private static int[] readNode(int[] depthData, TreeNode node) {
		int currentDepth = depthData[0] + 1;
		int maxDepth = depthData[1];
		if (node != null) {
			if (currentDepth > maxDepth) {
				depthData[1] = currentDepth;
			}
		} else {
			return depthData;
		}
		depthData[0] = currentDepth;
		depthData = readNode(depthData, node.left);
		depthData = readNode(depthData, node.right);
		depthData[0] = currentDepth - 1;
		return depthData;
	}
}
