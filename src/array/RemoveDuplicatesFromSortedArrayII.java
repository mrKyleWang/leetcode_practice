package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 80. 删除有序数组中的重复项 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月06日
 */
public class RemoveDuplicatesFromSortedArrayII {

    /*
        给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
        不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

        示例 1：
            输入：nums = [1,1,1,2,2,3]
            输出：5, nums = [1,1,2,2,3]
            解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
        示例 2：
            输入：nums = [0,0,1,1,1,1,2,3,3]
            输出：7, nums = [0,0,1,1,2,3,3]
            解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。

        提示：
            1 <= nums.length <= 3 * 104
            -104 <= nums[i] <= 104
            nums 已按升序排列
     */

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Assert.assertEquals(5, removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void test2() {
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        Assert.assertEquals(7, removeDuplicates(nums));
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (i >= 2 && nums[i] == nums[i - pos - 2]) {
                pos++;
            } else {
                nums[i - pos] = nums[i];
            }
        }
        return n - pos;
    }
}
