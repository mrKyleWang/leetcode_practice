package tree;

import tree.entity.TreeNode;

import java.util.Arrays;

/**
 * 108. 将有序数组转换为二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2019年06月12日
 */
public class SortedArrayToBST {

	/*
	将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
	本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
	示例:
	给定有序数组: [-10,-3,0,5,9],
	一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

		 0
	    / \
	  -3   9
	  /   /
	-10  5
	*/

	public static void main(String[] args) {
		int[] nums = { -10, -3, 0, 5, 9 };
		TreeNode treeNode = sortedArrayToBST(nums);
		if (treeNode != null) {
			treeNode.print();
		}
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums != null && nums.length > 0) {
			return getLeafNode(nums);
		}
		return null;
	}

	private static TreeNode getLeafNode(int[] nums) {
		int length = nums.length;
		if (length > 1) {
			int rootIndex = length / 2;
			TreeNode node = new TreeNode(nums[rootIndex]);
			int[] leftNums = Arrays.copyOfRange(nums, 0, rootIndex);
			node.left = getLeafNode(leftNums);
			if (rootIndex < length - 1) {
				int[] rightNums = Arrays.copyOfRange(nums, rootIndex + 1, length);
				node.right = getLeafNode(rightNums);
			}
			return node;
		}
		return new TreeNode(nums[0]);
	}
}
