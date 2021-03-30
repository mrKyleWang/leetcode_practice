package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 74. 搜索二维矩阵
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月30日
 */
public class SearchA2DMatrix {

    /*
        编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
        每行中的整数从左到右按升序排列。
        每行的第一个整数大于前一行的最后一个整数。
         

        示例 1：
            输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
            输出：true
        示例 2：
            输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
            输出：false

        提示：
            m == matrix.length
            n == matrix[i].length
            1 <= m, n <= 100
            -104 <= matrix[i][j], target <= 104
     */
    @Test
    public void test() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        Assert.assertEquals(false, searchMatrix(matrix, 20));
    }

    @Test
    public void test2() {
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        Assert.assertEquals(true, searchMatrix(matrix, 23));
    }

    /**
     * 两种方法：
     * 1. 先二分查找行，再在行中二分查找
     * 2. 把整个矩阵作为一个大数组，一次二分
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) {
            return false;
        }

        int l = 0, r = m * n - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) {
                return true;
            } else if (val < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return matrix[l / n][l % n] == target;
    }
}
