package greedy;

import org.junit.Test;

import java.util.Arrays;

/**
 * 321. 拼接最大数
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月02日
 */
public class CreateMaximumNumber {

    /*
        给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
        现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
        求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
        说明: 请尽可能地优化你算法的时间和空间复杂度。

        示例 1:
            输入:
                nums1 = [3, 4, 6, 5]
                nums2 = [9, 1, 2, 5, 8, 3]
                k = 5
            输出:
                [9, 8, 6, 5, 3]

        示例 2:
            输入:
                nums1 = [6, 7]
                nums2 = [6, 0, 4]
                k = 5
            输出:
                [6, 7, 6, 0, 4]

        示例 3:
            输入:
                nums1 = [3, 9]
                nums2 = [8, 9]
                k = 3
            输出:
                [9, 8, 9]
     */

    @Test
    public void test() {
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 = {9, 1, 2, 5, 8, 3};
        int k = 5;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }

    @Test
    public void test2() {
        int[] nums1 = {6, 7};
        int[] nums2 = {6, 0, 4};
        int k = 5;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }

    @Test
    public void test3() {
        int[] nums1 = {3, 9};
        int[] nums2 = {8, 9};
        int k = 3;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }

    @Test
    public void test4() {
        int[] nums1 = {8, 6, 9};
        int[] nums2 = {1, 7, 5};
        int k = 3;
        System.out.println(Arrays.toString(maxNumber(nums1, nums2, k)));
    }


    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        // 从nums1中选x个数，nums2中选y个数
        for (int x = Math.max(0, k - nums2.length); x <= k && x <= nums1.length; x++) {
            // 分别从nums1，nums2中找出最大子序列，合并
            int[] merge = merge(maxSubSeq(nums1, x), maxSubSeq(nums2, k - x));
            if (compare(merge, 0, result, 0) > 0) {
                result = merge;
            }
        }
        return result;
    }

    /**
     * 使用单调栈找出最大子序列
     */
    private int[] maxSubSeq(int[] nums, int k) {
        int[] stack = new int[k];
        if (k > 0) {
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                while (i > 0 && index > 0 && stack[index - 1] < num && nums.length - i > k - index) {
                    index--;
                }
                if (index < k) {
                    stack[index] = num;
                    index++;
                }
            }
        }
        return stack;
    }

    /**
     * 合并两个子序列
     */
    private int[] merge(int[] seq1, int[] seq2) {
        int[] mergeSeq = new int[seq1.length + seq2.length];
        for (int i = 0, index1 = 0, index2 = 0; index1 < seq1.length || index2 < seq2.length; i++) {
            if (compare(seq1, index1, seq2, index2) > 0) {
                mergeSeq[i] = seq1[index1];
                index1++;
            } else {
                mergeSeq[i] = seq2[index2];
                index2++;
            }
        }
        return mergeSeq;
    }

    /**
     * 比较两个子序列的大小
     */
    private int compare(int[] seq1, int index1, int[] seq2, int index2) {
        for (; index1 < seq1.length && index2 < seq2.length; index1++, index2++) {
            if (seq1[index1] != seq2[index2]) {
                return seq1[index1] - seq2[index2];
            }
        }
        return (seq1.length - index1) - (seq2.length - index2);
    }

}
