package dynamic;

import org.junit.Assert;
import org.junit.Test;
import tree.entity.TreeNode;

/**
 * 337. 打家劫舍 III
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月05日
 */
public class HouseRobberIII {

    /*
        在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
        这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
        一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
        如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

        计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

        示例 1:
            输入: [3,2,3,null,3,null,1]

                 3
                / \
               2   3
                \   \
                 3   1
`
            输出: 7
            解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

        示例 2:
            输入: [3,4,5,1,3,null,1]

                 3
                / \
               4   5
              / \   \
             1   3   1

            输出: 9
            解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

     */

    @Test
    public void test() {
        TreeNode root = TreeNode.deserialize("3,2,3,null,3,null,1");
        Assert.assertEquals(7, rob(root));
    }

    @Test
    public void test2() {
        TreeNode root = TreeNode.deserialize("3,4,5,1,3,null,1");
        Assert.assertEquals(9, rob(root));
    }

    @Test
    public void test3() {
        TreeNode root = TreeNode.deserialize("4,1,null,2,null,3");
        Assert.assertEquals(7, rob(root));
    }

    /**
     * 动态规划：从叶子节点向根节点遍历，计算每个节点 偷/不偷 的最大金额，向上层返回
     * 状态转移方程：（）
     *  node[0] = val+node.left[1]+node.right[1]
     *  node[1] = max(node.left[0],node.left[1])+max(node.right[0],node.right[1])
     */
    public int rob(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int[] max = getMax(root);
        return Math.max(max[0], max[1]);
    }

    private int[] getMax(TreeNode node) {
        int robMax = node.val;
        int notRobMax = 0;
        if (node.left != null) {
            int[] leftMax = getMax(node.left);
            robMax += leftMax[1];
            notRobMax += Math.max(leftMax[0], leftMax[1]);
        }
        if (node.right != null) {
            int[] rightMax = getMax(node.right);
            robMax += rightMax[1];
            notRobMax += Math.max(rightMax[0], rightMax[1]);
        }
        return new int[]{robMax, notRobMax};
    }
}
