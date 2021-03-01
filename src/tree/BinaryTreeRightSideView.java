package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 199. 二叉树的右视图
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月01日
 */
public class BinaryTreeRightSideView {

    /*
        给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
        示例:

            输入: [1,2,3,null,5,null,4]
            输出: [1, 3, 4]
            解释:

               1            <---
             /   \
            2     3         <---
             \     \
              5     4       <---
     */


    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2,3,null,5,null,4");
        System.out.println(rightSideView(root));
    }

    /**
     * 基于层次遍历，每次将当前一层的最后一个节点的值加入到结果
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root != null) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    if (i == size - 1) {
                        res.add(node.val);
                    }
                }
            }
        }
        return res;
    }
}
