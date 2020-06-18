package tree.entity;

import java.util.LinkedList;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
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
}