package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月26日
 */
public class PathSumII {

    /*
        给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
        说明: 叶子节点是指没有子节点的节点。

        示例:
        给定如下二叉树，以及目标和 sum = 22，

                      5
                     / \
                    4   8
                   /   / \
                  11  13  4
                 /  \    / \
                7    2  5   1
        返回:
            [
               [5,4,11,2],
               [5,8,4,5]
            ]
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("5,4,8,11,null,13,4,7,2,null,null,5,1");
        System.out.println(pathSum(root, 22));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,2");
        System.out.println(pathSum(root, 1));
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            pathSum(result, new ArrayList<>(), root, 0, sum);
        }
        return result;
    }

    private void pathSum(List<List<Integer>> result, List<Integer> path, TreeNode node, int cur, int sum) {
        cur += node.val;
        path.add(node.val);
        if (cur == sum && node.left == null && node.right == null) {
            result.add(path);
            return;
        }
        if (node.left != null) {
            pathSum(result, new ArrayList<>(path), node.left, cur, sum);
        }
        if (node.right != null) {
            pathSum(result, new ArrayList<>(path), node.right, cur, sum);
        }
    }
}
