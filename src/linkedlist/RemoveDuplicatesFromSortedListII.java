package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 82. 删除排序链表中的重复元素 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月26日
 */
public class RemoveDuplicatesFromSortedListII {

    /*
        存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
        返回同样按升序排列的结果链表。
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 1, 2);
        deleteDuplicates(head).printList();
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        ListNode cur = head;
        pre.next = head;
        while (cur != null) {
            ListNode next = cur.next;
            if (next != null && next.val == cur.val) {
                while (next != null && next.val == cur.val) {
                    next = next.next;
                }
                pre.next = next;
            } else {
                pre = cur;
            }
            cur = next;
        }
        return dummy.next;
    }
}
