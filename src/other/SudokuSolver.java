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
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] blockCheck = new boolean[9][9];
        // 先读取所有已有数字
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int num = c - '0';
                    rowCheck[i][num - 1] = true;
                    colCheck[j][num - 1] = true;
                    blockCheck[i / 3 * 3 + j / 3][num - 1] = true;
                }
            }
        }
        solve(board, rowCheck, colCheck, blockCheck, 0, 0);
    }

    private boolean solve(char[][] board, boolean[][] rowCheck, boolean[][] colCheck, boolean[][] blockCheck, int row, int col) {
        // 超出此行范围就向下换一行
        if (col == board.length) {
            row++;
            col = 0;
            if (row == board.length) {
                return true;
            }
        }
        if (board[row][col] == '.') {
            for (int i = 1; i <= 9; i++) {
                if (isValid(rowCheck, colCheck, blockCheck, i, row, col)) {
                    board[row][col] = (char) (i + '0');
                    rowCheck[row][i - 1] = true;
                    colCheck[col][i - 1] = true;
                    blockCheck[row / 3 * 3 + col / 3][i - 1] = true;
                    if (solve(board, rowCheck, colCheck, blockCheck, row, col + 1)) {
                        return true;
                    } else {
                        board[row][col] = '.';
                        rowCheck[row][i - 1] = false;
                        colCheck[col][i - 1] = false;
                        blockCheck[row / 3 * 3 + col / 3][i - 1] = false;
                    }
                }
            }
            return false;
        }
        return solve(board, rowCheck, colCheck, blockCheck, row, col + 1);
    }

    private boolean isValid(boolean[][] rowCheck, boolean[][] colCheck, boolean[][] blockCheck, int num, int row, int col) {
        return !rowCheck[row][num - 1] && !colCheck[col][num - 1] && !blockCheck[row / 3 * 3 + col / 3][num - 1];
    }
}
