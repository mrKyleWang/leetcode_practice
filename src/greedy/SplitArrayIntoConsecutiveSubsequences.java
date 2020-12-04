package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 659. 分割数组为连续子序列
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月04日
 */
public class SplitArrayIntoConsecutiveSubsequences {

    /*
        给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
        如果可以完成上述分割，则返回 true ；否则，返回 false 。

        示例 1：
            输入: [1,2,3,3,4,5]
            输出: True
            解释:
            你可以分割出这样两个连续子序列 :
            1, 2, 3
            3, 4, 5

        示例 2：
            输入: [1,2,3,3,4,4,5,5]
            输出: True
            解释:
            你可以分割出这样两个连续子序列 :
            1, 2, 3, 4, 5
            3, 4, 5

        示例 3：
            输入: [1,2,3,4,4,5]
            输出: False

        提示：输入的数组长度范围为 [1, 10000]
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 3, 4, 5};
        Assert.assertEquals(true, isPossible(nums));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3, 3, 4, 4, 5, 5};
        Assert.assertEquals(true, isPossible(nums));
    }

    @Test
    public void test3() {
        int[] nums = {1, 2, 3, 4, 4, 5};
        Assert.assertEquals(false, isPossible(nums));
    }

    /**
     * dp1为以上一个数结尾的长度为1的子序列，dp3为以上一个数结尾的长度为2的子序列，dp3为以上一个数结尾的长度>=3的子序列
     * count 为当前这个数的个数，需要先填补上一个数dp1以及dp2的空缺，然后填补dp3，剩下的重新开启子序列
     * 最后dp1和dp2的数量如果大于0，即有未填补完的
     */
    public boolean isPossible(int[] nums) {
        int dp1 = 0;
        int dp2 = 0;
        int dp3 = 0;
        int pre = -1;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            int count = 1;
            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                count++;
                i++;
            }
            if (pre >= 0 && cur > nums[pre] + 1) {
                if (dp1 + dp2 > 0) {
                    return false;
                } else {
                    dp1 = count;
                    dp2 = 0;
                    dp3 = 0;
                }
            } else {
                int left = count - dp1 - dp2;
                if (left < 0) {
                    return false;
                }
                int keep = Math.min(dp3, left);
                dp3 = dp2 + keep;
                dp2 = dp1;
                dp1 = left - keep;
            }
            pre = i;
        }
        return dp1 == 0 && dp2 == 0;
    }
}
