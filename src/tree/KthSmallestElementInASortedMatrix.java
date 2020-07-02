package tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * 378. 有序矩阵中第K小的元素
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月02日
 */
public class KthSmallestElementInASortedMatrix {

    /*
        给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
        请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。

        示例：
            matrix = [
               [ 1,  5,  9],
               [10, 11, 13],
               [12, 13, 15]
            ],
            k = 8,
            返回 13。
        提示：
            你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。

     */

    @Test
    public void test() {
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}};
        int k = 8;
        Assert.assertEquals(13, kthSmallest(matrix, k));
    }

    @Test
    public void test2() {
        int[][] matrix = {{200000000}};
        int k = 1;
        Assert.assertEquals(200000000, kthSmallest(matrix, k));
    }

    @Test
    public void test3() {
        int[][] matrix = {
                {1, 2},
                {1, 3}};
        int k = 2;
        Assert.assertEquals(1, kthSmallest(matrix, k));
    }

    @Test
    public void test4() {
        int[][] matrix = {
                {1, 3, 5},
                {6, 7, 12},
                {11,11, 14}};
        int k = 3;
        Assert.assertEquals(5, kthSmallest(matrix, k));
    }

    @Test
    public void test5() {
        int[][] matrix = {
                {1, 3, 5 ,9},
                {6, 7, 12,16},
                {11,11,13,17},
                {13,14,16,19}};
        int k = 10;
        Assert.assertEquals(13, kthSmallest(matrix, k));
    }

    /**
     * 左上角元素是下界，右下角元素是上界，划定出一个值域，第 k 小的元素肯定在这个值域中
     * 我们对值域进行二分查找，逼近值域中的目标值——第 k 小的元素
     * 算出中间值，并求出矩阵里小于等于这个中间值的有几个，count 个
     * count 和 k 比较，如果比 k 小，说明中间值小了，调整值域范围，否则，说明中间值大了，调整值域范围，一步步锁定目标值
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];
        while (low <= high) {
            int midVal = (high - low) / 2 + low;
            int count = getCount(matrix, midVal);
            if (count < k) {
                low = midVal + 1;
            } else {
                high = midVal - 1;
            }
        }
        return low;
    }

    /**
     * 我们可以先让它和第一行的最右元素进行比较
     * 如果大于等于它，就大于等于它左边所有数和它自己，个数累加进去，考察下一行
     * 否则，留在当前行，和左边较小的一个比较
     */
    private int getCount(int[][] matrix, int midVal) {
        int row = 0;
        int col = matrix.length - 1;
        int count = 0;
        while (row < matrix.length && col >= 0) {
            int num = matrix[row][col];
            if (num <= midVal) {
                count += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return count;
    }
}
