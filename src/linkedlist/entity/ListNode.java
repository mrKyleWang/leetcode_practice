package linkedlist.entity;

/**
 * 链表节点对象
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月14日
 */
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public static void printList(ListNode node) {
		while (node != null) {
			System.out.print(node.val + " ");
			node = node.next;
		}
		System.out.println();
	}
}