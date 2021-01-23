package map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1319. 连通网络的操作次数
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月23日
 */
public class NumberOfOperationsToMakeNetworkConnected {

    /*
    用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，
    其中 connections[i] = [a, b] 连接了计算机 a 和 b。
    网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
    给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
    请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 

     

    示例 1：
        输入：n = 4, connections = [[0,1],[0,2],[1,2]]
        输出：1
        解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
    示例 2：
        输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
        输出：2
    示例 3：
        输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
        输出：-1
        解释：线缆数量不足。
    示例 4：
        输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
        输出：0
         
    提示：
        1 <= n <= 10^5
        1 <= connections.length <= min(n*(n-1)/2, 10^5)
        connections[i].length == 2
        0 <= connections[i][0], connections[i][1] < n
        connections[i][0] != connections[i][1]
        没有重复的连接。
        两台计算机不会通过多条线缆连接。
     */

    @Test
    public void test() {
        int n = 2;
        int[][] connections = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        Assert.assertEquals(2, makeConnected(n, connections));
    }

    /**
     * 使用并查集保存已连接的计算机
     * 遍历所有线缆，记录两个数据：count为有效连接，extra为多余连接
     * 当线缆两端i,j不属于同一个集合，则此线缆为有效连接，count+1，然后union(i,j)
     * 如果i,j已属于同一个集合，则此线缆为多余连接，可留作后续移动操作，extra+1
     * <p>
     * 最后，由于连接n个计算机需要至少n-1条线缆，而当前已有count条连接，则需要移动n-1-count条多余线缆才能联通所有计算机
     */
    public int makeConnected(int n, int[][] connections) {
        int count = 0;
        int extra = 0;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] conn : connections) {
            int pI = find(parent, conn[0]);
            int pJ = find(parent, conn[1]);
            if (pI == pJ) {
                extra++;
            } else {
                union(parent, conn[0], conn[1]);
                count++;
            }
        }

        int op = n - 1 - count;
        if (extra >= op) {
            return op;
        }
        return -1;
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
}
