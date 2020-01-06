package array;

import java.util.*;

/**
 * 384. 打乱数组
 * @author KyleWang
 * @version 1.0
 * @date 2019年07月26日
 */
public class ShuffleAnArray {

	/*
	    1.以数字集合 1, 2 和 3 初始化数组。

		2.打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
		solution.shuffle();

		3.重设数组到它的初始状态[1,2,3]。
		solution.reset();

		4.随机返回数组[1,2,3]打乱后的结果。
		solution.shuffle();
	 */

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5};
		Solution solution = new Solution(nums);
		System.out.println(Arrays.toString(solution.shuffle()));
	}

	static class Solution {

		private int[] origin;

		public Solution(int[] nums) {
			origin = new int[nums.length];
			System.arraycopy(nums, 0, origin, 0, nums.length);
		}

		/** Resets the array to its original configuration and return it. */
		public int[] reset() {
			return origin;
		}

		/** Returns a random shuffling of the array. */
		public int[] shuffle() {
			// 获取一个从0~array.length的随机数组
			Random rand = new Random();
			int[] randArray = new int[origin.length];
			Map<Integer, Integer> conversion = new HashMap<>();
			for (int i = 0, upper = origin.length; i < origin.length; i++, upper--) {
				int key = rand.nextInt(upper);
				int val = conversion.getOrDefault(key, key);
				randArray[i] = origin[val];
				conversion.put(key, conversion.getOrDefault(upper - 1, upper - 1));
			}
			return randArray;
		}
	}

}
