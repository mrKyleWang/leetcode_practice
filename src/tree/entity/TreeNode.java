package tree.entity;

import java.util.LinkedList;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	/** 层次遍历输出 */
	public void print() {
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.offer(this);
		while (queue.size() > 0) {
			TreeNode node = queue.poll();
			System.out.println(node.val);
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}

	}
}