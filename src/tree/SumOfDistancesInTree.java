package tree;

import org.junit.Test;

import java.util.*;

/**
 * 834. 树中距离之和
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月09日
 */
public class SumOfDistancesInTree {

    /*
        给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
        第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
        返回一个表示节点 i 与其他所有节点距离之和的列表 ans。

        示例 1:
            输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
            输出: [8,12,6,10,10,10]
            解释:
                如下为给定的树的示意图：
                      0
                     / \
                    1   2
                       /|\
                      3 4 5
                我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
                也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
        说明: 1 <= N <= 10000
     */

    @Test
    public void test() {
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        System.out.println(Arrays.toString(sumOfDistancesInTree(6, edges)));
    }

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        result = new int[N];
        dp = new int[N];
        sz = new int[N];
        neighbors = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            neighbors.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            neighbors.get(edge[0]).add(edge[1]);
            neighbors.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return result;
    }

    List<Set<Integer>> neighbors;
    int[] dp;
    int[] sz;
    int[] result;

    private void dfs(int node, int pre) {
        sz[node] = 1;
        for (Integer neighbor : neighbors.get(node)) {
            if (neighbor == pre) {
                continue;
            }
            dfs(neighbor, node);
            dp[node] += dp[neighbor] + sz[neighbor];
            sz[node] += sz[neighbor];
        }
    }

    private void dfs2(int node, int pre) {
        result[node] = dp[node];
        for (Integer neighbor : neighbors.get(node)) {
            if (neighbor == pre) {
                continue;
            }
            int tempDpNode = dp[node];
            int tempDpNeighbor = dp[neighbor];
            int tempSzNode = sz[node];
            int tempSzNeighbor = sz[neighbor];

            dp[node] -= dp[neighbor] + sz[neighbor];
            sz[node] -= sz[neighbor];
            dp[neighbor] += dp[node] + sz[node];
            sz[neighbor] += sz[node];

            dfs2(neighbor, node);

            dp[node] = tempDpNode;
            dp[neighbor] = tempDpNeighbor;
            sz[node] = tempSzNode;
            sz[neighbor] = tempSzNeighbor;

        }
    }

}
