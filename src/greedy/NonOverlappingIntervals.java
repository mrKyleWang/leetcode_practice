package greedy;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月31日
 */
public class NonOverlappingIntervals {

    /*
        给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
        注意:
            可以认为区间的终点总是大于它的起点。
            区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。

        示例 1:
            输入: [ [1,2], [2,3], [3,4], [1,3] ]
            输出: 1
            解释: 移除 [1,3] 后，剩下的区间没有重叠。
        示例 2:
            输入: [ [1,2], [1,2], [1,2] ]
            输出: 2
            解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
        示例 3:
            输入: [ [1,2], [2,3] ]
            输出: 0
            解释: 你不需要移除任何区间，因为它们已经是无重叠的了。

     */

    @Test
    public void test() {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        Assert.assertEquals(1, eraseOverlapIntervals(intervals));
    }

    @Test
    public void test2() {
        int[][] intervals = {{1, 2}, {1, 2}, {1, 2}};
        Assert.assertEquals(2, eraseOverlapIntervals(intervals));
    }

    @Test
    public void test3() {
        int[][] intervals = {{1, 2}, {2, 3}};
        Assert.assertEquals(0, eraseOverlapIntervals(intervals));
    }

    @Test
    public void test4() {
        int[][] intervals = {{0, 2}, {1, 3}, {1, 3}, {2, 4}, {3, 5}, {3, 5}, {4, 6}};
        Assert.assertEquals(4, eraseOverlapIntervals(intervals));
    }


    /**
     * 转化为能保留最多的区间的问题
     * 先按起点排序，然后尽量保留区间结尾短的，这样可以给后面区间保留更多空间
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        if (intervals.length > 0) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                int[] interval = intervals[i];
                if (interval[0] < end) {
                    // 重叠了，需要移除一个区间，保留end更小的
                    count++;
                    end = Math.min(interval[1], end);
                } else {
                    end = interval[1];
                }
            }
        }
        return count;
    }
}
