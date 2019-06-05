package tree;

import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 102. 二叉树的层次遍历
 * @author KyleWang
 * @version 1.0
 * @date 2019年06月04日
 */
public class BinaryTreeLevelOrderTraversal {

	/*
	给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。

		例如:
		给定二叉树: [3,9,20,null,null,15,7],

			3
		   / \
		  9  20
			/  \
		   15   7
		返回其层次遍历结果：

		[
		  [3],
		  [9,20],
		  [15,7]
		]
	 */

	public static void main(String[] args) {
		TreeNode head = new TreeNode(3);
		TreeNode left = new TreeNode(9);
		TreeNode right = new TreeNode(20);
		right.left = new TreeNode(15);
		right.right = new TreeNode(7);
		head.left = left;
		head.right = right;
		for (List<Integer> list : levelOrder(head)) {
			for (Integer val : list) {
				System.out.print(val);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		List<Integer> list = new ArrayList<>();
		// 使用两个队列，分别存储当前层和下一层的node
		LinkedList<TreeNode> queue = new LinkedList<>();
		LinkedList<TreeNode> nextQueue = new LinkedList<>();
		queue.offer(root);
		while (queue.size() > 0) {
			TreeNode node = queue.poll();
			list.add(node.val);
			if (node.left != null) {
				nextQueue.offer(node.left);
			}
			if (node.right != null) {
				nextQueue.offer(node.right);
			}
			if (queue.size() == 0 && nextQueue.size() > 0) {
				result.add(list);
				list = new ArrayList<>();
				queue = nextQueue;
				nextQueue = new LinkedList<>();
			}
		}
		result.add(list);
		return result;
	}
}
