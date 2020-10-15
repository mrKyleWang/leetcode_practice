package tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月15日
 */
public class PopulatingNextRightPointersInEachNode {


    /*
        给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

        struct Node {
          int val;
          Node *left;
          Node *right;
          Node *next;
        }
        填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

        初始状态下，所有 next 指针都被设置为 NULL。

         

        示例：



        输入：
                   1
                  / \
                 2   3
                / \ / \
               4  5 6  7

        输出：
                1->N
               / \
              2-> 3->N
             / \ / \
            4 >5>6 >7->N

        解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
         

        提示：

        你只能使用常量级额外空间。
        使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     */

    @Test
    public void test() {
        Node root = Node.deserialize("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        Node node = connect(root);
        System.out.println();
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
            root.right.next = getNext(root.next);
        }
        connect(root.left);
        connect(root.right);
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

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
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
