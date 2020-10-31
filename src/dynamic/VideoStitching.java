package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 1024. 视频拼接
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月26日
 */
public class VideoStitching {

    /*
        你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
        视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。
        我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
        我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。

        示例 1：
            输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
            输出：3
            解释：
            我们选中 [0,2], [8,10], [1,9] 这三个片段。
            然后，按下面的方案重制比赛片段：
            将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
            现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
        示例 2：
            输入：clips = [[0,1],[1,2]], T = 5
            输出：-1
            解释：
            我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
        示例 3：
            输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
            输出：3
            解释：
            我们选取片段 [0,4], [4,7] 和 [6,9] 。
        示例 4：
            输入：clips = [[0,4],[2,8]], T = 5
            输出：2
            解释：
                注意，你可能录制超过比赛结束时间的视频。
         
        提示：
            1 <= clips.length <= 100
            0 <= clips[i][0] <= clips[i][1] <= 100
            0 <= T <= 100
     */

    @Test
    public void test() {
        int[][] clips = {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        Assert.assertEquals(3, videoStitching(clips, 10));
    }

    @Test
    public void test2() {
        int[][] clips = {{0, 1}, {1, 2}};
        Assert.assertEquals(-1, videoStitching(clips, 5));
    }

    @Test
    public void test3() {
        int[][] clips = {{0, 1}, {6, 8}, {0, 2}, {5, 6}, {0, 4}, {0, 3}, {6, 7}, {1, 3}, {4, 7}, {1, 4}, {2, 5}, {2, 6}, {3, 4}, {4, 5}, {5, 7}, {6, 9}};
        Assert.assertEquals(3, videoStitching(clips, 9));
    }

    @Test
    public void test4() {
        int[][] clips = {{0, 4}, {2, 8}};
        Assert.assertEquals(2, videoStitching(clips, 5));
    }


    /**
     * 动态规划：
     * dp[i] 保存能覆盖[0,i)区间所需的最少片段数
     * 枚举所有的子区间来依次计算出所有的dp值。
     *  我们首先枚举i，同时对于任意一个子区间[aj,bj)，如果满足aj<i<bj，
     *  那么这个子区间就可以覆盖[0,i)的后半段，前半段则可以用dp[aj]表示
     *  则dp[i] = dp[aj]+1
     */
    public int videoStitching(int[][] clips, int T) {
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                int start = clip[0];
                int end = clip[1];
                if (i > start && i <= end) {
                    dp[i] = Math.min(dp[i], dp[start] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

}