package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * LCP 13. 寻宝
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月29日
 */
public class DiscoveringTreasure {

    /*
        我们得到了一副藏宝图，藏宝图显示，在一个迷宫中存在着未被世人发现的宝藏。
        迷宫是一个二维矩阵，用一个字符串数组表示。它标识了唯一的入口（用 'S' 表示），和唯一的宝藏地点（用 'T' 表示）。
        但是，宝藏被一些隐蔽的机关保护了起来。在地图上有若干个机关点（用 'M' 表示），只有所有机关均被触发，才可以拿到宝藏。
        要保持机关的触发，需要把一个重石放在上面。迷宫中有若干个石堆（用 'O' 表示），每个石堆都有无限个足够触发机关的重石。但是由于石头太重，我们一次只能搬一个石头到指定地点。
        迷宫中同样有一些墙壁（用 '#' 表示），我们不能走入墙壁。剩余的都是可随意通行的点（用 '.' 表示）。石堆、机关、起点和终点（无论是否能拿到宝藏）也是可以通行的。
        我们每步可以选择向上/向下/向左/向右移动一格，并且不能移出迷宫。搬起石头和放下石头不算步数。那么，从起点开始，我们最少需要多少步才能最后拿到宝藏呢？如果无法拿到宝藏，返回 -1 。

        示例 1：
            输入： ["S#O", "M..", "M.T"]
            输出：16

        解释：最优路线为： S->O, cost = 4, 去搬石头 O->第二行的M, cost = 3, M机关触发 第二行的M->O, cost = 3,
        我们需要继续回去 O 搬石头。 O->第三行的M, cost = 4, 此时所有机关均触发 第三行的M->T, cost = 2，去T点拿宝藏。 总步数为16。

        限制：
            1 <= maze.length <= 100
            1 <= maze[i].length <= 100
            maze[i].length == maze[j].length
            S 和 T 有且只有一个
            0 <= M的数量 <= 16
            0 <= O的数量 <= 40，题目保证当迷宫中存在 M 时，一定存在至少一个 O 。

     */

    /**
     * 思路：
     * 整个过程是：S -> (O -> M -> O -> M ... -> M) -> T
     * <p>
     * 需要确定：S到每个O的最短距离，T到每个M的距离，以及每个O到每个M的最短距离，可以总结为：S到每个O的距离，M到所有点的距离
     * <p>
     * 先遍历，找到起点、终点、每个石头、每个机关的位置
     */

    @Test
    public void test() {
        String[] maze = {"S#O", "M.#", "M.T"};
        Assert.assertEquals(-1, minimalSteps(maze));
    }

    @Test
    public void test2() {
        String[] maze = {"S#O", "M.T", "M.."};
        Assert.assertEquals(17, minimalSteps(maze));
    }

    /*
        大概思路：具体的还请看代码里面的注释
        把点分为两种，一个列表放石头的，一个列表放机关的
        然后求出每个机关到每个石头的距离
        然后在求得每个机关到每个机关的距离
        然后就是逆天的游戏理解
        把一个数的二进制表示当前机关触发的状态
        这个数的二进制第i位如果为0就表示第i个机关还没有被触发，反之为1就表示被触发了
        然后再找出机关触发状态下，最短的距离
    */


