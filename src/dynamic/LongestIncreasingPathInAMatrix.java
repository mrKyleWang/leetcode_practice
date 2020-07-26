package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 329. 矩阵中的最长递增路径
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月26日
 */
public class LongestIncreasingPathInAMatrix {

    /*
        给定一个整数矩阵，找出最长递增路径的长度。
        对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
        示例 1:
            输入: nums =
            [
              [9,9,4],
              [6,6,8],
              [2,1,1]
            ]
            输出: 4
            解释: 最长递增路径为 [1, 2, 6, 9]。
        示例 2:
            输入: nums =
            [
              [3,4,5],
              [3,2,6],
              [2,2,1]
            ]
            输出: 4
            解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
     */

    @Test
    public void test(){
        int[][] matrix = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };
        Assert.assertEquals(4,longestIncreasingPath(matrix));
    }

    @Test
    public void test2(){
        int[][] matrix = {
                {3,4,5},
                {3,2,6},
                {2,2,1}
        };
        Assert.assertEquals(4,longestIncreasingPath(matrix));
    }

    /**
     * 深度优先：每次遍历的节点，再对4个方向的邻接点递归，找到其为终点的最大长度的递减路径
     */
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] result = new int[matrix.length][matrix[0].length];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, find(i, j, matrix, result));
            }
        }
        return max;
    }

    int[][] directions = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private int find(int row, int col, int[][] matrix, int[][] result) {
        if (result[row][col] > 0) {
            return result[row][col];
        }
        int max = 1;
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length && matrix[newRow][newCol] < matrix[row][col]) {
                max = Math.max(max, find(newRow, newCol, matrix, result) + 1);
            }
        }
        result[row][col] = max;
        return max;
    }
}
