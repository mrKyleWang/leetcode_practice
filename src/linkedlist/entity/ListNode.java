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

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int... arr) {
        ListNode node = this;
        node.val = arr[0];
        for (int i = 1; i < arr.length; i++) {
            node.next = new ListNode();
            node = node.next;
            node.val = arr[i];
        }
    }

    @Override
    public String toString() {
        return val + "";
    }

    public void printList() {
        ListNode node = this;
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }
}