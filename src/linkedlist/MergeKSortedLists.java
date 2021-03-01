package linkedlist;

import linkedlist.entity.ListNode;
import org.junit.Test;

/**
 * 23. 合并K个升序链表
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月01日
 */
public class MergeKSortedLists {

    /*
        给你一个链表数组，每个链表都已经按升序排列。
        请你将所有链表合并到一个升序链表中，返回合并后的链表。

        示例 1：
            输入：lists = [[1,4,5],[1,3,4],[2,6]]
            输出：[1,1,2,3,4,4,5,6]
            解释：链表数组如下：
            [
              1->4->5,
              1->3->4,
              2->6
            ]
            将它们合并到一个有序链表中得到。
            1->1->2->3->4->4->5->6
     */

    @Test
    public void test() {
        ListNode[] lists = new ListNode[]{new ListNode(1, 4, 5), new ListNode(1, 3, 4), new ListNode(2, 6)};
        mergeKLists(lists).printList();
    }

    /**
     * 分治，将lists数组一直平分，最后合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return divideMerge(lists, 0, lists.length - 1);
    }

    private ListNode divideMerge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        return mergeTwo(divideMerge(lists, start, mid), divideMerge(lists, mid + 1, end));
    }

    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
