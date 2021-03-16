package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月16日
 */
public class SpiralMatrixII {

    /*
        https://leetcode-cn.com/problems/spiral-matrix-ii/
        给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     */

    @Test
    public void test() {
        System.out.println(Arrays.deepToString(generateMatrix(3)));
    }


    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n > 0) {
            boolean[][] visited = new boolean[n][n];
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int index = 0;
            int val = 0;
            int i = 0, j = 0;
            while (val < n * n) {
                if (i >= 0 && i < n && j >= 0 && j < n && !visited[i][j]) {
                    matrix[i][j] = ++val;
                    visited[i][j] = true;
                } else {
                    i = i - directions[index % 4][0];
                    j = j - directions[index % 4][1];
                    index++;
                }
                i = i + directions[index % 4][0];
                j = j + directions[index % 4][1];
            }
        }
        return matrix;
    }
}
