package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 347. 前 K 个高频元素
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月07日
 */
public class TopKFrequentElements {


    /*
        给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
        示例 1:
            输入: nums = [1,1,1,2,2,3], k = 2
            输出: [1,2]
        示例 2:
            输入: nums = [1], k = 1
            输出: [1]
        提示：
            你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
            你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
            题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
            你可以按任意顺序返回答案。
     */

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    @Test
    public void test2() {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        System.out.println(Arrays.toString(topKFrequent(nums, 2)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.compute(num, (key, v) -> v == null ? 1 : v + 1);
        }
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }
        return result;
    }
}
