package other;

import java.util.Arrays;

/**
 * 37. 解数独
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/30
 */
public class SudokuSolver {

    static boolean[][] rowCheck = new boolean[9][9];
    static boolean[][] colCheck = new boolean[9][9];
    static boolean[][] blockCheck = new boolean[9][9];
    static char[][] board;

    public static void main(String[] args) {
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        solveSudoku(board);
        for (char[] chars : board) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public static void solveSudoku(char[][] board) {
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
        SudokuSolver.board = board;
        solve(board.length, 0, 0);
    }

    private static boolean solve(int n, int row, int col) {
        // 超出此行范围就向下换一行
        if (col == n) {
            row++;
            col = 0;
            if (row == n) {
                return true;
            }
        }
        if (board[row][col] == '.') {
            for (int i = 1; i <= 9; i++) {
                if (isValid(i, row, col)) {
                    board[row][col] = (char) (i + '0');
                    rowCheck[row][i - 1] = true;
                    colCheck[col][i - 1] = true;
                    blockCheck[row / 3 * 3 + col / 3][i - 1] = true;
                    if (solve(n, row, col + 1)) {
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
        return solve(n, row, col + 1);
    }

    private static boolean isValid(int num, int row, int col) {
        return !rowCheck[row][num - 1] && !colCheck[col][num - 1] && !blockCheck[row / 3 * 3 + col / 3][num - 1];
    }
}
