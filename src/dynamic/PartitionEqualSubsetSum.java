package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 416. 分割等和子集
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月15日
 */
public class PartitionEqualSubsetSum {

    /*
        给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
        注意:
            每个数组中的元素不会超过 100
            数组的大小不会超过 200

        示例 1:
            输入: [1, 5, 11, 5]
            输出: true
            解释: 数组可以分割成 [1, 5, 5] 和 [11].

        示例 2:
            输入: [1, 2, 3, 5]
            输出: false
            解释: 数组不能分割成两个元素和相等的子集.
     */

    @Test
    public void test() {
        int[] nums = {1, 5, 11, 5};
        Assert.assertEquals(true, canPartition2(nums));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3, 5};
        Assert.assertEquals(false, canPartition2(nums));
    }

    /**
     * 动态规划：
     * dp[i][j] 表示：数组nums在 [0~i] 范围内，是否存在若干个数(可为0)的和等于 j
     */
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        boolean[][] dp = new boolean[n][target + 1];
        for (boolean[] booleans : dp) {
            Arrays.fill(booleans, false);
        }

        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = 1; j <= target; j++) {
                if (j >= num) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];
    }

    /**
     * 降低空间复杂度：合并为一维数组
     */
    public boolean canPartition2(int[] nums) {
        int n = nums.length;
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        Arrays.fill(dp, false);

        dp[nums[0]] = true;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }
        return dp[target];
    }
}
