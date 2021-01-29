package map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1631. 最小体力消耗路径
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月29日
 */
public class PathWithMinimumEffort {

    /*
        你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
        一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
        你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
        一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
        请你返回从左上角走到右下角的最小 体力消耗值 。

        示例 1：
            输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
            输出：2
            解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
            这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
        示例 2：
            输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
            输出：1
            解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
        示例 3：
            输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
            输出：0
            解释：上图所示路径不需要消耗任何体力。

        提示：
            rows == heights.length
            columns == heights[i].length
            1 <= rows, columns <= 100
            1 <= heights[i][j] <= 106
     */

    @Test
    public void test() {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        Assert.assertEquals(2, minimumEffortPath(heights));
    }

    /**
     * 并查集
     * 将相邻两点之间的高度差作为边的权重，从小到大排序
     */
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[] parent = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parent[i] = i;
        }

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = n * i + j;
                if (i > 0) {
                    int upIndex = index - n;
                    edges.add(new int[]{index, upIndex, Math.abs(heights[i][j] - heights[i - 1][j])});
                }
                if (j > 0) {
                    int leftIndex = index - 1;
                    edges.add(new int[]{index, leftIndex, Math.abs(heights[i][j] - heights[i][j - 1])});
                }
            }
        }
        int start = 0;
        int end = m * n - 1;
        int res = 0;
        Collections.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        for (int[] edge : edges) {
            if (find(parent, edge[0]) != find(parent, edge[1])) {
                union(parent, edge[0], edge[1]);
            }
            if (find(parent, start) == find(parent, end)) {
                res = edge[2];
                break;
            }
        }
        return res;
    }

    private void union(int[] parent, int i, int j) {
        int pI = find(parent, i);
        int pJ = find(parent, j);
        if (pI != pJ) {
            parent[pJ] = pI;
        }
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }
}
