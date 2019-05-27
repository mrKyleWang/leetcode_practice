package linkedlist;

import linkedlist.entity.ListNode;

/**
 * 反转链表（206）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月17日
 */
public class ReverseLinkedList {

	/*
	 * 反转一个单链表。
	 * 
	 * 示例:
	 * 
	 * 输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL
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
		ListNode.printList(reverseList(head));
	}

	public static ListNode reverseList(ListNode head) {
		return reverse(null, head);
	}

	private static ListNode reverse(ListNode preNode, ListNode node) {
		if (node == null) {
			return null;
		}
		if (node.next == null) {
			node.next = preNode;
			return node;
		} else {
			ListNode nextNode = node.next;
			node.next = preNode;
			return reverse(node, nextNode);
		}
	}
}
