package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 330. 按要求补齐数组
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月29日
 */
public class PatchingArray {

    /*
        给定一个已排序的正整数数组 nums，和一个正整数 n 。
        从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
        请输出满足上述要求的最少需要补充的数字个数。

        示例 1:
            输入: nums = [1,3], n = 6
            输出: 1
            解释:
            根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
            现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
            其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
            所以我们最少需要添加一个数字。
        示例 2:
            输入: nums = [1,5,10], n = 20
            输出: 2
            解释: 我们需要添加 [2, 4]。
        示例 3:
            输入: nums = [1,2,2], n = 5
            输出: 0
     */

    @Test
    public void test() {
        int[] nums = {1, 3};
        Assert.assertEquals(1, minPatches(nums, 6));
    }

    @Test
    public void test2() {
        int[] nums = {1, 5, 10};
        Assert.assertEquals(2, minPatches(nums, 20));
    }

    @Test
    public void test3() {
        int[] nums = {1, 2, 2};
        Assert.assertEquals(0, minPatches(nums, 5));
    }

    @Test
    public void test4() {
        int[] nums = {1,2,31,33};
        Assert.assertEquals(28, minPatches(nums, 2147483647));
    }


    /**
     * https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-tan-xin-suan-fa-b4bwr/
     */
    public int minPatches(int[] nums, int n) {
        int count = 0;
        long tail = 0;
        int index = 0;
        while (tail < n) {
            if (index < nums.length && tail >= nums[index] - 1) {
                tail += nums[index];
                index++;
            } else {
                count++;
                tail = 2 * tail + 1;
            }
        }
        return count;
    }
}
