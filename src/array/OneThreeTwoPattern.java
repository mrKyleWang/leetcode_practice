package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 456. 132模式
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月24日
 */
public class OneThreeTwoPattern {

    /*
        给你一个整数数组 nums ，数组中共有 n 个整数。
        132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
        如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。

        进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？

        示例 1：
            输入：nums = [1,2,3,4]
            输出：false
            解释：序列中不存在 132 模式的子序列。
        示例 2：
            输入：nums = [3,1,4,2]
            输出：true
            解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
        示例 3：
            输入：nums = [-1,3,2,0]
            输出：true
            解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
        提示：
            n == nums.length
            1 <= n <= 104
            -109 <= nums[i] <= 109
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        Assert.assertEquals(false, find132pattern(nums));
    }

    @Test
    public void test2() {
        int[] nums = {3, 1, 4, 2};
        Assert.assertEquals(true, find132pattern(nums));
    }

    @Test
    public void test3() {
        int[] nums = {-1, 3, 2, 0};
        Assert.assertEquals(true, find132pattern(nums));
    }

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int maxK = Integer.MIN_VALUE;
        stack.push(nums[n - 1]);
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                maxK = stack.pop();
            }

            stack.push(nums[i]);
        }
        return false;
    }

}
