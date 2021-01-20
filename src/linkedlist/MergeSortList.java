package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 对链表进行归并排序
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月20日
 */
public class MergeSortList {

    @Test
    public void test() {
        ListNode head = new ListNode(4, 2, 1, 3);
        mergeSortList(head).printList();
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(-1, 5, 3, 4, 0);
        mergeSortList(head).printList();
    }


    public ListNode mergeSortList(ListNode head) {
        return sort(head);
    }

    private ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode quick = head.next.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }

        ListNode left = head;
        ListNode right = slow.next;
        slow.next = null;
        left = sort(left);
        right = sort(right);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }
}
