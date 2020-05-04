package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月04日
 */
public class ThreeSumClosest {

    /*
    给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
    找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

    例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

    与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

     */

    @Test
    public void test() {
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int gap = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int curSum = nums[i] + nums[j] + nums[k];
                int curGap = Math.abs(curSum - target);
                if (curGap < gap) {
                    gap = curGap;
                    result = curSum;
                }

                if (curSum > target) {
                    k--;
                } else if (curSum < target) {
                    j++;
                } else {
                    return target;
                }
            }
        }
        return result;
    }
}
