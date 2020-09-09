package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月09日
 */
public class CombinationSum {

    /*
        给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
        candidates 中的数字可以无限制重复被选取。

        说明：
            所有数字（包括 target）都是正整数。
            解集不能包含重复的组合。 
        示例 1：
            输入：candidates = [2,3,6,7], target = 7,
            所求解集为：
            [
              [7],
              [2,2,3]
            ]
        示例 2：
            输入：candidates = [2,3,5], target = 8,
            所求解集为：
            [
              [2,2,2,2],
              [2,3,3],
              [3,5]
            ]
        提示：
            1 <= candidates.length <= 30
            1 <= candidates[i] <= 200
            candidate 中的每个元素都是独一无二的。
            1 <= target <= 500
     */

    @Test
    public void test() {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> result = combinationSum(candidates, 7);
        System.out.println(result);
    }

    @Test
    public void test2() {
        int[] candidates = {2, 3, 5};
        List<List<Integer>> result = combinationSum(candidates, 8);
        System.out.println(result);
    }

    /**
     * 回溯算法，递归实现
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        sum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void sum(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int cur) {
        if (target == 0) {
            result.add(path);
            return;
        }
        for (int i = cur; i < candidates.length && target - candidates[i] >= 0; i++) {
            ArrayList<Integer> newPath = new ArrayList<>(path);
            newPath.add(candidates[i]);
            sum(result, newPath, candidates, target - candidates[i], i);
        }
    }
}
