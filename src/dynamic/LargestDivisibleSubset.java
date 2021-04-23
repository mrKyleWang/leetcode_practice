package dynamic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月23日
 */
public class LargestDivisibleSubset {

    /*
        给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
        answer[i] % answer[j] == 0 ，或
        answer[j] % answer[i] == 0
        如果存在多个有效解子集，返回其中任何一个均可。

        示例 1：
            输入：nums = [1,2,3]
            输出：[1,2]
            解释：[1,3] 也会被视为正确答案。
        示例 2：
            输入：nums = [1,2,4,8]
            输出：[1,2,4,8]
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 8, 9};
        System.out.println(largestDivisibleSubset(nums));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int max = 1;
        int maxVal = nums[0];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > max) {
                max = dp[i];
                maxVal = nums[i];
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(maxVal);
        int i = n - 1;
        while (nums[i] != maxVal) {
            i--;
        }
        i--;
        max--;
        while (i >= 0) {
            if (maxVal % nums[i] == 0 && dp[i] == max) {
                res.add(nums[i]);
                maxVal = nums[i];
                max--;
            }
            i--;
        }
        return res;
    }
}
