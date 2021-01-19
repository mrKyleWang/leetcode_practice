package map;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1584. 连接所有点的最小费用
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月19日
 */
public class MinCostConnectPoints {

    /*
        给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
        连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
        请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。

        示例 1：
            输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
            输出：20
        示例 2：
            输入：points = [[3,12],[-2,5],[-4,1]]
            输出：18
        示例 3：
            输入：points = [[0,0],[1,1],[1,0],[-1,1]]
            输出：4
        示例 4：
            输入：points = [[-1000000,-1000000],[1000000,1000000]]
            输出：4000000
        示例 5：
            输入：points = [[0,0]]
            输出：0

        提示：
            1 <= points.length <= 1000
            -106 <= xi, yi <= 106
            所有点 (xi, yi) 两两不同。
     */

    @Test
    public void test() {
        Assert.assertEquals(20, minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    /**
     * 将所有边的距离edges算出来，按升序排列
     * 如果需要将n个点连起来，则需要n-1条边
     * 使用并查集判断点的集合
     * 遍历edges，如果此边连接了两个不在同一集合的点，则此边则是当前连接此两点的最短距离，加到结果后，将此两点合并集
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[] parent = new int[n];
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(points, i, j));
            }
        }
        edges.sort((o1, o2) -> o1.dis - o2.dis);

        int sum = 0;
        int edgeCount = 0;
        for (Edge edge : edges) {
            int pA = find(parent, edge.a);
            int pB = find(parent, edge.b);
            if (pA != pB) {
                union(parent, edge.a, edge.b);
                edgeCount++;
                sum += edge.dis;
            }
            if (edgeCount == n - 1) {
                return sum;
            }
        }
        return sum;
    }

    private void union(int[] parent, int i, int j) {
        int pI = find(parent, i);
        int pJ = find(parent, j);
        if (pI != pJ) {
            parent[pJ] = pI;
        }
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]);
        }
        return parent[i];
    }

    class Edge {
        int a;
        int b;
        int dis;

        public Edge(int[][] points, int a, int b) {
            this.a = a;
            this.b = b;
            this.dis = Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
        }
    }
}
