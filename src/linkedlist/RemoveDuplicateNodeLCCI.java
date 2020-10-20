package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

import java.util.HashSet;

/**
 * 面试题 02.01. 移除重复节点
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月26日
 */
public class RemoveDuplicateNodeLCCI {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 3, 2, 1};
        ListNode head = new ListNode(arr);
        ListNode node = removeDuplicateNodes(head);
        node.printList();
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = head;
        ListNode cur = pre.next;
        set.add(head.val);
        while (cur != null) {
            ListNode tempNext = cur.next;
            if (set.contains(cur.val)) {
                pre.next = cur.next;
                if (cur.next != null) {
                    cur.next = null;
                }
            } else {
                set.add(cur.val);
                pre = cur;
            }
            cur = tempNext;
        }
        return head;
    }
}
