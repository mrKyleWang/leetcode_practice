package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月10日
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    /*
        给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
        设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
        你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
        卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。

        示例:
            输入: [1,2,3,0,2]
            输出: 3
            解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     */

    @Test
    public void test() {
        int maxProfit = maxProfit(new int[]{1, 2, 3, 0, 2});
        Assert.assertEquals(3, maxProfit);
    }

    @Test
    public void test2() {
        int maxProfit = maxProfit(new int[]{1, 2, 4});
        Assert.assertEquals(3, maxProfit);
    }

    /**
     * 动态规划，保存每天 [不持股/持股] [不操作/买/卖]时的 最大利润
     */
    public int maxProfit(int[] prices) {
        int[][][] dp = new int[prices.length + 1][2][3];
        dp[0][0][2] = Integer.MIN_VALUE;
        dp[0][1][0] = Integer.MIN_VALUE;
        dp[0][1][1] = Integer.MIN_VALUE;
        dp[0][1][2] = Integer.MIN_VALUE;

        for (int i = 1; i <= prices.length; i++) {
            int price = prices[i - 1];

            // 今天结束后，未持有股票
            // 未操作：前一天 未操作/卖出 未持股
            dp[i][0][0] = Math.max(dp[i - 1][0][0], dp[i - 1][0][2]);
            // 卖出：前一天 未操作/买入 导致持有股票
            dp[i][0][2] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1]) + price;

            // 今天结束后，持有股票
            // 未操作：前一天 未操作/买入 持股
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1]);
            // 买入：前一天 未操作 导致未持有股票
            dp[i][1][1] = dp[i - 1][0][0] - price;

            System.out.println(String.format("第%s天结束：买入：%s，卖出：%s，无股票未操作：%s，持股未操作：%s", i, dp[i][1][1], dp[i][0][2], dp[i][0][0], dp[i][1][0]));
        }
        int max = 0;
        for (int profit : dp[prices.length][0]) {
            if (profit > max) {
                max = profit;
            }
        }
        return max;
    }
}
