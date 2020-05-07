package array;

import java.util.Arrays;

/**
 * 26. 删除排序数组中的重复项
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class RemoveDuplicatesFromSortedArray {

	/*
	 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
	 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
	 */

	public static void main(String[] args) {
		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println(removeDuplicates(nums));
		System.out.println(Arrays.toString(nums));
	}

	public static int removeDuplicates(int[] nums) {
		int count = 0;
		if (nums.length > 0) {
			int pre = nums[0];
			for (int i = 1; i < nums.length; i++) {
				if (count > 0) {
					nums[i - count] = nums[i];
				}
				if (nums[i] == pre) {
					count++;
				} else {
					pre = nums[i];
				}
			}
		}
		return nums.length - count;
	}
}