    public int minimalSteps(String[] maze) {
        int n = maze.length;
        char[][] mat = new char[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = maze[i].toCharArray();
        }

        int m = mat[0].length;
        List<int[]> triggers = new ArrayList<>();
        List<int[]> stones = new ArrayList<>();
        int[] start = null;
        int[] end = null;
        // 把各个类型的点分开
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 'M') {
                    triggers.add(new int[]{i, j});
                }
                if (mat[i][j] == 'O') {
                    stones.add(new int[]{i, j});
                }
                if (mat[i][j] == 'S') {
                    start = new int[]{i, j};
                }
                if (mat[i][j] == 'T') {
                    end = new int[]{i, j};
                }
            }
        }
        // 把初始点加入机关队列，把终点加入石头队列
        triggers.add(start);
        stones.add(end);
        int T = triggers.size();
        int S = stones.size();

        // 保存 机关-石头 的最短距离
        int[][] dist = new int[T][S];
        // 方向
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // BFS（如果这里不懂得话，不建议先看这道题，先看一下BFS类型得题）
        int inf = (int) 1e8;
        Deque<int[]> dq = new ArrayDeque<>(n * m);
        // 记录已访问过的点（未访问为-1）
        int[][] access = new int[n][m];
        // 遍历机关，从每个机关出发，算出其到每个点的距离
        for (int i = 0; i < T; i++) {
            dq.clear();
            for (int[] a : access) {
                Arrays.fill(a, -1);
            }
            int[] t = triggers.get(i);
            access[t[0]][t[1]] = 0;
            dq.addLast(t);
            while (!dq.isEmpty()) {
                int[] head = dq.removeFirst();
                for (int[] dir : dirs) {
                    int x = head[0] + dir[0];
                    int y = head[1] + dir[1];
                    if (x < 0 || x >= n || y < 0 || y >= m || mat[x][y] == '#' || access[x][y] != -1) {
                        continue;
                    }
                    access[x][y] = access[head[0]][head[1]] + 1;
                    dq.addLast(new int[]{x, y});
                }
            }
            // dist[i][j]这里就是 第i个机关到第j个机关的最短距离
            for (int j = 0; j < S; j++) {
                int[] s = stones.get(j);
                if (access[s[0]][s[1]] == -1) {
                    dist[i][j] = inf;
                } else {
                    dist[i][j] = access[s[0]][s[1]];
                }
            }
        }
        // 循环所有的点，找到从一个机关经过石堆到另一个机关的最短距离
        int[][] move = new int[T][T];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                if (i == j) {
                    continue;
                }
                move[i][j] = inf;
                // 石堆的最后一个是终点，所以要-1
                for (int k = 0; k < S - 1; k++) {
                    // i到j的最短距离为：i到k石堆+j到k石堆
                    move[i][j] = Math.min(move[i][j], dist[i][k] + dist[j][k]);
                }
            }
        }
        // 初始化
        // mask的二进制中，第j位如果为0，证明第j个机关没有触发，比如有10个机关，求出mask=1023，二进制则为1111111111
        int mask = (1 << (T - 1)) - 1;
        int[][] dp = new int[T][mask + 1];
        for (int i = 0; i < T; i++) {
            dp[i][0] = inf;
        }
        // 这里运用二进制，i的第j位如果是0的话，证明第j个机关还没触发，反之，就是第j个机关触发了
        dp[T - 1][0] = 0;
        for (int i = 1; i <= mask; i++) {
            for (int j = 0; j < T; j++) {
                dp[j][i] = inf;
                // 这里相当于剪枝操作吧，如果都是i>>j的最后一位不能触发，就直接过吧
                // 既然有不能触发的机关，求出就没有意义
                if (bit(i, j) == 0) {
                    continue;
                }
                // 这里异运算，就是找没触发的
                // 也就是需要改变的状态
                int remove = i ^ (1 << j);
                for (int k = 0; k < T; k++) {
                    // 当前的j个机关最小值，就是k个机关的remove状态，然后加上k到j的路径
                    dp[j][i] = Math.min(dp[j][i], dp[k][remove] + move[k][j]);
                }
            }
        }

        int ans = inf;
        if (T > 1) {
            for (int i = 0; i < T - 1; i++) {
                // 找mask就是全都为1，证明全部机关触发
                // dist是上面求得最短距离，第i个机关到s-1的最短路径（到终点的最短路径）
                // 因为开始的时候，把初始点加入到了机关队列，把终点加入到了石头队列
                ans = Math.min(ans, dp[i][mask] + dist[i][S - 1]);
            }
        } else {
            ans = dist[0][S - 1];
        }

        if (ans >= inf) {
            return -1;
        }
        return ans;
    }

    int bit(int x, int i) {
        return (x >> i) & 1;
    }

    public static void main(String[] args) {

        int T = 3;
        int mask = (1<<T) -1;
        System.out.println(Integer.toBinaryString(mask));

        for (int i = 0; i < mask; i++) {
            System.out.println();

            for (int j = 0; j < T; j++) {
                int remove = i ^ (1 << j);
                System.out.println("j="+j+"  remove:"+Integer.toBinaryString(remove));
            }
        }
    }


}
