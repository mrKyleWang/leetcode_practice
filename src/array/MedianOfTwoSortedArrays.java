package array;

import org.junit.Test;

/**
 * 4. 寻找两个有序数组的中位数
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月01日
 */
public class MedianOfTwoSortedArrays {

    /*
        给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
        请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
        你可以假设 nums1 和 nums2 不会同时为空。
        示例 1:
            nums1 = [1, 3]
            nums2 = [2]
            则中位数是 2.0
        示例 2:
            nums1 = [1, 2]
            nums2 = [3, 4]
            则中位数是 (2 + 3)/2 = 2.5


        1,2,3,5
        3,6,7,8
   */

    @Test
    public void test() {
        int[] nums1 = {1, 3};
        int[] nums2 = {1, 3};
        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findKTh(nums1, 0, nums2, 0, 2));
        System.out.println(findKTh(nums1, 0, nums2, 0, 3));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double m1 = findKTh(nums1, 0, nums2, 0, (nums1.length + nums2.length + 1) / 2);
        double m2 = findKTh(nums1, 0, nums2, 0, (nums1.length + nums2.length + 2) / 2);

        return (m1 + m2) / 2;
    }

    /**
     * 找第K小的数
     * 如果在i1~ 和 i2~ 两个范围内找第k大的数，则先各自找出自己的第k/2大的数，
     * 如果n1<n2，则说明此时的范围内，arr1[i1]~n1这段范围内的数为最小的k/2个数，可直接移除后，即从剩余的范围内查找第k-k/2个数
     *
     * 比如：从arr1和arr2中找出第4大的数，此时从arr1找出第2大n1，从arr2中找出第2大n2，
     * 如果n1<n2，则将0~n1范围排除，因为这是当前范围内最小的2个数，此时最小的2个数排除掉了，那么就在剩余的范围内找到的第4-2=2大的数
     */
    private double findKTh(int[] nums1, int i1, int[] nums2, int i2, int k) {
        if (i1 >= nums1.length) {
            return nums2[i2 + k - 1];
        }
        if (i2 >= nums2.length) {
            return nums1[i1 + k - 1];
        }
        if (k == 1) {
            return Math.min(nums1[i1], nums2[i2]);
        }
        int n1 = i1 + k / 2 - 1 < nums1.length ? nums1[i1 + k / 2 - 1] : Integer.MAX_VALUE;
        int n2 = i2 + k / 2 - 1 < nums2.length ? nums2[i2 + k / 2 - 1] : Integer.MAX_VALUE;
        if (n1 < n2) {
            return findKTh(nums1, i1 + k / 2, nums2, i2, k - k / 2);
        } else {
            return findKTh(nums1, i1, nums2, i2 + k / 2, k - k / 2);
        }
    }


}
