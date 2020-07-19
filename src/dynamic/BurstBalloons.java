package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 312. 戳气球
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月19日
 */
public class BurstBalloons {

    /*
    有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
    现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
    求所能获得硬币的最大数量。

    说明:
        你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
        0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
    示例:
        输入: [3,1,5,8]
        输出: 167
        解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     */


    /*
        nums: [3,1,5,8]
               0     3
        dp   0 1 2 3 4 5
     */

    @Test
    public void test() {
        int[] nums = {3, 1, 5, 8};
        Assert.assertEquals(167, maxCoins(nums));
    }

    /**
     * 动态规划，使用dp[i][j] 保存 (i,j)区间内能得到的最多硬币数
     * 要确定(i,j)的最优解，倒推，则需要确定最后要戳破i和j时，中间剩的气球k，
     * 而要戳破k，则先要确定(i,k)和(k,j)的最优解
     * 扩充数组，为了模拟nums[-1]和nums[n]
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        newNums[0] = newNums[n + 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, n);
        int[][] dp = new int[n + 2][n + 2];

        for (int len = 3; len <= n + 2; len++) {
            for (int i = 0, j = i + len - 1; j < n + 2; i++, j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[j] * newNums[i] * newNums[k]);
                }
            }
        }
        return dp[0][n + 1];
    }
}
