package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 86. 分隔链表
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月04日
 */
public class PartitionList {


    /*
        给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
        你应当保留两个分区中每个节点的初始相对位置。
         
        示例：
            输入：head = 1->4->3->2->5->2, x = 3
            输出：1->2->2->4->3->5
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 4, 3, 2, 5, 2);
        partition(head, 3).printList();
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(1, 4, 3, 2, 5, 2);
        partition(head, 6).printList();
    }

    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode(0);
        ListNode left = leftHead;
        ListNode rightHead = new ListNode(0);
        ListNode right = rightHead;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                left.next = node;
                left = left.next;
            } else {
                right.next = node;
                right = right.next;
            }
            node = node.next;
        }
        left.next = rightHead.next;
        right.next = null;
        return leftHead.next;
    }
}
