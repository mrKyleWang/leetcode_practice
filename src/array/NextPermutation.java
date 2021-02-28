package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月10日
 */
public class NextPermutation {

    /*
        实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
        如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
        必须原地修改，只允许使用额外常数空间。
        以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
            1,2,3 → 1,3,2
            3,2,1 → 1,2,3
            1,1,5 → 1,5,1
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        // 1,3,2
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 4, 6, 5, 3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        // 1,2,5,3,4,6
    }

    @Test
    public void test3() {
        int[] nums = {6, 5, 4, 3, 2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        // 1,2,3,4,5,6
    }

    /**
     * 从数组倒着查找，找到nums[i] 比nums[i+1]小的时候
     * 就将nums[i]跟nums[i+1]到nums[nums.length - 1]当中找到一个最小的比nums[i]大的元素交换。
     * 交换后，再把nums[i+1]到nums[nums.length-1]排序
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int reverseStart = 0;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                reverseStart = i;
                for (int j = n - 1; j >= i; j--) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        break;
                    }
                }
                break;
            }
        }
        reverse(nums, reverseStart, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            l++;
            r--;
        }
    }
}
