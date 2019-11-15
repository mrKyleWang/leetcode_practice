package dynamic;

/**
 * 63. 不同路径 II
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/15
 */
public class UniquePathsWithObstacles {

    /*
        一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
        机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
        现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
        网格中的障碍物和空位置分别用 1 和 0 来表示。
        说明：m 和 n 的值均不超过 100。
     */

    public static void main(String[] args) {
//        int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] grid = {{0, 0}, {1, 1}, {0, 0}};
//        int[][] grid = {{0, 1}};
        System.out.println(new UniquePathsWithObstacles().uniquePathsWithObstacles(grid));
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        obstacleGrid[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) {
                    continue;
                }
                int count = 0;
                if (obstacleGrid[i][j] == 0) {
                    if (i < m - 1) {
                        count += obstacleGrid[i + 1][j];
                    }
                    if (j < n - 1) {
                        count += obstacleGrid[i][j + 1];
                    }
                }
                obstacleGrid[i][j] = count;
            }
        }
        return obstacleGrid[0][0];
    }
}
