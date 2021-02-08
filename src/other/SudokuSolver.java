package other;

import org.junit.Test;

import java.util.Arrays;

/**
 * 37. 解数独
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/30
 */
public class SudokuSolver {


    @Test
    public void test() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public void solveSudoku(char[][] board) {
        int[][] places = new int[81][2];
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] blockCheck = new boolean[9][9];
        // 先读取所有已有数字
        int pos = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '1';
                    rowCheck[i][num] = colCheck[j][num] = blockCheck[i / 3 * 3 + j / 3][num] = true;
                }
                places[pos][0] = i;
                places[pos++][1] = j;
            }
        }
        dfs(board, 0, places, rowCheck, colCheck, blockCheck);

    }

    private boolean dfs(char[][] board, int pos, int[][] places, boolean[][] rowCheck, boolean[][] colCheck, boolean[][] blockCheck) {
        if (pos == 81) {
            return true;
        }
        int i = places[pos][0];
        int j = places[pos][1];
        if (board[i][j] == '.') {
            for (int num = 0; num < 9; num++) {
                if (rowCheck[i][num] || colCheck[j][num] || blockCheck[i / 3 * 3 + j / 3][num]) {
                    continue;
                }
                board[i][j] = (char) ('1' + num);
                rowCheck[i][num] = colCheck[j][num] = blockCheck[i / 3 * 3 + j / 3][num] = true;
                if (dfs(board, pos + 1, places, rowCheck, colCheck, blockCheck)) {
                    return true;
                }
                rowCheck[i][num] = colCheck[j][num] = blockCheck[i / 3 * 3 + j / 3][num] = false;
                board[i][j] = '.';
            }
            return false;
        }
        return dfs(board, pos + 1, places, rowCheck, colCheck, blockCheck);
    }
}
