package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 327. 区间和的个数
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月10日
 */
public class CountOfRangeSum {

    /*
        给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
        区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

        说明:
            最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。

        示例:
            输入: nums = [-2,5,-1], lower = -2, upper = 2,
            输出: 3
            解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
     */

    @Test
    public void test() {
        int[] nums = {-2, 5, -1};
        int lower = -2;
        int upper = 2;
        Assert.assertEquals(3, countRangeSum(nums, lower, upper));
    }

    /**
     * 类暴力求法，通过dp[]保存临时结果
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int count = 0;
        int n = nums.length;
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
            if (dp[i] >= lower && dp[i] <= upper) {
                count++;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i] += dp[j];
                if (dp[i] >= lower && dp[i] <= upper) {
                    count++;
                }
            }
        }
        return count;
    }
}
