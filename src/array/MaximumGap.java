package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 164. 最大间距
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月26日
 */
public class MaximumGap {

    /*
        给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
        如果数组元素个数小于 2，则返回 0。

        示例 1:
            输入: [3,6,9,1]
        输出: 3
            解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
        示例 2:
            输入: [10]
            输出: 0
            解释: 数组元素个数小于 2，因此返回 0。

        说明:
            你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
            请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
     */

    @Test
    public void test() {
        int[] nums = {3, 6, 9, 1};
        Assert.assertEquals(3, maximumGap(nums));
    }

    @Test
    public void test2() {
        int[] nums = {10};
        Assert.assertEquals(0, maximumGap(nums));
    }

    /**
     * 桶排序
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 每个桶的大小
        int d = (max - min) / (n - 1);
        if (d == 0) {
            d = 1;
        }
        int count = (max - min) / d + 1;
        int[][] buckets = new int[count][2];
        boolean[] check = new boolean[count];

        for (int num : nums) {
            int index = (num - min) / d;
            int[] bucket = buckets[index];
            if (!check[index]) {
                buckets[index] = new int[]{num, num};
                check[index] = true;
            } else {
                bucket[0] = Math.min(bucket[0], num);
                bucket[1] = Math.max(bucket[1], num);
            }
        }

        int result = 0;
        int index = 0;
        while (!check[index]) {
            index++;
        }
        int preMax = buckets[index][1];
        for (int i = index + 1; i < count; i++) {
            int[] bucket = buckets[i];
            if (check[i]) {
                result = Math.max(result, bucket[0] - preMax);
                preMax = bucket[1];
            }
        }
        return result;
    }

}
