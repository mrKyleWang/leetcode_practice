package tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 96. 不同的二叉搜索树
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月15日
 */
public class UniqueBinarySearchTrees {

    /*
        给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

        示例:

        输入: 3
        输出: 5
        解释:
        给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

           1         3     3      2      1
            \       /     /      / \      \
             3     2     1      1   3      2
            /     /       \                 \
           2     1         2                 3
     */

    @Test
    public void test() {
        Assert.assertEquals(5, numTrees(3));
    }

    /**
     * 使用数组保存n个节点组成的不同二叉树数量
     * 遍历n，计算以n为root时，左右两侧的count相乘，最后累加即为结果
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        return numTrees(dp, n);
    }

    private int numTrees(int[] dp, int n) {
        int count = 0;
        if (n <= 1 || n == dp.length) {
            count = 1;
        } else if (dp[n] > 0) {
            count = dp[n];
        } else {
            for (int i = 1; i <= n; i++) {
                count += numTrees(dp, i - 1) * numTrees(dp, n - i);
            }
        }
        dp[n] = count;
        return count;
    }
}
