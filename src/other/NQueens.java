package other;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/29
 */
public class NQueens {

    /*n 
    皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击（皇后所在的横、竖、斜向上都会被攻击）。
    给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
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
    解释: 4 皇后问题存在两个不同的解法。
    */

    @Test
    public void test() {
        List<List<String>> results = solveNQueens2(4);
        for (List<String> result : results) {
            for (String s : result) {
                System.out.println(s);
            }
            System.out.println("---------");
        }
    }

    @Test
    public void test2() {
        System.out.println(Integer.toBinaryString(13));
        System.out.println(Integer.toBinaryString(1100));
    }

    /**
     * 递归+剪枝
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 从第0层开始，
        boolean[] colFilter = new boolean[n];
        boolean[] leftFilter = new boolean[n * 2 - 1];
        boolean[] rightFilter = new boolean[n * 2 - 1];
        for (int i = 0; i < n; i++) {
            deep(n, 0, i, colFilter, leftFilter, rightFilter, result, new int[n]);
        }
        return result;
    }

    /**
     * 向下递归
     * @param n           总长度
     * @param row         行
     * @param col         列
     * @param colFilter   竖向过滤
     * @param leftFilter  左斜过滤
     * @param rightFilter 右斜过滤
     * @param result      结果集
     */
    private void deep(int n, int row, int col, boolean[] colFilter, boolean[] leftFilter, boolean[] rightFilter, List<List<String>> result, int[] queens) {
        // 纵轴过滤
        if (colFilter[col]) {
            return;
        }
        // 左斜过滤
        if (leftFilter[row + col]) {
            return;
        }
        // 右斜过滤
        if (rightFilter[row - col + n - 1]) {
            return;
        }

        // 生成此行
        queens[row] = col;

        // 最后一行，添加至结果集
        if (row >= n - 1) {
            result.add(getString(queens));
            return;
        }

        // 递归至下一层
        colFilter[col] = true;
        leftFilter[row + col] = true;
        rightFilter[row - col + n - 1] = true;
        for (int i = 0; i < n; i++) {
            deep(n, row + 1, i, colFilter, leftFilter, rightFilter, result, queens);
        }
        colFilter[col] = false;
        leftFilter[row + col] = false;
        rightFilter[row - col + n - 1] = false;
    }

    private List<String> getString(int[] queens) {
        List<String> lines = new ArrayList<>();
        for (int i : queens) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < queens.length; j++) {
                if (i == j) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            lines.add(sb.toString());
        }
        return lines;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * 递归+剪枝（位运算）
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        // 从第0层开始，
        deep(n, 0, 0, 0, 0, result, new int[n]);
        return result;
    }

    /**
     * 向下递归
     * @param n      总长度
     * @param row    行
     * @param col    列过滤
     * @param left   左斜过滤
     * @param right  右斜过滤
     * @param result 结果集
     */
    private void deep(int n, int row, int col, int left, int right, List<List<String>> result, int[] queens) {
        if (row == n) {
            result.add(getString(queens));
            return;
        }
        int availablePos = ((1 << n) - 1) & (~(col | left | right));
        while (availablePos != 0) {
            // 获取最后一个1的位置
            int pos = availablePos & -availablePos;
            // 把最后一个1的位置置为0
            availablePos &= (availablePos - 1);
            // 获取此位置的列索引（将pos中的1之后所有的0都变成1，从后往前对1计数）
            queens[row] = Integer.bitCount(pos - 1);
            deep(n, row + 1, col | pos, (left | pos) >> 1, (right | pos) << 1, result, queens);
        }
    }
}
