package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 148. 排序链表
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月15日
 */
public class SortList {

    /*
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
        进阶：
            你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

        示例 1：
            输入：head = [4,2,1,3]
            输出：[1,2,3,4]
        示例 2：
            输入：head = [-1,5,3,4,0]
            输出：[-1,0,3,4,5]
        示例 3：
            输入：head = []
            输出：[]

        提示：
            链表中节点的数目在范围 [0, 5 * 104] 内
            -105 <= Node.val <= 105
     */

    @Test
    public void test() {
        ListNode head = new ListNode(4, 2, 1, 3);
        sortList(head).printList();
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(-1, 5, 3, 4, 0);
        sortList(head).printList();
    }

    @Test
    public void test3() {
        ListNode head = new ListNode();
        sortList(head).printList();
    }

    /**
     * 递归 + 归并排序：
     * 先使用快慢指针，找到中点，分成两个链表分别排序，最后合并
     * 使用DummyHead（new ListNode(0)）处理边界情况
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        ListNode cur = new ListNode(0);
        ListNode res = cur;
        while (left != null && right != null) {
            if (left.val > right.val) {
                cur.next = right;
                right = right.next;
            } else {
                cur.next = left;
                left = left.next;
            }
            cur = cur.next;
        }

        cur.next = left != null ? left : right;
        return res.next;
    }
}
