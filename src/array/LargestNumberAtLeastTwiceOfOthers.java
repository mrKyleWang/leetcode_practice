package array;

import org.junit.Test;

/**
 * 747. 至少是其他数字两倍的最大数
 * @author KyleWang
 * @version 1.0
 * @date 2020/02/24
 */
public class LargestNumberAtLeastTwiceOfOthers {

    /*
        在一个给定的数组nums中，总是存在一个最大元素 。
        查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
        如果是，则返回最大元素的索引，否则返回-1。

        示例 1:
            输入: nums = [3, 6, 1, 0]
            输出: 1
            解释: 6是最大的整数, 对于数组中的其他整数,6大于数组中其他元素的两倍。6的索引是1, 所以我们返回1.
        示例 2:
            输入: nums = [1, 2, 3, 4]
            输出: -1
            解释: 4没有超过3的两倍大, 所以我们返回 -1.

        nums 的长度范围在[1, 50].
        每个 nums[i] 的整数范围在 [0, 100].
     */

    @Test
    public void test() {
        System.out.println(dominantIndex(new int[]{3, 6, 1, 0}));
        System.out.println(dominantIndex(new int[]{1, 2, 3, 4}));
        System.out.println(dominantIndex(new int[]{1, 0}));
        System.out.println(dominantIndex(new int[]{0}));
    }

    public int dominantIndex(int[] nums) {
        int maxIndex = -1;
        int secondIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (maxIndex < 0 || nums[i] > nums[maxIndex]) {
                secondIndex = maxIndex;
                maxIndex = i;
            } else if (secondIndex < 0 || nums[i] > nums[secondIndex]) {
                secondIndex = i;
            }
        }

        if (nums.length <= 1 || nums[maxIndex] >= nums[secondIndex] * 2) {
            return maxIndex;
        } else {
            return -1;
        }
    }
}
