package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 1、两数之和
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class TwoSum {

    /*
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 示例: 给定 nums = [2, 7, 11, 15], target = 9 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(nums, 9)));
        System.out.println(Arrays.toString(twoSum2(nums, 9)));
    }

    /**
     * 解法1：嵌套遍历
     * O(n²)
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    /**
     * 解法2：判断每次遍历到的数i，在map中存不存在target-i的数，存在则返回下标，不存在则push到map
     * O(n)
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
