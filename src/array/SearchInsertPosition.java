package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 35. 搜索插入位置
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月17日
 */
public class SearchInsertPosition {

    /*
        35. 搜索插入位置
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

        你可以假设数组中无重复元素。

        示例 1:
            输入: [1,3,5,6], 5
            输出: 2
        示例 2:
            输入: [1,3,5,6], 2
            输出: 1
        示例 3:
            输入: [1,3,5,6], 7
            输出: 4
        示例 4:
            输入: [1,3,5,6], 0
            输出: 0
     */

    @Test
    public void test() {
        int[] nums = {1, 3, 5, 6};
        Assert.assertEquals(2, searchInsert(nums, 5));
    }

    @Test
    public void test2() {
        int[] nums = {1, 3, 5, 6};
        Assert.assertEquals(1, searchInsert(nums, 2));
    }

    @Test
    public void test3() {
        int[] nums = {1, 3, 5, 6};
        Assert.assertEquals(4, searchInsert(nums, 7));
    }

    @Test
    public void test4() {
        int[] nums = {1, 3, 5, 6};
        Assert.assertEquals(0, searchInsert(nums, 0));
    }

    @Test
    public void test5() {
        int[] nums = {1, 3};
        Assert.assertEquals(1, searchInsert(nums, 2));
    }

    public int searchInsert(int[] nums, int target) {
        // 二分查找
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return target > nums[start] ? start + 1 : start;
    }
}
