package linkedlist;

/**
 * 删除链表的倒数第N个节点（19）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月16日
 */
public class RemoveNthNodeFromEndOfList {

	/*
	 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	 * 
	 * 示例：
	 * 
	 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
	 * 
	 * 当删除了倒数第二个节点后，链表变为 1->2->3->5. 说明：
	 * 
	 * 给定的 n 保证是有效的。
	 */

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(3);
		ListNode forth = new ListNode(4);
		ListNode fifth = new ListNode(5);
		head.next = second;
		second.next = third;
		third.next = forth;
		forth.next = fifth;
		ListNode.printList(head);
		ListNode.printList(removeNthFromEnd(head, 1));
	}

	public static ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || head.next == null) {
			return null;
		}
		int length = 0;
		ListNode current = head;
		while (current != null) {
			length++;
			current = current.next;
		}
		System.out.println(length);

		ListNode pre = head;
		if (n == length) {
			head = head.next;
		} else {
			for (int i = 1; i < length - n; i++) {
				pre = pre.next;
			}
			System.out.println(pre.val);
			if (pre.next.next == null) {
				pre.next = null;
			} else {
				pre.next = pre.next.next;
			}
		}
		return head;
	}
}
