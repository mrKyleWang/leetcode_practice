package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 143. 重排链表
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月20日
 */
public class ReorderList {

    /*
        给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
        将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

        你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

        示例 1:

        给定链表 1->2->3->4, 重新排列为 1->4->2->3.
        示例 2:

        给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 2, 3, 4);
        reorderList(head);
        head.printList();
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(1, 2, 3, 4, 5);
        reorderList(head);
        head.printList();
    }

    /**
     * 先通过数组保存后半部分，然后逐步将尾部插入到前半部分的间隙
     */
    public void reorderList(ListNode head) {
        if (head != null) {
            ListNode quick = head;
            ListNode mid = head;
            int n = 0;
            while (quick.next != null && quick.next.next != null) {
                quick = quick.next.next;
                mid = mid.next;
                n++;
            }
            ListNode[] temp = new ListNode[n];
            ListNode tail = quick.next == null ? mid : mid.next;
            mid = tail.next;
            for (int i = 0; i < n; i++) {
                temp[i] = mid;
                mid = mid.next;
            }
            for (int i = n - 1; i >= 0; i--) {
                ListNode node = temp[i];
                ListNode nextHead = head.next;
                head.next = node;
                node.next = nextHead;
                head = nextHead;
            }
            if (n > 0) {
                tail.next = null;
            }
        }
    }
}
