package other;

import org.junit.Test;

import java.util.Arrays;

/**
 * 130. 被围绕的区域
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月11日
 */
public class SurroundedRegions {

    /*
        给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
        找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

        示例:
            X X X X
            X O O X
            X X O X
            X O X X
        运行你的函数后，矩阵变为：
            X X X X
            X X X X
            X X X X
            X O X X
        解释:
            被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
            任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     */


    @Test
    public void test() {
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    @Test
    public void test2() {
        char[][] board = {
                {'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    @Test
    public void test3() {
        char[][] board = {
                {'X'}};
        solve(board);
        System.out.println(Arrays.deepToString(board));
    }

    /**
     * 深度优先
     */
    public void solve(char[][] board) {
        if (board.length < 3 || board[0].length < 3) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] checkMatrix = new boolean[m][n];

        // 边缘遍历，先标记所有与边缘相连的O
        for (int i = 0; i < m; i += m - 1) {
            for (int j = 0; j < n; j++) {
                checkBorder(checkMatrix, board, i, j);
            }
        }
        for (int j = 0; j < n; j += n - 1) {
            for (int i = 0; i < m; i++) {
                checkBorder(checkMatrix, board, i, j);
            }
        }

        // 遍历中间
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!checkMatrix[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void checkBorder(boolean[][] checkMatrix, char[][] board, int row, int col) {
        if (board[row][col] == 'O') {
            checkMatrix[row][col] = true;
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow >= 0 && newRow < board.length - 1 && newCol >= 0 && newCol < board[0].length - 1 && !checkMatrix[newRow][newCol]) {
                    checkBorder(checkMatrix, board, newRow, newCol);
                }
            }
        }
    }
}
