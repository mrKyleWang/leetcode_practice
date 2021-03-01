package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 56. 合并区间
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月01日
 */
public class MergeIntervals {

    /*
        以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
        请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

        示例 1：
            输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
            输出：[[1,6],[8,10],[15,18]]
            解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
        示例 2：
            输入：intervals = [[1,4],[4,5]]
            输出：[[1,5]]
            解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。

        提示：
            1 <= intervals.length <= 104
            intervals[i].length == 2
            0 <= starti <= endi <= 104
     */
    @Test
    public void test() {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    @Test
    public void test2() {
        int[][] intervals = {{1, 4}, {0, 2}, {3, 5}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }


    /**
     * 先根据区间起始位置排序，每次判断当前区间和上一个区间是否重叠来进行合并
     * 这里用intervals本身保存结果，因此使用pos标识结果的当前索引
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        int pos = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[pos - 1][1] < intervals[i][0]) {
                intervals[pos++] = intervals[i];
            } else if (intervals[pos - 1][1] < intervals[i][1]) {
                intervals[pos - 1][1] = intervals[i][1];
            }
        }
        return Arrays.copyOfRange(intervals, 0, pos);
    }
}
