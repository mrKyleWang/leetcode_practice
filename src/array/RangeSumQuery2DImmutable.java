package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月02日
 */
public class RangeSumQuery2DImmutable {

    /*
        给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
     */

    @Test
    public void test() {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        Assert.assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        Assert.assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        Assert.assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));
    }


    /**
     * 使用sums保存以[0,0]为左上角，[i,j]为右下角的矩形的元素总和，然后根据矩形面积覆盖来计算
     */
    class NumMatrix {

        int[][] sums;

        public NumMatrix(int[][] matrix) {
            if (matrix.length > 0 && matrix[0].length > 0) {
                sums = new int[matrix.length + 1][matrix[0].length + 1];
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        sums[i + 1][j + 1] = sums[i][j + 1] + sums[i + 1][j] - sums[i][j] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sums[row2 + 1][col2 + 1] - sums[row2 + 1][col1] - sums[row1][col2 + 1] + sums[row1][col1];
        }
    }
}
