package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 978. 最长湍流子数组
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月08日
 */
public class LongestTurbulentSubarray {

    /*
        当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
        若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
        或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
        也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。

        返回 A 的最大湍流子数组的长度。

     */

    @Test
    public void test() {
        int[] arr = {9, 4, 2, 10, 7, 8, 8, 1, 9};
        Assert.assertEquals(5, maxTurbulenceSize(arr));
        Assert.assertEquals(5, maxTurbulenceSize2(arr));
    }

    /**
     * 动态规划
     * 优化前：使用dp[i][0] 和 dp[i][1]分别保存下标为i时，且为上升趋势或下降趋势的最大长度
     * 当arr[i]大于或等于arr[i-1]时，状态转移公式即：dp[i][0] = dp[i-1][1]+1，dp[i][1] = dp[i-1][0]+1
     * 优化后：由于只需要保存前一个状态，因此可以将二维数组缩减为一维
     */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[i - 1]) {
                dp[1] = dp[0] + 1;
                dp[0] = 1;
            } else if (arr[i] > arr[i - 1]) {
                dp[0] = dp[1] + 1;
                dp[1] = 1;
            } else {
                dp[0] = 1;
                dp[1] = 1;
            }
            int cur = Math.max(dp[0], dp[1]);
            max = Math.max(max, cur);
        }
        return max;
    }

    /**
     * 双指针
     */
    public int maxTurbulenceSize2(int[] arr) {
        int n = arr.length;
        int max = 1;
        int left = 0, right = 0;
        while (right < n - 1) {
            if (left == right) {
                if (arr[left] == arr[left + 1]) {
                    left++;
                }
                right++;
            } else {
                if (arr[right] > arr[right - 1] && arr[right] > arr[right + 1]) {
                    right++;
                } else if (arr[right] < arr[right - 1] && arr[right] < arr[right + 1]) {
                    right++;
                } else {
                    left = right;
                }
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
