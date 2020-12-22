package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月22日
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    /*
        给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
        例如：
        给定二叉树 [3,9,20,null,null,15,7],

            3
           / \
          9  20
            /  \
           15   7
        返回锯齿形层序遍历如下：

        [
          [3],
          [20,9],
          [15,7]
        ]
     */
    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("3,9,20,null,null,15,7,16,8");
        System.out.println(zigzagLevelOrder(root));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        left.add(root);
        while (!left.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            while (!left.isEmpty()) {
                TreeNode node = left.pollLast();
                if (node != null) {
                    list.add(node.val);
                    right.add(node.left);
                    right.add(node.right);
                }
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
            list = new ArrayList<>();
            while (!right.isEmpty()) {
                TreeNode node = right.pollLast();
                if (node != null) {
                    list.add(node.val);
                    left.add(node.right);
                    left.add(node.left);
                }
            }
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        return result;
    }
}
