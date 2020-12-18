package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 493. 翻转对
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月18日
 */
public class ReversePairs {

    /*
        给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
        你需要返回给定数组中的重要翻转对的数量。

        示例 1:
            输入: [1,3,2,3,1]
            输出: 2
        示例 2:
            输入: [2,4,3,5,1]
            输出: 3
        注意:
            给定数组的长度不会超过50000。
            输入数组中的所有数字都在32位整数的表示范围内。
     */

    @Test
    public void test() {
        int[] nums = {1, 3, 2, 3, 1};
        Assert.assertEquals(2, reversePairs(nums));
    }

    @Test
    public void test2() {
        int[] nums = {2, 4, 3, 5, 1};
        Assert.assertEquals(3, reversePairs(nums));
    }

    @Test
    public void test3() {
        int[] nums = {5, 2};
        Assert.assertEquals(1, reversePairs(nums));
    }

    @Test
    public void test4() {
        int[] nums = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        Assert.assertEquals(0, reversePairs(nums));
    }

    @Test
    public void test5() {
        int[] nums = {2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647};
        Assert.assertEquals(9, reversePairs(nums));
    }

    @Test
    public void test6() {
        int[] nums = {233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237, 2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004};
        Assert.assertEquals(40, reversePairs(nums));
    }

    /**
     * 分治算法-归并排序
     * 将大的数组递归分成子序列
     * 形成翻转对有两种情况：
     *      a. i,j同属于一个子序列
     *      b. i属于左子序列，j属于右子序列
     * 这样，我们在递归到小子序列（序列中只有2个元素）时，可以判断出a类翻转对的数量，然后再将这个子序列排序，返回上层递归
     * 而在上层对左右两个子序列判断b类翻转对时，由于左右子序列都已为升序，因此，当确定left[i] > 2*right[j]时，left中i以后的数都可以与right[j]形成翻转对
     *         比如：left = {2,5,8} , right = {1,3,4};
     *         顺序遍历left与right，当找到5>2*1时，5后面的8一定也大于2*1，同时因为left和right是左右子序列，left中的索引一定小于right中的索引，因此可以判断为翻转对
     */
    public int reversePairs(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        return merge(nums, 0, nums.length - 1);
    }

    private int merge(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) / 2;

        int result = 0;
        result += merge(nums, left, mid);
        result += merge(nums, mid + 1, right);

        int i = left;
        int j = mid + 1;
        while (i <= mid) {
            while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                j++;
                result += mid - i + 1;
            }
            i++;
        }
        int l = left;
        int r = mid + 1;
        int[] sorted = new int[right - left + 1];
        for (int k = 0; k < sorted.length; k++) {
            if (r > right || (l < mid + 1 && nums[l] < nums[r])) {
                sorted[k] = nums[l];
                l++;
            } else {
                sorted[k] = nums[r];
                r++;
            }
        }
        System.arraycopy(sorted, 0, nums, left, sorted.length);
        return result;
    }
}
