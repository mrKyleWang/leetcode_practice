package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月31日
 */
public class SubsetsII {

    /*
        给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
        解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 
        示例 1：
            输入：nums = [1,2,2]
            输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
        示例 2：
            输入：nums = [0]
            输出：[[],[0]]
         
        提示：
            1 <= nums.length <= 10
            -10 <= nums[i] <= 10
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0, false);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int index, boolean choosePre) {
        if (index == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 不选当前数字
        dfs(res, path, nums, index + 1, false);

        // 选择当前数字，这里的判断是为了去重，当前一个没有选，而当前数字与前一个相同，那就不继续递归，例如：12_ 会保存至结果，而1_2不保存
        if (choosePre || index == 0 || nums[index] != nums[index - 1]) {
            path.add(nums[index]);
            dfs(res, path, nums, index + 1, true);
            path.remove(path.size() - 1);
        }
    }
}
