package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 24. 两两交换链表中的节点
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月15日
 */
public class SwapNodesInPairs {

    /*
        定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

        你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

        示例 1：
            1 -> 2 -> 3 -> 4
                   ↓
            2 -> 1 -> 4 -> 3

            输入：head = [1,2,3,4]
            输出：[2,1,4,3]
        示例 2：

            输入：head = []
            输出：[]
        示例 3：
            输入：head = [1]
            输出：[1]
     */

    @Test
    public void test() {
        ListNode head = new ListNode(1, 2, 3, 4, 5, 6);
        swapPairs(head).printList();
    }

    public ListNode swapPairs(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
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

        }
        return head;
    }
}
