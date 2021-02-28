package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 25. K 个一组翻转链表
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月28日
 */
public class ReverseNodesInKGroup {

    /*
        给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
        k 是一个正整数，它的值小于或等于链表的长度。
        如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

        进阶：
            你可以设计一个只使用常数额外空间的算法来解决此问题吗？
            你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 2, 3, 4, 5);
        head = reverseKGroup(head, 2);
        head.printList();
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null) {
            ListNode cur = pre.next;
            ListNode tail = cur;
            for (int i = 1; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            ListNode next = tail.next;
            reverse(cur, tail);
            pre.next = tail;
            cur.next = next;
            pre = cur;
        }
        return dummy.next;
    }

    private void reverse(ListNode start, ListNode end) {
        ListNode pre = null, cur = start;
        while (pre != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }


}
