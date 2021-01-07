package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 547. 省份数量
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月07日
 */
public class NumberOfProvinces {

    /*
        有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
        省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
        给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
        返回矩阵中 省份 的数量。

        示例 1：
            输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
            输出：2
        示例 2：
            输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
            输出：3

        提示：
            1 <= n <= 200
            n == isConnected.length
            n == isConnected[i].length
            isConnected[i][j] 为 1 或 0
            isConnected[i][i] == 1
            isConnected[i][j] == isConnected[j][i]
     */

    @Test
    public void test() {
        int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        Assert.assertEquals(2, findCircleNum(M));
    }

    @Test
    public void test2() {
        int[][] M = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        Assert.assertEquals(3, findCircleNum(M));
    }

    @Test
    public void test3() {
        int[][] M = {{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}};
        Assert.assertEquals(3, findCircleNum(M));
    }

    /**
     * 并查集
     */
    public int findCircleNum(int[][] M) {
        int count = 0;
        int n = M.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        return count;
    }

    private void union(int[] parent, int i, int j) {
        parent[find(parent, j)] = find(parent, i);
    }

    private int find(int[] parent, int index) {
        if (parent[index] != index) {
            return find(parent, parent[index]);
        }
        return index;
    }

    public int findCircleNum2(int[][] M) {
        int n = M.length;
        int[] temp = new int[n];
        Map<Integer, Set<Integer>> sets = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (M[i][j] == 1) {
                    System.out.println(i + "-" + j);
                    if (temp[i] != temp[j]) {
                        int mergeGroup = temp[i];
                        int follow = j;
                        if (temp[i] < temp[j]) {
                            mergeGroup = temp[j];
                            follow = i;
                        }
                        int removeGroup = temp[follow];
                        Set<Integer> set = sets.get(mergeGroup);
                        if (sets.containsKey(temp[follow])) {
                            for (Integer index : sets.get(temp[follow])) {
                                set.add(index);
                                temp[index] = mergeGroup;
                            }
                            sets.remove(removeGroup);
                        } else {
                            set.add(follow);
                        }
                        temp[follow] = mergeGroup;
                    } else if (temp[i] == 0) {
                        int group = i + 1;
                        Set<Integer> set = sets.computeIfAbsent(group, k -> new HashSet<>());
                        set.add(i);
                        set.add(j);
                        temp[i] = group;
                        temp[j] = group;
                    }
                }
            }
        }
        return sets.size();
    }


}
