package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 714. 买卖股票的最佳时机含手续费
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月17日
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /*
        给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
        你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
        返回获得利润的最大值。
        注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

        示例 1:
            输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
            输出: 8
            解释: 能够达到的最大利润:
            在此处买入 prices[0] = 1
            在此处卖出 prices[3] = 8
            在此处买入 prices[4] = 4
            在此处卖出 prices[5] = 9
            总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
        注意:
            0 < prices.length <= 50000.
            0 < prices[i] < 50000.
            0 <= fee < 50000.
     */

    @Test
    public void test() {
        int[] prices = {1, 3, 2, 8, 4, 9};
        Assert.assertEquals(8, maxProfit(prices, 2));
    }

    @Test
    public void test2() {
        int[] prices = {1};
        Assert.assertEquals(0, maxProfit(prices, 2));
    }

    /**
     * dp[i] 保存第i天，买入、卖出、持有不操作、未持有不操作 的利润
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][4];
        dp[0][0] = -prices[0];
        dp[0][1] = Integer.MIN_VALUE / 2;
        dp[0][2] = Integer.MIN_VALUE / 2;
        dp[0][3] = 0;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][3]) - prices[i];
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + prices[i] - fee;
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][1], dp[i - 1][3]);
        }
        return Math.max(dp[n - 1][1], dp[n - 1][3]);
    }
}
