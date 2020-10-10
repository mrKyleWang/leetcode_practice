package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

import java.util.HashSet;

/**
 * 142. 环形链表 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月10日
 */
public class LinkedListCycleII {

    /*
        给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
        为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
        说明：不允许修改给定的链表。

        示例 1：
            输入：head = [3,2,0,-4], pos = 1
            输出：tail connects to node index 1
            解释：链表中有一个环，其尾部连接到第二个节点。

            3 -> 2 -> 0 -> -4
                 ↑          |
                 └----------┘

        示例 2：
            输入：head = [1,2], pos = 0
            输出：tail connects to node index 0
            解释：链表中有一个环，其尾部连接到第一个节点。

            1 -> 2
            ↑    |
            └----┘

        示例 3：
            输入：head = [1], pos = -1
            输出：no cycle
            解释：链表中没有环。

        进阶：
        你是否可以不用额外空间解决此题？
     */


    @Test
    public void test() {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(detectCycle(head).val);
    }

    @Test
    public void test2() {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        node1.next = head;
        System.out.println(detectCycle(head).val);
    }

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null && head.next != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
                head = head.next;
            }
        }
        return null;
    }
}
