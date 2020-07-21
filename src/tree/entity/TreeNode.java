package tree.entity;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int i, TreeNode leftNode, TreeNode rightNode) {
        val = i;
        left = leftNode;
        right = rightNode;
    }

    /**
     * 层次遍历输出
     */
    public void print() {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        while (queue.size() > 0) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }

    /**
     * 层次遍历格式化
     */
    public String serialize() {
        StringBuilder builder = new StringBuilder();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(this);
        int n = 0;
        while (queue.size() > n) {
            TreeNode node = queue.poll();
            if (node == null) {
                builder.append("null,");
                n--;
            } else {
                builder.append(node.val).append(",");
                queue.offer(node.left);
                if (node.left == null) {
                    n++;
                }
                queue.offer(node.right);
                if (node.right == null) {
                    n++;
                }
            }
        }
        builder.delete(builder.length() - 1, builder.length());
        return builder.toString();
    }

    public static TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] split = data.split(",");
        Queue<TreeNode> pre = new LinkedList<>();
        Queue<TreeNode> cur = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(split[0]));
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
            TreeNode node = null;
            if (!"null".equals(split[i])) {
                node = new TreeNode(Integer.parseInt(split[i]));
                cur.add(node);
            }
            TreeNode parent = pre.peek();
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