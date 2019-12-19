package dynamic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/19
 */
public class PerfectSquares {

    /*
        给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
        示例 1:
            输入: n = 12
            输出: 3
            解释: 12 = 4 + 4 + 4.
        示例 2:
            输入: n = 13
            输出: 2
            解释: 13 = 4 + 9.
     */

    @Test
    public void test() {
        System.out.println(numSquares(15));
    }

    /**
     * 动态规划：使用数组保存到每一个值时的最小个数
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] tempMin = new int[n];
        List<Integer> list = new ArrayList<>();
        int x = 1;
        while (x * x <= n) {
            list.add(x * x);
            x++;
        }
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (Integer j : list) {
                int diff = (i + 1) - j;
                if (diff == 0) {
                    min = 1;
                } else if (diff > 0) {
                    min = Math.min(min, tempMin[diff - 1] + 1);
                }
            }
            tempMin[i] = min;
        }
        return tempMin[n - 1];
    }
}
