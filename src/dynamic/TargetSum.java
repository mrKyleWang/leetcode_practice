package dynamic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/26
 */
public class TargetSum {

    /*
        给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
        返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

        示例 1:
            输入: nums: [1, 1, 1, 1, 1], S: 3
            输出: 5
            解释:
                -1+1+1+1+1 = 3
                +1-1+1+1+1 = 3
                +1+1-1+1+1 = 3
                +1+1+1-1+1 = 3
                +1+1+1+1-1 = 3
                一共有5种方法让最终目标和为3。
        注意:
            数组非空，且长度不会超过20。
            初始的数组的和不会超过1000。
            保证返回的最终结果能被32位整数存下。
     */

    @Test
    public void test() {
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        System.out.println(findTargetSumWays(nums, 3));
        System.out.println(findTargetSumWays2(nums, 1));
    }

    /**
     * 使用DFS，递归求解
     */
    public int findTargetSumWays(int[] nums, int S) {
        return find(nums, S, 0, 0);
    }

    private int find(int[] nums, int S, int pre, int index) {
        int count = 0;
        if (index == nums.length - 1) {
            if (pre + nums[index] == S) {
                count++;
            }
            if (pre - nums[index] == S) {
                count++;
            }
        } else {
            count += find(nums, S, pre + nums[index], index + 1);
            count += find(nums, S, pre - nums[index], index + 1);
        }
        return count;
    }

    /**
     * 使用动态规划方式
     */
    public int findTargetSumWays2(int[] nums, int S) {
        Map<Integer, Integer> map = new HashMap<>();
        if (nums[0] == 0) {
            map.put(nums[0], 2);
        } else {
            map.put(nums[0], 1);
            map.put(-nums[0], 1);
        }
        for (int i = 1; i < nums.length; i++) {
            Map<Integer, Integer> temp = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int addNum = entry.getKey() + nums[i];
                int reduceNum = entry.getKey() - nums[i];
                temp.computeIfPresent(addNum, (k, v) -> temp.get(k) + entry.getValue());
                temp.computeIfAbsent(addNum, (k) -> entry.getValue());
                temp.computeIfPresent(reduceNum, (k, v) -> temp.get(k) + entry.getValue());
                temp.computeIfAbsent(reduceNum, (k) -> entry.getValue());
            }
            map = temp;
        }
        return map.get(S) == null ? 0 : map.get(S);
    }
}
