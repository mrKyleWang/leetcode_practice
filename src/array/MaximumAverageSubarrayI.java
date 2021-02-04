package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 643. 子数组最大平均数 I
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月04日
 */
public class MaximumAverageSubarrayI {

    /*
        给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
     */

    @Test
    public void test() {
        int[] nums = {1, 12, -5, -6, 50, 3};
        Assert.assertEquals(12.75d, findMaxAverage(nums, 4), 0.01);
    }

    public double findMaxAverage(int[] nums, int k) {
        double windowTotal = 0;
        double maxTotal = 0;
        for (int i = 0; i < nums.length; i++) {
            windowTotal += nums[i];
            if (i < k) {
                maxTotal = windowTotal;
            } else {
                windowTotal -= nums[i - k];
                if (windowTotal > maxTotal) {
                    maxTotal = windowTotal;
                }
            }
        }
        return maxTotal / k;
    }
}
