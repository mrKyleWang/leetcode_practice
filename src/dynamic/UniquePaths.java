package dynamic;

/**
 * 62. 不同路径
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/15
 */
public class UniquePaths {

    /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
        问总共有多少条不同的路径？
        说明：m 和 n 的值均不超过 100。
     */

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(7, 3));
    }

    public int uniquePaths(int m, int n) {
        // 从最后一步开始倒推
        int[][] temp = new int[m][n];
        temp[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int count;
                // 如果不靠边就将后续两步的可能性相加，否则只有一种路径
                if (i < m - 1 && j < n - 1) {
                    count = temp[i + 1][j] + temp[i][j + 1];
                } else {
                    count = 1;
                }
                temp[i][j] = count;
            }
        }
        return temp[0][0];
    }
}
