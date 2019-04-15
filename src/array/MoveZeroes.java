package array;

import java.util.Arrays;

/**
 * 移动零（283）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class MoveZeroes {

	/*
	 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 示例: 输入: [0,1,0,3,12] 输出:
	 * [1,3,12,0,0] 说明: 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
	 */

	public static void main(String[] args) {
		int[] nums = { 0, 1, 0, 3, 12 };
		moveZeroes(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static void moveZeroes(int[] nums) {
		for (int i = 0, j = nums.length - 1; i < j;) {
			if (nums[i] == 0) {
				moveEnd(nums, i, j);
				j--;
			} else {
				i++;
			}
		}
	}

	// 将index位置的值顺移到end
	private static void moveEnd(int[] nums, int index, int target) {
		for (int i = index; i < target; i++) {
			exchange(nums, i, i + 1);
		}
	}

	// 交换两个位置的值
	private static void exchange(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
}
