package map;

import org.junit.Assert;
import org.junit.Test;

/**
 * 959. 由斜杠划分区域
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月25日
 */
public class RegionsCutBySlashes {

    /*
        在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
        （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
        返回区域的数目。

        示例 1：
            输入：
            [
              " /",
              "/ "
            ]
            输出：2

        示例 2：
            输入：
            [
              " /",
              "  "
            ]
            输出：1
        示例 3：
            输入：
            [
              "\\/",
              "/\\"
            ]
            输出：4

        示例 4：
            输入：
            [
              "/\\",
              "\\/"
            ]
            输出：5

        示例 5：
            输入：
            [
              "//",
              "/ "
            ]
            输出：3

        提示：
            1 <= grid.length == grid[0].length <= 30
            grid[i][j] 是 '/'、'\'、或 ' '。
     */

    @Test
    public void test() {
        String[] grid = {" /", "/ "};
        Assert.assertEquals(2, regionsBySlashes(grid));
    }

    @Test
    public void test2() {
        String[] grid = {" /", "  "};
        Assert.assertEquals(1, regionsBySlashes(grid));
    }

    @Test
    public void test3() {
        String[] grid = {"\\/", "/\\"};
        Assert.assertEquals(4, regionsBySlashes(grid));
    }

    @Test
    public void test4() {
        String[] grid = {"/\\", "\\/"};
        Assert.assertEquals(5, regionsBySlashes(grid));
    }

    @Test
    public void test5() {
        String[] grid = {"//", "/ "};
        Assert.assertEquals(3, regionsBySlashes(grid));
    }

    /**
     * 将 mxm的矩阵分为(m+1)*(m+1)个节点：
     *  0  1  2
     *  3  4  5
     *  6  7  8
     */
    public int regionsBySlashes(String[] grid) {
        int m = grid.length;
        int[] parent = new int[(m + 1) * (m + 1)];
        // 先将中间部分都置为未连接
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < m; j++) {
                int index = i * (m + 1) + j;
                parent[index] = index;
            }
        }

        int res = 1;
        for (int i = 0; i < m; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < m; j++) {
                char c = chars[j];
                if (c == '/') {
                    // 左下连右上
                    int ru = i * (m + 1) + j + 1;
                    int ld = i * (m + 1) + j + m + 1;
                    if (find(parent, ru) != find(parent, ld)) {
                        union(parent, ru, ld);
                    } else {
                        res++;
                    }
                } else if (c == '\\') {
                    // 左上连右下
                    int lu = i * (m + 1) + j;
                    int rd = i * (m + 1) + j + m + 2;
                    if (find(parent, lu) != find(parent, rd)) {
                        union(parent, lu, rd);
                    } else {
                        res++;
                    }
                }
            }
        }
        return res;
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
