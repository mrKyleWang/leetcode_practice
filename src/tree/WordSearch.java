package tree;


/**
 * 79. 单词搜索
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/05
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
        给定 word = "ABCCED", 返回 true.
        给定 word = "SEE", 返回 true.
        给定 word = "ABCB", 返回 false.
     */

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        System.out.println(new WordSearch().exist(board, "ABCCED"));

    }

    static final int[] X_MOVE = {1, -1, 0, 0};
    static final int[] Y_MOVE = {0, 0, 1, -1};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, visited, i, j, "", word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] visited, int x, int y, String str, String word) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return false;
        }
        if (visited[x][y]) {
            return false;
        }
        str += board[x][y];
        if (!word.startsWith(str)) {
            return false;
        }
        if (word.equals(str)) {
            return true;
        }
        visited[x][y] = true;
        for (int i = 0; i < X_MOVE.length; i++) {
            if (dfs(board, visited, x + X_MOVE[i], y + Y_MOVE[i], str, word)) {
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

}
