package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月14日
 */
public class LongestConsecutiveSequence {

    /*
        给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
        进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？


        示例 1：
            输入：nums = [100,4,200,1,3,2]
            输出：4
            解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
        示例 2：
            输入：nums = [0,3,7,2,5,8,4,6,0,1]
            输出：9

        提示：
            0 <= nums.length <= 104
            -109 <= nums[i] <= 109
     */


    @Test
    public void test() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        Assert.assertEquals(4, longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int pos = 1;
                while (set.contains(num + pos)) {
                    pos++;
                }
                res = Math.max(res, pos);
            }
        }
        return res;
    }
}
