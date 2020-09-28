package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月28日
 */
public class PopulatingNextRightPointersInEachNodeII {

    /*
        给定一个二叉树
        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }
        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
        初始状态下，所有 next 指针都被设置为 NULL。

        例如：
            1->N
           / \
          2-> 3->N
         / \   \
        4-> 5-> 7->N

        进阶：
            你只能使用常量级额外空间。
            使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度
     */

    @Test
    public void test() {
        Node root = Node.deserialize("1,2,3,4,5,null,7");
        Node node = connect2(root);
        System.out.println();
    }

    /**
     * 层次遍历方法
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node pre = null;
        LinkedList<Node> queue = new LinkedList<>();
        LinkedList<Node> nextQueue = new LinkedList<>();
        queue.offer(root);
        while (queue.size() > 0) {
            Node node = queue.poll();
            if (pre != null) {
                pre.next = node;
            }
            pre = node;
            if (node.left != null) {
                nextQueue.offer(node.left);
            }
            if (node.right != null) {
                nextQueue.offer(node.right);
            }
            if (queue.size() == 0 && nextQueue.size() > 0) {
                pre = null;
                queue = nextQueue;
                nextQueue = new LinkedList<>();
            }
        }
        return root;
    }

    /**
     * 递归方法
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.left != null && root.right == null) {
            root.left.next = getNext(root.next);
        }
        if (root.right != null) {
            root.right.next = getNext(root.next);
        }
        connect2(root.left);
        connect2(root.right);
        return root;
    }

    private Node getNext(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return root.left;
        }
        if (root.right != null) {
            return root.right;
        }
        return getNext(root.next);
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }

        public static Node deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            Queue<Node> pre = new LinkedList<>();
            Queue<Node> cur = new LinkedList<>();
            Node root = new Node(Integer.parseInt(split[0]));
            pre.add(root);
            long index = 0;
            int preSize = 1;
            for (int i = 1; i < split.length; i++) {
                if (pre.isEmpty() || index / 2 >= preSize) {
                    index = 0;
                    pre = cur;
                    preSize = pre.size();
                    cur = new LinkedList<>();
                }
                Node node = null;
                if (!"null".equals(split[i])) {
                    node = new Node(Integer.parseInt(split[i]));
                    cur.add(node);
                }
                Node parent = pre.peek();
                if (index % 2 == 0) {
                    parent.left = node;
                } else {
                    parent.right = node;
                    pre.poll();
                }
                index++;
            }
            return root;
        }
    }
}
