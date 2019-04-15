package array;

import java.util.Arrays;

/**
 * 只出现一次的数字（136）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class SingleNumber {

	/*
	 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
	 * 
	 * 说明：
	 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
	 */

	public static void main(String[] args) {
		int[] nums = { 4, 1, 2, 1, 2 };
		System.out.println(nums);
	}

	public static int singleNumber(int[] nums) {
		if (nums.length > 1) {
			Arrays.sort(nums);
			for (int i = 0; i < nums.length; i++) {
				if ((i == 0 && nums[i] != nums[i + 1]) || (i == nums.length - 1 && nums[i] != nums[i - 1])
						|| (i > 0 && i < nums.length - 1 && nums[i] != nums[i - 1] && nums[i] != nums[i + 1])) {
					return nums[i];
				}
			}
		}
		return nums[0];
	}
}
