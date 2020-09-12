package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 637. 二叉树的层平均值
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月12日
 */
public class AverageOfLevelsInBinaryTree {

    /*
        给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
        示例 1：
            输入：
                    3
                   / \
                  9  20
                    /  \
                   15   7
            输出：[3, 14.5, 11]
            解释：第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
         

        提示：节点值的范围在32位有符号整数范围内。
     */


    @Test
    public void test() {
        TreeNode node = TreeNode.deserialize("3,9,20,null,null,15,7");
        System.out.println(averageOfLevels(node));
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        double temp = 0.0;
        int i = 0;
        // 使用两个队列，分别存储当前层和下一层的node
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<TreeNode> nextQueue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            temp += node.val;
            i++;
            if (node.left != null) {
                nextQueue.offer(node.left);
            }
            if (node.right != null) {
                nextQueue.offer(node.right);
            }
            if (queue.size() == 0 && nextQueue.size() > 0) {
                result.add(temp / i);
                temp = 0.0;
                i = 0;
                queue = nextQueue;
                nextQueue = new LinkedList<>();
            }
        }
        result.add(temp / i);
        return result;
    }

}
