package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 41. 缺失的第一个正数
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月27日
 */
public class FirstMissingPositive {

    /*
        给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
        示例 1:

        输入: [1,2,0]
        输出: 3
        示例 2:

        输入: [3,4,-1,1]
        输出: 2
        示例 3:

        输入: [7,8,9,11,12]
        输出: 1

        提示：
            你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 0};
        Assert.assertEquals(3, firstMissingPositive(nums));
    }

    @Test
    public void test2() {
        int[] nums = {3, 4, -1, 1};
        Assert.assertEquals(2, firstMissingPositive(nums));
    }

    @Test
    public void test3() {
        int[] nums = {7, 8, 9, 11, 12};
        Assert.assertEquals(1, firstMissingPositive(nums));
    }

    @Test
    public void test4() {
        int[] nums = {1, 1};
        Assert.assertEquals(2, firstMissingPositive(nums));
    }

    /**
     * 遍历一次数组把大于等于1的和小于数组大小的值放到原数组对应位置，
     * 然后再遍历一次数组查当前下标是否和值对应，如果不对应那这个下标就是答案，否则遍历完都没出现那么答案就是数组长度加1。
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 使用while循环判断交换值，因为num所对应的位置可能本身也是一个范围内的整数
            while (num > 0 && num <= n && i != num - 1 && nums[num - 1] != nums[i]) {
                swap(nums, num - 1, i);
                num = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
