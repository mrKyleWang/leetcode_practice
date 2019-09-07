package linkedlist;

import linkedlist.entity.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表（141）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月24日
 */
public class LinkedListCycle {

    /*
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     *
     * 示例： 输入：head = [3,2,0,-4], pos = 1 输出：true 解释：链表中有一个环，其尾部连接到第二个节点。
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(1, 2, 3, 4);
        ListNode mid = new ListNode(5, 6, 7, 8);
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = mid;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = mid;
        System.out.println(hasCycle(head));
        System.out.println(hasCycle2(head));
    }

    /**
     * 解法1：Set判断
     */
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null && head.next != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    /**
     * 解法2：快慢指针，判断是否重合
     */
    public static boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (slow != null && quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
            if (slow == quick) {
                return true;
            }
        }
        return false;
    }
}
