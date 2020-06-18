package tree;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

import java.util.Stack;

/**
 * 1028. 从先序遍历还原二叉树
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月18日
 */
public class RecoverATreeFromPreorderTraversal {

    /*
        我们从二叉树的根节点 root 开始进行深度优先搜索。
        在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
        如果节点只有一个子节点，那么保证该子节点为左子节点。
        给出遍历输出 S，还原树并返回其根节点 root。
     */

    @Test
    public void test() {
        String s = "1-2--3--4-5--6--7";
        TreeNode node = recoverFromPreorder(s);
        Assert.assertEquals("1,2,5,3,4,6,7", node.serialize());
    }

    @Test
    public void test2() {
        String s = "1-2--3---4-5--6---7";
        TreeNode node = recoverFromPreorder(s);
        Assert.assertEquals("1,2,5,3,null,6,null,4,null,7", node.serialize());
    }

    @Test
    public void test3() {
        String s = "1";
        TreeNode node = recoverFromPreorder(s);
        Assert.assertEquals("1", node.serialize());
    }


    public TreeNode recoverFromPreorder(String S) {
        char[] chars = S.toCharArray();
        Stack<TreeNode> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int level = 0;
        boolean preIsNum = false;
        boolean isNum;
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || chars[i] == '-') {
                level++;
                isNum = false;
            } else {
                builder.append(chars[i]);
                isNum = true;
            }
            if (preIsNum && !isNum) {
                int num = Integer.parseInt(builder.toString());
                TreeNode node = new TreeNode(num);
                if (stack.size() == 0) {
                    stack.push(node);
                } else {
                    while (stack.size() > level) {
                        stack.pop();
                    }
                    TreeNode parent = stack.peek();
                    stack.push(node);
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }
                builder = new StringBuilder();
                level = 0;
            }
            preIsNum = isNum;
        }
        return stack.firstElement();
    }

}
