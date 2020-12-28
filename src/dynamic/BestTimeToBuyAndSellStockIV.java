package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月28日
 */
public class BestTimeToBuyAndSellStockIV {

    /*
        给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
        设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
        注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

        示例 1：
            输入：k = 2, prices = [2,4,1]
            输出：2
            解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
        示例 2：
            输入：k = 2, prices = [3,2,6,5,0,3]
            输出：7
            解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
                 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
         
        提示：
            0 <= k <= 109
            0 <= prices.length <= 1000
            0 <= prices[i] <= 1000
     */

    @Test
    public void test() {
        int k = 2;
        int[] prices = {2, 4, 1};
        Assert.assertEquals(2, maxProfit(k, prices));
    }

    @Test
    public void test2() {
        int k = 2;
        int[] prices = {3, 2, 6, 5, 0, 3};
        Assert.assertEquals(7, maxProfit(k, prices));
    }

    @Test
    public void test3() {
        int k = 2;
        int[] prices = {1, 2, 4, 7};
        Assert.assertEquals(6, maxProfit(k, prices));
    }

    /**
     * int[i][j][0/1] 保存第i天，并已完成第k笔交易，持有/未持有股票的利润
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2 || k <= 0) {
            return 0;
        }
        int[][][] dp = new int[n][k + 1][2];
        dp[0][0][1] = -prices[0];
        dp[0][1] = new int[]{Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2};
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k && j <= (i + 1) / 2; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);

                // 未持有： 无操作 / 卖出(j+1)
                if (j > 0) {
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j - 1][1] + prices[i]);
                } else {
                    dp[i][j][0] = dp[i - 1][j][0];
                }
                // 持有：无操作 / 买入
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j][0] - prices[i]);
            }
        }
        int max = 0;
        for (int i = 0; i <= k; i++) {
            max = Math.max(max, dp[n - 1][i][0]);
        }
        return max;
    }
}
