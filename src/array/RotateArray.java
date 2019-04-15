package array;

import java.util.Arrays;

/**
 * 旋转数组（189）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class RotateArray {

	/*
	 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
	 */

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		rotate(nums, 3);
		System.out.println(Arrays.toString(nums));
	}

	public static void rotate(int[] nums, int k) {
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
}
