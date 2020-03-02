package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * @author KyleWang
 * @version 1.0
 * @date 2020/03/02
 */
public class SpiralMatrix {

    /*
        给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
        示例 1:
            输入:
            [
             [ 1, 2, 3 ],
             [ 4, 5, 6 ],
             [ 7, 8, 9 ]
            ]
            输出: [1,2,3,6,9,8,7,4,5]
        示例 2:
            输入:
            [
              [1, 2, 3, 4],
              [5, 6, 7, 8],
              [9,10,11,12]
            ]
            输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     */

    @Test
    public void test() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(matrix));
//        int[][] matrix2 = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] matrix2 = new int[][]{{1}, {2}};
        System.out.println(spiralOrder(matrix2));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        int m = matrix.length;
        if (m > 0) {
            int n = matrix[0].length;
            int[][] visited = new int[m][n];
            int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int i = 0;
            int j = 0;
            int direction = 0;
            while (list.size() < m * n) {
                list.add(matrix[i][j]);
                visited[i][j] = 1;
                System.out.println(matrix[i][j]);

                int newI = i + directions[direction][0];
                int newJ = j + directions[direction][1];
                if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || visited[newI][newJ] == 1) {
                    direction = direction == directions.length - 1 ? 0 : direction + 1;
                    i += directions[direction][0];
                    j += directions[direction][1];
                } else {
                    i = newI;
                    j = newJ;
                }
            }
        }
        return list;
    }

}
