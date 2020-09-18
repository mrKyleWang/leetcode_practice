package tree;

import org.junit.Test;

import java.util.Arrays;

/**
 * 685. 冗余连接 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月17日
 */
public class RedundantConnectionII {


    /*
        在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
        输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
        结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
        返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。

        示例 1:

        输入: [[1,2], [1,3], [2,3]]
        输出: [2,3]
        解释: 给定的有向图如下:
          1
         / \
        v   v
        2-->3
        示例 2:

        输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
        输出: [4,1]
        解释: 给定的有向图如下:
        5 <- 1 -> 2
             ^    |
             |    v
             4 <- 3
        注意:

        二维数组大小的在3到1000范围内。
        二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
     */


    @Test
    public void test() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }

    @Test
    public void test2() {
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }

    @Test
    public void test3() {
        int[][] edges = {{1, 2}, {1, 3}, {3, 4}, {4, 5}, {4, 2}};
        System.out.println(Arrays.toString(findRedundantDirectedConnection(edges)));
    }

    /**
     * 并查集，保存祖先
     */
    int[] ancestor;
    /**
     * 保存父节点
     */
    int[] parent;

    /**
     * 冗余边会导致两种情况：
     * 1. 指向非根节点：此节点会有两个父节点
     * 2. 指向根节点：图中存在环
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] lastCircleEdge = null;
        int[] edge1 = null;
        int[] edge2 = null;
        ancestor = new int[edges.length + 1];
        for (int i = 0; i < ancestor.length; i++) {
            ancestor[i] = i;
        }
        parent = new int[edges.length + 1];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (parent[v] != 0) {
                // v已经有一个父节点
                edge1 = new int[]{parent[v], v};
                edge2 = edge;
            } else {
                parent[v] = u;

                int ancU = find(u);
                int ancV = find(v);
                System.out.println(String.format("u:%s,ancU:%s,v:%s,ancV:%s",u,ancU,v,ancV));
                if (ancU != ancV) {
                    ancestor[ancV] = ancU;
                } else {
                    // 碰到环
                    lastCircleEdge = edge;
                }
            }
        }
        if (edge1 != null) {
            return lastCircleEdge == null ? edge2 : edge1;
        }
        return lastCircleEdge;
    }

    private int find(int node) {
        if (ancestor[node] != node) {
            ancestor[node] = find(this.ancestor[node]);
        }
        return ancestor[node];
    }


}
