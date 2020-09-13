package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 79. 单词搜索
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月13日
 */
public class WordSearch {

    /*
        给定一个二维网格和一个单词，找出该单词是否存在于网格中。
        单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

        示例:
            board =
            [
              ['A','B','C','E'],
              ['S','F','C','S'],
              ['A','D','E','E']
            ]

            给定 word = "ABCCED", 返回 true
            给定 word = "SEE", 返回 true
            给定 word = "ABCB", 返回 false

        提示：
            board 和 word 中只包含大写和小写英文字母。
            1 <= board.length <= 200
            1 <= board[i].length <= 200
            1 <= word.length <= 10^3
     */

    @Test
    public void test() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        Assert.assertEquals(true, exist(board, "ABCCED"));
    }

    @Test
    public void test2() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        Assert.assertEquals(true, exist(board, "SEE"));
    }

    @Test
    public void test3() {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        Assert.assertEquals(false, exist(board, "ABCB"));
    }

    @Test
    public void test4() {
        char[][] board = {{'A', 'B'}};
        Assert.assertEquals(true, exist(board, "BA"));
    }

    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                visited[i][j] = true;
                if (check(board, visited, chars, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean check(char[][] board, boolean[][] visited, char[] chars, int index, int row, int col) {
        visited[row][col] = true;
        if (chars[index] == board[row][col]) {
            if (index == chars.length - 1) {
                return true;
            }
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length &&
                        !visited[newRow][newCol] && check(board, visited, chars, index + 1, newRow, newCol)) {
                    return true;
                }
            }
        }
        visited[row][col] = false;
        return false;
    }
}
