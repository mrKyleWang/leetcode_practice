package map;

import org.junit.Test;

import java.util.*;

/**
 * 1489. 找到最小生成树里的关键边和伪关键边
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月21日
 */
public class FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {

    /*
        给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，
        其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。
        最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
        请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。
        伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
        请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。

        示例 1：
            输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
            输出：[[0,1],[2,3,4,5]]
        示例 2 ：
            输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
            输出：[[],[0,1,2,3]]
            解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。

        提示：
            2 <= n <= 100
            1 <= edges.length <= min(200, n * (n - 1) / 2)
            edges[i].length == 3
            0 <= fromi < toi < n
            1 <= weighti <= 1000
            所有 (fromi, toi) 数对都是互不相同的。
     */

    @Test
    public void test() {
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {2, 3, 2}, {0, 3, 2}, {0, 4, 3}, {3, 4, 3}, {1, 4, 6}};
        System.out.println(findCriticalAndPseudoCriticalEdges(5, edges));
    }

    @Test
    public void test2() {
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {2, 3, 1}, {0, 3, 1}};
        System.out.println(findCriticalAndPseudoCriticalEdges(4, edges));
    }

    @Test
    public void test3() {
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {0, 2, 1}, {2, 3, 4}, {3, 4, 2}, {3, 5, 2}, {4, 5, 2}};
        System.out.println(findCriticalAndPseudoCriticalEdges(6, edges));
    }

    @Test
    public void test4() {
        int[][] edges = {{0, 1, 1}, {0, 3, 1}, {0, 2, 1}, {1, 2, 1}, {1, 3, 1}, {2, 3, 1}};
        System.out.println(findCriticalAndPseudoCriticalEdges(4, edges));
    }

    /**
     * 对边按权重排序，用并查集找到MST及总权重min
     * 找关键边：尝试移除边i并构建MST，如果总权重>min，则说明是关键边
     * 找伪关键边：尝试优先加入边i并构建MST，如果总权重==min，并且不为关键边，则说明是伪关键边
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // 记录原索引
        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            map.put(edges[i], i);
        }

        // 按权重排序
        Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

        // 生成MST（最小生成树）
        int minWeight = buildMST(edges, n, -1, -1);

        Set<Integer> keyEdge = new HashSet<>();
        List<Integer> fakeKeyEdge = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int weight = buildMST(edges, n, i, -1);
            if (weight > minWeight) {
                keyEdge.add(map.get(edges[i]));
            }
        }
        for (int i = 0; i < edges.length; i++) {
            int weight = buildMST(edges, n, -1, i);
            if (weight == minWeight && !keyEdge.contains(map.get(edges[i]))) {
                fakeKeyEdge.add(map.get(edges[i]));
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>(keyEdge));
        res.add(fakeKeyEdge);
        return res;
    }

    /**
     * 不选第i条边的前提下，生成MST
     */
    private int buildMST(int[][] edges, int n, int remove, int priority) {
        int[] parent = new int[n];
        for (int j = 0; j < n; j++) {
            parent[j] = j;
        }
        int count = 0;
        int sum = 0;

        if (priority >= 0) {
            int[] edge = edges[priority];
            union(parent, edge[0], edge[1]);
            count++;
            sum += edge[2];
        }

        for (int i = 0; i < edges.length; i++) {
            if (i != remove) {
                int[] edge = edges[i];
                int k1 = edge[0];
                int k2 = edge[1];
                int p1 = find(parent, k1);
                int p2 = find(parent, k2);
                if (p1 != p2) {
                    union(parent, k1, k2);
                    count++;
                    sum += edge[2];
                }
            }
        }
        if (count == n - 1) {
            return sum;
        }
        return Integer.MAX_VALUE;
    }

    private void union(int[] parent, int i, int j) {
        int pI = find(parent, i);
        int pJ = find(parent, j);
        if (pI != pJ) {
            parent[pI] = pJ;
        }
    }

    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = parent[find(parent, parent[i])];
        }
        return parent[i];
    }
}
