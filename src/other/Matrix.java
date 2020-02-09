package other;

import org.junit.Test;

import java.util.Arrays;

/**
 * 542. 01 矩阵
 * @author KyleWang
 * @version 1.0
 * @date 2020/02/08
 */
public class Matrix {

    /*
    给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
    两个相邻元素间的距离为 1 。

    示例 1:
        输入:
            0 0 0
            0 1 0
            0 0 0
        输出:
            0 0 0
            0 1 0
            0 0 0
    示例 2:
        输入:
            0 0 0
            0 1 0
            1 1 1
        输出:
            0 0 0
            0 1 0
            1 2 1
    注意:
        给定矩阵的元素个数不超过 10000。
        给定矩阵中至少有一个元素是 0。
        矩阵中的元素只在四个方向上相邻: 上、下、左、右。
    */

    @Test
    public void test() {
        int[][] matrix = {
                {1,0,1,1,0,0,1,0,0,1},
                {0,1,1,0,1,0,1,0,1,1},
                {0,0,1,0,1,0,0,1,0,0},
                {1,0,1,0,1,1,1,1,1,1},
                {0,1,0,1,1,0,0,0,0,1},
                {0,0,1,0,1,1,1,0,1,0},
                {0,1,0,1,0,1,0,0,1,1},
                {1,0,0,0,1,1,1,1,0,1},
                {1,1,1,1,1,1,1,0,1,0},
                {1,1,1,1,0,1,0,0,1,1}};
        System.out.println(Arrays.deepToString(updateMatrix(matrix)));
    }

    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // 正向遍历，从左上向右下动态推
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] != 0) {
                    int tempMin = row + col;
                    if (r > 0) {
                        tempMin = Math.min(tempMin, matrix[r - 1][c] + 1);
                    }
                    if (c > 0) {
                        tempMin = Math.min(tempMin, matrix[r][c - 1] + 1);
                    }
                    matrix[r][c] = tempMin;
                }
            }
        }

        // 反向遍历，从右下向左上动态推
        for (int r = row - 1; r >= 0; r--) {
            for (int c = col - 1; c >= 0; c--) {
                if (matrix[r][c] != 0) {
                    int tempMin = matrix[r][c];
                    if (r < row - 1) {
                        tempMin = Math.min(tempMin, matrix[r + 1][c] + 1);
                    }
                    if (c < col - 1) {
                        tempMin = Math.min(tempMin, matrix[r][c + 1] + 1);
                    }
                    matrix[r][c] = tempMin;
                }
            }
        }
        return matrix;
    }
}
