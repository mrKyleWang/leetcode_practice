package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 46. 全排列
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月01日
 */
public class Permutations {

    /*
        给定一个 没有重复 数字的序列，返回其所有可能的全排列。
        示例:

        输入: [1,2,3]
        输出:
        [
          [1,2,3],
          [1,3,2],
          [2,1,3],
          [2,3,1],
          [3,1,2],
          [3,2,1]
        ]
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Integer> option = new LinkedList<>();
        for (int num : nums) {
            option.offer(num);
        }
        dfs(res, new ArrayList<>(), option);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, Queue<Integer> option) {
        if (option.isEmpty()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < option.size(); i++) {
            Integer num = option.poll();
            path.add(num);
            dfs(res, path, option);
            option.offer(num);
            path.remove(path.size() - 1);
        }
    }
}
