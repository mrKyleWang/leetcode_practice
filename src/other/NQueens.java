package other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void main(String[] args) {
        List<List<String>> results = solveNQueens(4);
        for (List<String> result : results) {
            for (String s : result) {
                System.out.println(s);
            }
            System.out.println("---------");
        }
    }

    /**
     * 递归+剪枝
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 从第0层开始，
        int parallel = 0;
        for (int i = 0; i < n; i++) {
            addResult(n, parallel, i, new HashSet<>(), new HashSet<>(), new HashSet<>(), new ArrayList<>(), result);
        }
        return result;
    }

    /**
     * 向下递归
     * @param n                  总长度
     * @param parallel           横向位置
     * @param vertical           竖向位置
     * @param verticalFilter     竖向过滤
     * @param leftObliqueFilter  左斜过滤
     * @param rightObliqueFilter 右斜过滤
     * @param result             结果集
     */
    private static void addResult(int n, int parallel, int vertical, Set<Integer> verticalFilter, Set<Integer> leftObliqueFilter, Set<Integer> rightObliqueFilter, List<String> tempResult, List<List<String>> result) {
        // 纵轴过滤
        if (verticalFilter.contains(vertical)) {
            return;
        }
        // 左斜过滤
        if (leftObliqueFilter.contains(parallel + vertical)) {
            return;
        }
        // 右斜过滤
        if (rightObliqueFilter.contains(parallel - vertical)) {
            return;
        }

        // 生成此行
        tempResult.add(getString(n, vertical));

        // 最后一行，添加至结果集
        if (parallel >= n - 1) {
            result.add(tempResult);
            return;
        }

        // 递归至下一层
        for (int i = 0; i < n; i++) {
            Set<Integer> newVerticalFilter = new HashSet<>(verticalFilter);
            newVerticalFilter.add(vertical);
            Set<Integer> newLeftObliqueFilter = new HashSet<>(leftObliqueFilter);
            newLeftObliqueFilter.add(parallel + vertical);
            Set<Integer> newRightObliqueFilter = new HashSet<>(rightObliqueFilter);
            newRightObliqueFilter.add(parallel - vertical);
            List<String> newTempResult = new ArrayList<>(tempResult);
            addResult(n, parallel + 1, i, newVerticalFilter, newLeftObliqueFilter, newRightObliqueFilter, newTempResult, result);
        }
    }

    private static String getString(int n, int vertical) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == vertical) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }
        return sb.toString();
    }
}
