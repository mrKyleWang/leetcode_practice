package linkedlist;

import linkedlist.entity.ListNode;

/**
 * 2. 两数相加
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月05日
 */
public class AddTwoNumbers {

    /*
        给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
        如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

        您可以假设除了数字 0 之外，这两个数都不会以0开头。

        示例：
        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 0 -> 8
        原因：342 + 465 = 807
    */


    public static void main(String[] args) {
        ListNode head1 = new ListNode(5);
        ListNode head2 = new ListNode(5);
        addTwoNumbers(head1, head2).printList();
    }

    /**
     * 遍历，
     * O(n)
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;
            sum %= 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }

}