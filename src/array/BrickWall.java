package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 554. 砖墙
 * @author KyleWang
 * @version 1.0
 * @date 2021年05月02日
 */
public class BrickWall {

    /*
    你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
    你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
    你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。

    给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。
    你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
     */

    @Test
    public void test() {
        List<List<Integer>> wall = Arrays.asList(Arrays.asList(1, 2, 2, 1), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 2), Arrays.asList(2, 4), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 1, 1));
        Assert.assertEquals(2, leastBricks(wall));
    }

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int pos = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                pos += list.get(i);
                map.compute(pos, (k, v) -> v == null ? 1 : v + 1);
            }
        }

        int n = wall.size();
        int res = n;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            res = Math.min(res, n - entry.getValue());
        }
        return res;
    }
}
