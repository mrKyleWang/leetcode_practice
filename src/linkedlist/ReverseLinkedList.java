package linkedlist;

import linkedlist.entity.ListNode;

/**
 * 206. 反转链表
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月17日
 */
public class ReverseLinkedList {

    /*
     * 反转一个单链表。
     *
     * 示例:
     *
     * 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(1, 2, 3, 4, 5);
        head.printList();
//        ListNode.printList(reverseList(head));
        reverseList2(head).printList();
    }

    /**
     * 解法1：递归
     */
    public static ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private static ListNode reverse(ListNode preNode, ListNode node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            node.next = preNode;
            return node;
        } else {
            ListNode nextNode = node.next;
            node.next = preNode;
            return reverse(node, nextNode);
        }
    }

    /**
     * 解法2：遍历
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode current = head;
        ListNode pre = null;
        while (current != null) {
            ListNode temp = current.next;
            current.next = pre;
            pre = current;
            current = temp;
        }
        return pre;
    }

}
