package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 189. 旋转数组
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class RotateArray {

    /*
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 双循环（时间复杂度O(kn)）
     */
    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            if (nums.length > 1) {
                int temp = nums[0];
                int index = 1;
                while (index < nums.length) {
                    int t = nums[index];
                    nums[index] = temp;
                    temp = t;
                    index++;
                }
                nums[0] = temp;
            }
        }

    }

    /**
     * 翻转（时间复杂度O(n)）
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
