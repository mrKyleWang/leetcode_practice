package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. 递增子序列
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月25日
 */
public class IncreasingSubsequences {

    /*
        给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
        示例:
            输入: [4, 6, 7, 7]
            输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
        说明:
            给定数组的长度不会超过15。
            数组中的整数范围是 [-100,100]。
            给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
     */

    @Test
    public void test() {
        int[] nums = {4,6,7,7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        System.out.println(subsequences);
    }

    /**
     * DFS
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        find(nums, -1, new ArrayList<>(), result);
        return result;
    }

    private void find(int[] nums, int cur, List<Integer> path, List<List<Integer>> result) {
        Set<Integer> set = new HashSet<>();
        for (int i = cur + 1; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            set.add(nums[i]);
            if (cur < 0 || nums[cur] <= nums[i]) {
                List<Integer> temp = new ArrayList<>(path);
                temp.add(nums[i]);
                if (temp.size() > 1) {
                    result.add(temp);
                }
                find(nums, i, temp, result);
            }
        }
    }

}
