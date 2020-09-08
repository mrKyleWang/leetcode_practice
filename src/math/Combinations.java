package math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月08日
 */
public class Combinations {


    /*
        给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
        示例:
        输入: n = 4, k = 2
        输出:
            [
              [2,4],
              [3,4],
              [2,3],
              [1,2],
              [1,3],
              [1,4],
            ]
     */

    @Test
    public void test() {
        List<List<Integer>> result = combine(6, 4);
        System.out.println(result);
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combine(n, k, 0, new ArrayList<>(), result);
        return result;
    }

    public void combine(int n, int k, int start, List<Integer> path, List<List<Integer>> result) {
        if (k == 1) {
            for (int i = start + 1; i <= n; i++) {
                ArrayList<Integer> newPath = new ArrayList<>(path);
                newPath.add(i);
                result.add(newPath);
            }
        } else {
            for (int i = start + 1; i <= n; i++) {
                ArrayList<Integer> newPath = new ArrayList<>(path);
                newPath.add(i);
                combine(n, k - 1, i, newPath, result);
            }
        }
    }
}
