package tree;

import org.junit.Test;
import tree.entity.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月24日
 */
public class FindModeInBinarySearchTree {

    /*
        给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
        假定 BST 有如下定义：
            结点左子树中所含结点的值小于等于当前结点的值
            结点右子树中所含结点的值大于等于当前结点的值
            左子树和右子树都是二叉搜索树
        例如：
            给定 BST [1,null,2,2],

               1
                \
                 2
                /
               2
            返回[2].

        提示：如果众数超过1个，不需考虑输出顺序
        进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("1,null,2,2");
        System.out.println(Arrays.toString(findMode(root)));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("1,1");
        System.out.println(Arrays.toString(findMode(root)));
    }

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        pre = root.val;
        maxVal = root.val + 1;
        traversal(root);
        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    int pre;
    int count = 0;
    int maxVal;
    int maxCount = -1;
    List<Integer> result = new ArrayList<>();

    public void traversal(TreeNode root) {
        if (root.left != null) {
            traversal(root.left);
        }
        int cur = root.val;
        if (cur == pre) {
            count++;
        } else {
            count = 1;
        }
        if (count > maxCount) {
            if (cur != maxVal) {
                result.clear();
                maxVal = cur;
                result.add(cur);
            }
            maxCount = count;
        } else if (count == maxCount) {
            result.add(cur);
        }
        pre = cur;
        if (root.right != null) {
            traversal(root.right);
        }
    }
}
