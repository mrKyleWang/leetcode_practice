package dynamic;

/**
 * 152. 乘积最大子序列
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/20
 */
public class MaxProduct {

    /*
        给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。

        示例 1:
        输入: [2,3,-2,4]
        输出: 6
        解释: 子数组 [2,3] 有最大乘积 6。

        示例 2:
        输入: [-2,0,-1]
        输出: 0
        解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     */

    public static void main(String[] args) {
        int[] nums = {-2, -3, -4};
        System.out.println(new MaxProduct().maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int result = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int preMax = curMax;
            int preMin = curMin;
            curMax = max(preMax * cur, preMin * cur, cur);
            curMin = min(preMax * cur, preMin * cur, cur);
            if (curMax > result) {
                result = curMax;
            }
        }
        return result;
    }

    private int max(int... nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = max > nums[i] ? max : nums[i];
        }
        return max;
    }

    private int min(int... nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min = min < nums[i] ? min : nums[i];
        }
        return min;
    }
}
