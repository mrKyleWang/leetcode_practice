package linkedlist;

/**
 * 删除链表中的节点（237）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月14日
 */
public class DeleteNodeInALinkedList {


	/*
		请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
		现有一个链表 -- head = [4,5,1,9]，它可以表示为:

		输入: head = [4,5,1,9], node = 5
		输出: [4,1,9]
		解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

	*/

	public static void main(String[] args) {
		ListNode head = new ListNode(4);
		ListNode second = new ListNode(5);
		ListNode third = new ListNode(1);
		ListNode forth = new ListNode(9);
		head.next = second;
		second.next = third;
		third.next = forth;
		printList(head);
		deleteNode(third);
		System.out.println();
		printList(head);
	}

	public static void deleteNode(ListNode node) {
		ListNode next = node.next;
		node.val = next.val;
		if (next.next != null) {
			node.next = next.next;
		} else {
			node.next = null;
		}
	}

	private static void printList(ListNode node) {
		while (node != null) {
			System.out.print(" " + node.val);
			node = node.next;
		}
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
