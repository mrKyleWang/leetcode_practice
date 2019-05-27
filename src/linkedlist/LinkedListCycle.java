package linkedlist;

import linkedlist.entity.ListNode;

import java.util.ArrayList;
import java.util.List;

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

	public static boolean hasCycle(ListNode head) {
		List<ListNode> list = new ArrayList<>();
		while (head != null && head.next != null) {
			if (list.contains(head)) {
				return true;
			} else {
				list.add(head);
				head = head.next;
			}
		}
		return false;
	}
}
