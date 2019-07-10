package dynamic;

/**
 * 53. 最大子序和
 * @author KyleWang
 * @version 1.0
 * @date 2019年07月09日
 */
public class MaxSubArray {

	/*
	给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

	示例:
	输入: [-2,1,-3,4,-1,2,1,-5,4],
	输出: 6
	解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
	进阶:如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
	*/

	public static void main(String[] args) {
		int[] nums = { -1, -1, -2, -2 };
		System.out.println(maxSubArray(nums));
	}

	private static int maxSubArray(int[] nums) {
		int maxSum = nums[0];
		int sum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int current = nums[i];
			// 只要前面的和为负数，并且比当前数小，则直接从当前开始重新求和
			if (0 > sum && sum < current) {
				sum = current;
			} else {
				sum += current;
			}
			if (sum > maxSum) {
				maxSum = sum;
			}
		}
		return maxSum;
	}
}
