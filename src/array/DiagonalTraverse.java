package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 498. 对角线遍历
 * @author KyleWang
 * @version 1.0
 * @date 2020/02/28
 */
public class DiagonalTraverse {

    /*
    给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
    示例:
        输入:
        [
         [ 1, 2, 3 ],
         [ 4, 5, 6 ],
         [ 7, 8, 9 ]
        ]
        输出:  [1,2,4,7,5,3,6,8,9]
     */


    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        int[][] matrix = new int[0][0];
        System.out.println(Arrays.toString(findDiagonalOrder(matrix)));
    }

    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            int[] result = new int[m * n];
            int i = 0, j = 0;
            for (int k = 0; k < result.length; k++) {
                System.out.println(matrix[i][j]);
                result[k] = matrix[i][j];
                if ((i + j) % 2 == 0) {
                    if (j < n - 1) {
                        j++;
                        if (i > 0) {
                            i--;
                        }
                    } else {
                        i++;
                    }
                } else {
                    if (i < m - 1) {
                        i++;
                        if (j > 0) {
                            j--;
                        }
                    } else {
                        j++;
                    }
                }
            }
            return result;
        }
        return new int[0];
    }
}
