package array;

import org.junit.Test;

import java.util.*;

/**
 * 47. 全排列 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月18日
 */
public class PermutationsII {


    /*
        给定一个可包含重复数字的序列，返回所有不重复的全排列。
        示例:
            输入: [1,1,2]
            输出:
            [
              [1,1,2],
              [1,2,1],
              [2,1,1]
            ]
     */

    @Test
    public void test() {
        int[] nums = {1, 1, 3};
        System.out.println(permuteUnique(nums));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2, 3};
        System.out.println(permuteUnique(nums));
    }

    /**
     * 通过递归，改变在每层所使用的数
     * 关键在于去重，在每层使用set保存当前层已使用过的数并过滤
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Deque<Integer> retain = new LinkedList<>();
        for (int num : nums) {
            retain.add(num);
        }
        add(result, new ArrayList<>(), retain, nums.length);
        return result;
    }

    private void add(List<List<Integer>> result, List<Integer> path, Deque<Integer> retain, int cur) {
        if (cur == 0) {
            result.add(path);
            return;
        }
        int size = retain.size();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            List<Integer> newPath = new ArrayList<>(path);
            Integer next = retain.getFirst();
            newPath.add(next);
            Integer remove = retain.removeFirst();
            if (!set.contains(next)) {
                add(result, newPath, retain, cur - 1);
                set.add(next);
            }
            retain.addLast(remove);
        }
    }
}
