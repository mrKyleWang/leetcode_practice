package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 147. 对链表进行插入排序
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月20日
 */
public class InsertionSortList {

    /*
        插入排序算法：

        插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
        每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
        重复直到所有输入数据插入完为止。

        示例 1：
            输入: 4->2->1->3
            输出: 1->2->3->4
        示例 2：
            输入: -1->5->3->4->0
            输出: -1->0->3->4->5
     */

    @Test
    public void test() {
        ListNode head = new ListNode(4, 2, 1, 3);
        insertionSortList(head).printList();
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(-1, 5, 3, 4, 0);
        insertionSortList(head).printList();
    }


    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
            } else {
                ListNode pre = dummy;
                while (head.next.val > pre.next.val) {
                    pre = pre.next;
                }
                ListNode cur = head.next;
                head.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
        }
        return dummy.next;
    }
}
