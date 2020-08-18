package tree;

import linkedlist.entity.ListNode;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 109. 有序链表转换二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月18日
 */
public class ConvertSortedListToBinarySearchTree {

    /*
        给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
        本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

        示例:
        给定的有序链表： [-10, -3, 0, 5, 9],
        一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

              0
             / \
           -3   9
           /   /
         -10  5
     */

    @Test
    public void test() {
        ListNode node = new ListNode(1, 2, 3, 4, 5, 6);
        TreeNode treeNode = sortedListToBST(node);
        System.out.println(treeNode.serialize());
    }

    /**
     * 快慢指针找到中点，分割左右区间，递归找到左右区间中点作为左右子节点，
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return buildNode(head, null);
    }

    private TreeNode buildNode(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode slow = head;
        int tailVal = tail != null ? tail.val : Integer.MAX_VALUE;
        if (head.next != null && head.next.val < tailVal) {
            ListNode quick = head;
            do {
                pre = slow;
                slow = slow.next;
                quick = quick.next.next;
            } while (quick != null && quick.next != null && quick.val < tailVal);
        }
        TreeNode node = new TreeNode(slow.val);
        if (pre != null) {
            node.left = buildNode(head, pre);
        }
        if (slow.val < tailVal && slow.next != null) {
            node.right = buildNode(slow.next, tail);
        }
        return node;
    }
}
