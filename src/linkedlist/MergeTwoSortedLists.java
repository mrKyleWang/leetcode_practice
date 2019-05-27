package linkedlist;

import linkedlist.entity.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 合并两个有序链表（21）
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月17日
 */
public class MergeTwoSortedLists {

	/*
	 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
	 * 
	 * 示例：
	 * 
	 * 输入：1->2->4, 1->3->4 输出：1->1->2->3->4->4
	 */

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode second = new ListNode(2);
		ListNode third = new ListNode(4);
		ListNode head2 = new ListNode(1);
		ListNode second2 = new ListNode(3);
		ListNode third2 = new ListNode(4);
		head.next = second;
		second.next = third;
		head2.next = second2;
		second2.next = third2;
		ListNode.printList(mergeTwoLists(head, head2));
	}

	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		List<ListNode> list = new ArrayList<>();
		while (l1 != null) {
			list.add(l1);
			l1 = l1.next;
		}
		while (l2 != null) {
			list.add(l2);
			l2 = l2.next;
		}
		list.sort(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		});
		if (list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				list.get(i).next = list.get(i + 1);
			}
			return list.get(0);
		}
		return null;
	}
}
