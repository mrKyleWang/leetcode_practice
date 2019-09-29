package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 * @author KyleWang
 * @version 1.0
 * @date 2019年08月24日
 */
public class ThreeSum {

    /*
        给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
        例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
        满足要求的三元组集合为：
        [
          [-1, 0, 1],
          [-1, -1, 2]
        ]
     */

    public static void main(String[] args) {
//        int[] nums = {-1, 0, 1, 2, -1, -4};
//        int[] nums = {-4, -1, -1, 0, 1, 2};
        int[] nums = {0, 0, 0, 0};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length >= 3) {
            Arrays.sort(nums);
            for (int i = 0; i < nums.length - 2; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int twoSum = nums[j] + nums[k];
                    if (nums[i] + twoSum == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        while (j < k && nums[j] == nums[j + 1]) {
                            j++;
                        }
                        while (j < k && nums[k] == nums[k - 1]) {
                            k--;
                        }
                        j++;
                        k--;

                    } else {
                        if (nums[i] + twoSum < 0) {
                            while (j < k && nums[j] == nums[j + 1]) {
                                j++;
                            }
                            j++;
                        } else {
                            while (j < k && nums[k] == nums[k - 1]) {
                                k--;
                            }
                            k--;
                        }
                    }
                }
            }
        }
        return result;
    }
}
