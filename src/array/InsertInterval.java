package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 57. 插入区间
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月04日
 */
public class InsertInterval {

    /*
        给出一个无重叠的 ，按照区间起始端点排序的区间列表。
        在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

        示例 1：
            输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
            输出：[[1,5],[6,9]]
        示例 2：
            输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
            输出：[[1,2],[3,10],[12,16]]
            解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     */

    @Test
    public void test() {
        int[][] intervals = {{3, 5}, {12, 15}};
        int[] newInterval = {6, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    @Test
    public void test2() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    @Test
    public void test3() {
        int[][] intervals = {{2, 6}, {7, 9}};
        int[] newInterval = {15, 18};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        List<int[]> list = new ArrayList<>();
        boolean insert = false;
        for (int[] interval : intervals) {
            if (insert) {
                list.add(interval);
            } else {
                if (newInterval[1] < interval[0]) {
                    list.add(newInterval);
                    list.add(interval);
                    insert = true;
                } else if (newInterval[0] <= interval[1]) {
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                } else {
                    list.add(interval);
                }
            }
        }
        if (!insert) {
            list.add(newInterval);
        }
        int[][] result = new int[list.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}
