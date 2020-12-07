package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 861. 翻转矩阵后的得分
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月07日
 */
public class ScoreAfterFlippingMatrix {

    /*
        有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
        移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
        在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
        返回尽可能高的分数。

        示例：
            输入：[[0,0,1,1],
                  [1,0,1,0],
                  [1,1,0,0]]
            输出：39
            解释：
            转换为 [[1,1,1,1],
                   [1,0,0,1],
                   [1,1,1,1]]
            0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
         
        提示：
            1 <= A.length <= 20
            1 <= A[0].length <= 20
            A[i][j] 是 0 或 1
     */

    @Test
    public void test() {
        int[][] A = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        Assert.assertEquals(39, matrixScore(A));
    }

    /**
     * 先通过行转换保证第一列都是1，然后依次往后，通过列转换保证每列的1的个数最多
     */
    public int matrixScore(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int[] row : A) {
            if (row[0] == 0) {
                for (int i = 0; i < n; i++) {
                    row[i] = row[i] ^ 1;
                }
            }
        }
        int sum = 0;
        int factor = 1;
        for (int j = n - 1; j > 0; j--) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][j] == 1) {
                    count++;
                }
            }
            sum += Math.max(count, m - count) * factor;
            factor *= 2;
        }
        sum += m * factor;
        return sum;
    }
}
