package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 83. 删除排序链表中的重复元素
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月26日
 */
public class RemoveDuplicatesFromSortedList {

    /*
        存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
        返回同样按升序排列的结果链表。
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 1, 2);
        deleteDuplicates(head).printList();
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            while (next != null && next.val == cur.val) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return head;
    }
}
