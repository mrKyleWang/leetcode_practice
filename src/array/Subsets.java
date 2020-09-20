package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月20日
 */
public class Subsets {

    /*
        给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
        说明：解集不能包含重复的子集。

        示例:
            输入: nums = [1,2,3]
            输出:
            [
              [3],
              [1],
              [2],
              [1,2,3],
              [1,3],
              [2,3],
              [1,2],
              []
            ]
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    /**
     * 遍历，遇到一个数就把所有已有的子集加上该数组成新的子集，遍历完毕即是所有子集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(result.get(i));
                temp.add(num);
                result.add(temp);
            }
        }
        return result;
    }

}
