package array;

import org.junit.Test;

import java.util.*;

/**
 * 632. 最小区间
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月01日
 */
public class SmallestRangeCoveringElementsFromKLists {

    /*
        你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。
        我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。

        示例 1:
            输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
            输出: [20,24]
        解释:
            列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
            列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
            列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
        注意:
            给定的列表可能包含重复元素，所以在这里升序表示 >= 。
            1 <= k <= 3500
            -105 <= 元素的值 <= 105
     */


    @Test
    public void test() {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(4, 10, 15, 24, 26));
        nums.add(Arrays.asList(0, 9, 12, 20));
        nums.add(Arrays.asList(5, 18, 22, 30));
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

    @Test
    public void test2() {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(10, 10));
        nums.add(Arrays.asList(11, 11));
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

    @Test
    public void test3() {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1, 2, 3));
        nums.add(Arrays.asList(1, 2, 3));
        nums.add(Arrays.asList(1, 2, 3));
        System.out.println(Arrays.toString(smallestRange(nums)));
    }

    /**
     * 双指针+滑动窗口
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        // 构建Map：<num,[所属数组index]>
        TreeMap<Integer, Set<Integer>> map = new TreeMap<>(Collections.reverseOrder());

        // 合并所有数组
        for (int i = 0; i < nums.size(); i++) {
            for (Integer num : nums.get(i)) {
                Set<Integer> set = map.computeIfAbsent(num, k -> new HashSet<>());
                set.add(i);
            }
        }

        // 滑动窗口，保存当前窗口内的数的所属数组index
        int[] window = new int[nums.size()];
        Set<Integer> windowSet = new HashSet<>();


        // 从后向前遍历，保证最后得到的min一定是最靠左的
        Iterator<Map.Entry<Integer, Set<Integer>>> iterator = map.entrySet().iterator();
        int head;
        int tail = map.firstKey();
        int min = Integer.MAX_VALUE;
        int[] result = new int[2];

        while (iterator.hasNext()) {
            head = iterator.next().getKey();
            Set<Integer> newIndexList = map.get(head);
            windowSet.addAll(map.get(head));
            for (Integer index : newIndexList) {
                window[index]++;
            }

            if (windowSet.size() == nums.size()) {
                // 尝试收缩window
                boolean flag = true;
                while (flag) {
                    Set<Integer> tailList = map.get(tail);
                    for (Integer index : tailList) {
                        if (window[index] <= 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        // 移动tail到下个位置
                        do {
                            tail--;
                        } while (tail >= head && !map.containsKey(tail));
                        for (Integer index : tailList) {
                            window[index]--;
                            if (window[index] == 0) {
                                windowSet.remove(index);
                            }
                        }
                    }
                }
                if (tail - head <= min) {
                    result[0] = head;
                    result[1] = tail;
                    min = tail - head;
                }
            }
        }
        return result;
    }
}
