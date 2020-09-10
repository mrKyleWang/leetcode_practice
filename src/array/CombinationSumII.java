package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 40. 组合总和 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月10日
 */
public class CombinationSumII {

    /*
        给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的每个数字在每个组合中只能使用一次。

        说明：
            所有数字（包括目标数）都是正整数。
            解集不能包含重复的组合。 
        示例 1:
            输入: candidates = [10,1,2,7,6,1,5], target = 8,
            所求解集为:
            [
              [1, 7],
              [1, 2, 5],
              [2, 6],
              [1, 1, 6]
            ]
        示例 2:
            输入: candidates = [2,5,2,1,2], target = 5,
            所求解集为:
            [
              [1,2,2],
              [5]
            ]
     */

    @Test
    public void test() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(combinationSum2(candidates, 8));
    }

    @Test
    public void test2() {
        int[] candidates = {2, 5, 2, 1, 2};
        System.out.println(combinationSum2(candidates, 5));
    }

    @Test
    public void test3() {
        int[] candidates = {1, 1, 2, 2, 2, 3, 4, 4, 4};
        System.out.println(combinationSum2(candidates, 6));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        sum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    public void sum(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int cur) {
        if (target == 0) {
            result.add(path);
            return;
        }
        for (int i = cur; i < candidates.length && target - candidates[i] >= 0; i++) {
            // 这里是做了对相同深度下相同值的去重
            if (i > cur && candidates[i] == candidates[i - 1]) {
                continue;
            }
            ArrayList<Integer> newPath = new ArrayList<>(path);
            newPath.add(candidates[i]);
            sum(result, newPath, candidates, target - candidates[i], i + 1);
        }
    }

}
