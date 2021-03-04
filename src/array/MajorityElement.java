package array;

import org.junit.Assert;
import org.junit.Test;

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

    @Test
    public void test() {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
        Assert.assertEquals(2, majorityElement(nums));
        Assert.assertEquals(2, majorityElement2(nums));
        Assert.assertEquals(2, majorityElement3(nums));
    }

    /**
     * 方法1：map，时间O(n) 空间O(n)
     */
    public int majorityElement(int[] nums) {
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

    /**
     * 方法2：排序，时间O(nlogn) 空间O(1)
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == nums[i + (length / 2)]) {
                return nums[i];
            }
        }
        return nums[0];
    }


    /**
     * 方法3：
     * 每次消除两个不同的数，最后剩下的数是唯一可能为众数的数
     */
    public int majorityElement3(int[] nums) {
        // 第一次遍历，找出两两消除后剩下的数
        int candidate = 0;
        int hp = 0;
        for (int num : nums) {
            if (hp == 0) {
                candidate = num;
                hp = 1;
            } else if (num == candidate) {
                hp++;
            } else {
                hp--;
            }
        }

        // 第二次遍历，验证这个数
        int count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count > nums.length / 2 ? candidate : -1;
    }

}
