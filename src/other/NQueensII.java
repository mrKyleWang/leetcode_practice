package other;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 52. N皇后 II
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/29
 */
public class NQueensII {

    /*n 
    皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击（皇后所在的横、竖、斜向上都会被攻击）。
    给定一个整数 n，返回 n 皇后不同的解决方案的数量。
    每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
    示例:
    输入: 4
    输出: [
            [".Q..",  // 解法 1
            "...Q",
            "Q...",
            "..Q."],

            ["..Q.",  // 解法 2
            "Q...",
            "...Q",
            ".Q.."]
            ]
    */

    @Test
    public void test() {
        Assert.assertEquals(2, totalNQueens(4));
    }

    int result = 0;

    /**
     * 递归+剪枝
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        // 从第0层开始，
        int row = 0;
        for (int i = 0; i < n; i++) {
            addResult(n, row, i, new HashSet<>(), new HashSet<>(), new HashSet<>());
        }
        return result;
    }

    /**
     * 向下递归
     * @param n                  总长度
     * @param row                行
     * @param col                列
     * @param colFilter          竖向过滤
     * @param leftObliqueFilter  左斜过滤
     * @param rightObliqueFilter 右斜过滤
     */
    private void addResult(int n, int row, int col, Set<Integer> colFilter, Set<Integer> leftObliqueFilter, Set<Integer> rightObliqueFilter) {
        // 纵轴过滤
        if (colFilter.contains(col)) {
            return;
        }
        // 左斜过滤
        if (leftObliqueFilter.contains(row + col)) {
            return;
        }
        // 右斜过滤
        if (rightObliqueFilter.contains(row - col)) {
            return;
        }

        if (row >= n - 1) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            Set<Integer> newColFilter = new HashSet<>(colFilter);
            newColFilter.add(col);
            Set<Integer> newLeftObliqueFilter = new HashSet<>(leftObliqueFilter);
            newLeftObliqueFilter.add(row + col);
            Set<Integer> newRightObliqueFilter = new HashSet<>(rightObliqueFilter);
            newRightObliqueFilter.add(row - col);
            addResult(n, row + 1, i, newColFilter, newLeftObliqueFilter, newRightObliqueFilter);
        }
    }
}
