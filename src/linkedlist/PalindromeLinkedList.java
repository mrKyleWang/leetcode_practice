package linkedlist;

import linkedlist.entity.ListNode;

/**
 * 回文链表（234）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月17日
 */
public class PalindromeLinkedList {

	/*
	 * 请判断一个链表是否为回文链表。
	 * 
	 * 示例 1:
	 * 
	 * 输入: 1->2 输出: false 示例 2:
	 * 
	 * 输入: 1->2->2->1 输出: true
	 */

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(3);
		ListNode forth = new ListNode(2);
		ListNode fifth = new ListNode(1);
		 head.next = second;
		 second.next = third;
		 third.next = forth;
		 forth.next = fifth;
		ListNode.printList(head);
		System.out.println(isPalindrome(head));
	}

	public static boolean isPalindrome(ListNode head) {
		// 获取长度
		if (head == null || head.next == null) {
			return true;
		}
		int length = 0;
		ListNode temp = head;
		while (temp != null) {
			length++;
			temp = temp.next;
		}

		// 前半部分反序
		ListNode seq1 = head;
		ListNode seq1Next = head.next;
		ListNode seq2;
		ListNode pre = null;
		ListNode current = head;
		ListNode next = head.next;
		for (int i = 0; i < length / 2; i++) {
			seq1 = current;
			seq1Next = next;
			current.next = pre;
			pre = seq1;
			current = next;
			next = next.next;
		}
		if (length % 2 == 0) {
			// 偶数个
			seq2 = seq1Next;
		} else {
			// 奇数个
			seq2 = seq1Next.next;
		}
		ListNode.printList(seq1);
		ListNode.printList(seq2);
		do {
			if (seq1.val != seq2.val) {
				return false;
			}
			seq1 = seq1.next;
			seq2 = seq2.next;
		} while (seq1 != null && seq2 != null);
		return true;
	}

}
