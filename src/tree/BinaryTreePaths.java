package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月04日
 */
public class BinaryTreePaths {

    /*
        给定一个二叉树，返回所有从根节点到叶子节点的路径。
        说明: 叶子节点是指没有子节点的节点。

        示例:
        输入:

               1
             /   \
            2     3
             \
              5

        输出: ["1->2->5", "1->3"]
        解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,2,3,null,5");
        List<String> paths = binaryTreePaths(null);
        System.out.println(paths);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<Integer> path = new ArrayList<>();
        return binaryTreePaths(root, path);
    }


    public List<String> binaryTreePaths(TreeNode node, List<Integer> path) {
        if (node == null) {
            return Collections.emptyList();
        }

        if (node.left == null && node.right == null) {
            String pathStr;
            if (path.size() > 0) {
                StringBuilder builder = new StringBuilder();
                for (Integer i : path) {
                    builder.append(i);
                    builder.append("->");
                }
                builder.append(node.val);
                pathStr = builder.toString();
            } else {
                pathStr = node.val + "";
            }
            return Collections.singletonList(pathStr);
        }
        List<Integer> newPath = new ArrayList<>(path);
        newPath.add(node.val);
        List<String> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(binaryTreePaths(node.left, newPath));
        }
        if (node.right != null) {
            list.addAll(binaryTreePaths(node.right, newPath));
        }
        return list;
    }
}
