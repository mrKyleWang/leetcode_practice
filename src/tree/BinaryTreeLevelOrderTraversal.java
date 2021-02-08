package tree;

import org.junit.Test;
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

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7");
        for (List<Integer> list : levelOrder(root)) {
            for (Integer val : list) {
                System.out.print(val);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
