package linkedlist;

import linkedlist.entity.ListNode;

/**
 * 24. 两两交换链表中的节点
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月06日
 */
public class SwapPairs {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, 2, 3, 4, 5, 6);
        ListNode.printList(swapPairs(head));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            if (cur.next != null) {
                ListNode next = cur.next.next;
                cur.next.next = cur;
                if (pre != null) {
                    pre.next = cur.next;
                } else {
                    head = cur.next;
                }
                cur.next = next;
                pre = cur;
                cur = next;
            } else {
                break;
            }
        }
        return head;
    }
}
