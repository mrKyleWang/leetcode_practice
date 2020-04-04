package array;

import org.junit.Test;

/**
 * 209. 长度最小的子数组
 * @author KyleWang
 * @version 1.0
 * @date 2020年04月04日
 */
public class MinimumSizeSubarraySum {

    /*
        给定一个含有n个正整数的数组和一个正整数s，找出该数组中满足其和≥s的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回0。

        示例: 
            输入: s = 7, nums = [2,3,1,2,4,3]
        输出: 2
            解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
     */


    @Test
    public void test() {
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLen(7, nums1));

        int[] nums2 = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        System.out.println(minSubArrayLen(2131, nums2));
    }


    /**
     * 双指针（head,i）形成滑动窗口
     */
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int head = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            sum += cur;

            if (sum >= s) {
                if (cur > nums[head]) {
                    while (sum - nums[head] >= s) {
                        sum -= nums[head];
                        head++;
                    }
                }
                min = Math.min(min, (i - head + 1));
            }
        }
        return sum >= s ? min : 0;
    }
}
