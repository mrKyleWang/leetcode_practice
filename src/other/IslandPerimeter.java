package other;

import org.junit.Assert;
import org.junit.Test;

/**
 * 463. 岛屿的周长
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月30日
 */
public class IslandPerimeter {

    /*
        给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
        网格中的格子水平和垂直方向相连（对角线方向不相连）。
        整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
        岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。
        格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。

        示例 :
            输入:
            [[0,1,0,0],
             [1,1,1,0],
             [0,1,0,0],
             [1,1,0,0]]
        输出: 16
        解释: 它的周长是下面图片中的 16 个黄色的边：
     */

    @Test
    public void test() {
        int[][] grid = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
        Assert.assertEquals(16, islandPerimeter(grid));
    }

    public int islandPerimeter(int[][] grid) {
        int length = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int block = grid[i][j];
                if (block == 1) {
                    length += 4;
                    for (int[] direction : directions) {
                        int newI = i + direction[0];
                        int newJ = j + direction[1];
                        if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && grid[newI][newJ] == 1) {
                            length -= 1;
                        }
                    }
                }
            }
        }
        return length;
    }


}
