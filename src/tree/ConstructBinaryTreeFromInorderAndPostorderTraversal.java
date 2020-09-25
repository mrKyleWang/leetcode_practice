package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月25日
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /*
        根据一棵树的中序遍历与后序遍历构造二叉树。

        注意:
        你可以假设树中没有重复的元素。

        例如，给出
            中序遍历 inorder = [9,3,15,20,7]
            后序遍历 postorder = [9,15,7,20,3]
            返回如下的二叉树：

                3
               / \
              9  20
                /  \
               15   7
     */

    @Test
    public void test() {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        System.out.println(buildTree(inorder, postorder).serialize());
    }

    @Test
    public void test2() {
        int[] inorder = {2, 1};
        int[] postorder = {2, 1};
        System.out.println(buildTree(inorder, postorder).serialize());
    }

    @Test
    public void test3() {
        int[] inorder = {1, 3, 2};
        int[] postorder = {3, 2, 1};
        System.out.println(buildTree(inorder, postorder).serialize());
    }


    /**
     * 对于后续postorder来说，最后一个数一定是根节点root
     * 而在中序inorder中，root左边是他的左子树，右边是他的右子树，分别又回到postorder中找到各自的根节点
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(map, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    private TreeNode build(Map<Integer, Integer> map, int[] postorder, int startI, int endI, int startP, int endP) {
        if (startI > endI) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[endP]);
        if (startI < endI) {
            Integer index = map.get(root.val);
            // 左子树的长度
            int leftLength = index - startI;
            int rightLength = endI - index;
            root.left = build(map, postorder, startI, startI + leftLength - 1, startP, startP + leftLength - 1);
            root.right = build(map, postorder, index + 1, endI, endP - rightLength, endP - 1);
        }
        return root;
    }
}
