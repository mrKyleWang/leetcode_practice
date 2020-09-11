package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月11日
 */
public class CombinationSumIII {

    /*
        找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
        说明：
            所有数字都是正整数。
            解集不能包含重复的组合。 
        示例 1:
            输入: k = 3, n = 7
            输出: [[1,2,4]]
        示例 2:
            输入: k = 3, n = 9
            输出: [[1,2,6], [1,3,5], [2,3,4]]
     */

    @Test
    public void test() {
        List<List<Integer>> result = combinationSum3(3, 7);
        System.out.println(result);
    }

    @Test
    public void test2() {
        List<List<Integer>> result = combinationSum3(3, 15);
        System.out.println(result);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        sum(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    public void sum(List<List<Integer>> result, List<Integer> path, int k, int n, int cur) {
        if (n == 0 && k == 0) {
            result.add(path);
            return;
        }
        if (k > 0) {
            for (int i = cur; i <= 9 && n - i >= 0; i++) {
                ArrayList<Integer> newPath = new ArrayList<>(path);
                newPath.add(i);
                sum(result, newPath, k - 1, n - i, i + 1);
            }
        }
    }
}
