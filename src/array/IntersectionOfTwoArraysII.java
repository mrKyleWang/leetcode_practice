package array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 两个数组的交集 II（350）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月14日
 */
public class IntersectionOfTwoArraysII {

	/*
	 * 给定两个数组，编写一个函数来计算它们的交集。
	 */

	public static void main(String[] args) {
		int[] nums1 = { 4, 9, 5 };
		int[] nums2 = { 9, 4, 9, 8, 4 };
		System.out.println(Arrays.toString(intersect(nums1, nums2)));
	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		ArrayList<Integer> removeList = new ArrayList<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i1 : nums1) {
			for (int j = 0; j < nums2.length; j++) {
				if (i1 == nums2[j] && !removeList.contains(j)) {
					// 将nums2中已遍历过的索引保存，下次跳过
					removeList.add(j);
					list.add(i1);
					break;
				}
			}
		}
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;
	}

}
