package array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 169. 求众数
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/14
 */
public class MajorityElement {

    /*
        给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
        你可以假设数组是非空的，并且给定的数组总是存在众数。

        示例 1:
        输入: [3,2,3]
        输出: 3

        示例 2:
        输入: [2,2,1,1,1,2,2]
        输出: 2
     */

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement(nums));
        System.out.println(majorityElement2(nums));
    }

    public static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        double threshold = ((double) nums.length / 2);
        for (int num : nums) {
            Integer count = map.get(num);
            if (count == null) {
                count = 0;
            }
            count++;
            if (count >= threshold) {
                return num;
            }
            map.put(num, count);
        }
        return nums[0];
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == nums[i + (length / 2)]) {
                return nums[i];
            }
        }
        return nums[0];
    }
}
