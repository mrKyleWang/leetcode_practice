package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 99. 恢复二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月08日
 */
public class RecoverBinarySearchTree {

    /*
        二叉搜索树中的两个节点被错误地交换。
        请在不改变其结构的情况下，恢复这棵树。

        示例 1:
            输入: [1,3,null,null,2]
               1
              /
             3
              \
               2
            输出: [3,1,null,null,2]
               3
              /
             1
              \
               2
        示例 2:
            输入: [3,1,4,null,null,2]
              3
             / \
            1   4
               /
              2
            输出: [2,1,4,null,null,3]
              2
             / \
            1   4
               /
              3
        进阶:
            使用 O(n) 空间复杂度的解法很容易实现。
            你能想出一个只使用常数空间的解决方案吗？
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,3,null,null,2");
        recoverTree(root);
        System.out.println(root.serialize());
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("3,1,4,null,null,2");
        recoverTree(root);
        System.out.println(root.serialize());
    }

    /**
     * 中序遍历，遇到降序则记录下来（搜索树中序遍历一定是升序）
     *
     * 节点交换存在2种情况：
     *      1. 相邻节点交换，则交换的即是存在降序的较大值和较小值
     *          比如：12345 -> 21345 ，21存在降序，21被交换
     *      2. 不相邻节点交换，则交换的即是第一次降序的较大值和第二次降序的较小值
     *          比如：12345 -> 32145 ，32存在降序，21存在降序，31被交换
     * 因此将每次降序的两个值都加入result，最后取首尾即是被交换的两个值
     */
    public void recoverTree(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        if (root != null) {
            traversal(root, list);
        }
        TreeNode first = result.get(0);
        TreeNode second = result.get(result.size() - 1);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    ArrayList<TreeNode> result = new ArrayList<>();

    public void traversal(TreeNode root, LinkedList<TreeNode> list) {
        if (root.left != null) {
            traversal(root.left, list);
        }

        if (!list.isEmpty() && list.getLast().val > root.val) {
            result.add(list.getLast());
            result.add(root);
        } else {
            list.add(root);
        }
        if (root.right != null) {
            traversal(root.right, list);
        }
    }
}
