package tree;

import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 108. 将有序数组转换为二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月03日
 */
public class ConvertSortedArrayToBinarySearchTree {

    /*
        将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
        本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

        示例:

        给定有序数组: [-10,-3,0,5,9],
        一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

              0
             / \
           -3   9
           /   /
         -10  5
     */

    @Test
    public void test() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        TreeNode treeNode = sortedArrayToBST(nums);
        treeNode.print();
    }


    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (left <= right) {
            return buildNode(nums, left, right);
        } else {
            return null;
        }
    }

    /**
     * 取index区间内的mid的值作为root，左侧递归取mid作为这层root的左子节点，右侧作为右子节点
     */
    private TreeNode buildNode(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        if (left < mid) {
            node.left = buildNode(nums, left, mid - 1);
        }
        if (mid < right) {
            node.right = buildNode(nums, mid + 1, right);
        }
        return node;
    }
}
