package other;

import java.util.LinkedList;

/**
 * 200. 岛屿数量
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/29
 */
public class NumberOfIslands {

    /*
        给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

        示例 1:
            输入:
                11110
                11010
                11000
                00000
            输出: 1
        示例 2:
            输入:
                11000
                11000
                00100
                00011
            输出: 3
     */

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
        System.out.println(new NumberOfIslands().numIslands(grid2));
    }

    /**
     * 染色：每次碰到1时，将count++，然后通过广度优先将相邻的所有为1的格子变为0
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int[][] around = {{0,1},{1,0},{0,-1},{-1,0}};
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    LinkedList<int[]> queue = new LinkedList<>();
                    queue.push(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        grid[cur[0]][cur[1]] = '0';
                        for (int[] step : around) {
                            int nextI = cur[0] + step[0];
                            int nextJ = cur[1] + step[1];
                            if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == '1') {
                                queue.push(new int[]{nextI, nextJ});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
