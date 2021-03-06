package other;

import org.junit.Test;

/**
 * 529. 扫雷游戏
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月20日
 */
public class Minesweeper {

    /*
        给定一个代表游戏板的二维字符矩阵。
         'M' 代表一个未挖出的地雷，
         'E' 代表一个未挖出的空方块，
         'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，
         'X' 则表示一个已挖出的地雷。

        现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：

        如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
        如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
        如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
        如果在此次点击中，若无更多方块可被揭露，则返回面板。
         
        示例 1：
            输入:
                [['E', 'E', 'E', 'E', 'E'],
                 ['E', 'E', 'M', 'E', 'E'],
                 ['E', 'E', 'E', 'E', 'E'],
                 ['E', 'E', 'E', 'E', 'E']]
            Click : [3,0]
            输出:

                [['B', '1', 'E', '1', 'B'],
                 ['B', '1', 'M', '1', 'B'],
                 ['B', '1', '1', '1', 'B'],
                 ['B', 'B', 'B', 'B', 'B']]

        示例 2：
            输入:
                [['B', '1', 'E', '1', 'B'],
                 ['B', '1', 'M', '1', 'B'],
                 ['B', '1', '1', '1', 'B'],
                 ['B', 'B', 'B', 'B', 'B']]
            Click : [1,2]
            输出:
                [['B', '1', 'E', '1', 'B'],
                 ['B', '1', 'X', '1', 'B'],
                 ['B', '1', '1', '1', 'B'],
                 ['B', 'B', 'B', 'B', 'B']]


        注意：
            输入矩阵的宽和高的范围为 [1,50]。
            点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
            输入面板不会是游戏结束的状态（即有地雷已被挖出）。
            简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
     */

    @Test
    public void test(){
        char[][] board =
                {{'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}};
        int[] click = {3,0};
        char[][] updateBoard = updateBoard(board, click);
        for (char[] chars : updateBoard) {
            System.out.println(chars);
        }
    }

    @Test
    public void test2(){
        char[][] board =
                {{'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}};
        int[] click = {1,2};
        char[][] updateBoard = updateBoard(board, click);
        for (char[] chars : updateBoard) {
            System.out.println(chars);
        }
    }

    @Test
    public void test3(){
        char[][] board =
                {{'B'}};
        int[] click = {0,0};
        char[][] updateBoard = updateBoard(board, click);
        for (char[] chars : updateBoard) {
            System.out.println(chars);
        }
    }

    /**
     * 递归更新：
     *  1. 点位为炸弹（更新为X）
     *  2. 点位周围无炸弹，更新当前为B，并递归周围点位
     *  3. 点位周围有炸弹，更新当前为周围炸弹数，不再递归
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        } else {
            boolean[][] check = new boolean[board.length][board[0].length];
            checkBoard(board, check, click[0], click[1]);
        }
        return board;
    }

    int[][] around = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    private void checkBoard(char[][] board, boolean[][] check, int row, int col) {
        check[row][col] = true;
        int count = 0;
        for (int[] to : around) {
            int newRow = row + to[0];
            int newCol = col + to[1];
            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && !check[newRow][newCol]) {
                if (board[newRow][newCol] == 'M') {
                    count++;
                }
            }
        }
        if (count > 0) {
            board[row][col] = (char) (count + '0');
        } else {
            board[row][col] = 'B';
            for (int[] to : around) {
                int newRow = row + to[0];
                int newCol = col + to[1];
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length && !check[newRow][newCol]) {
                    checkBoard(board, check, newRow, newCol);
                }
            }
        }
    }
}
