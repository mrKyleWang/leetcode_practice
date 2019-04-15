package array;

import java.util.Arrays;

/**
 * 存在重复元素（217）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class ContainsDuplicate {

	/*
	 * 给定一个整数数组，判断是否存在重复元素。
	 * 
	 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
	 */

	public static void main(String[] args) {
		int[] nums = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
		System.out.println(containsDuplicate(nums));
	}

	public static boolean containsDuplicate(int[] nums) {
		int n = nums.length;
		Arrays.sort(nums);
		for (int i = 0; i < n - 1; i++) {
			if (nums[i] == nums[i + 1]) {
				return true;
			}
		}
		return false;
	}
}
