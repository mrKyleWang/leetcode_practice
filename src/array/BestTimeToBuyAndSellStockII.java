package array;

/**
 * 买卖股票的最佳时机 II（122）
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class BestTimeToBuyAndSellStockII {

    /*
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        int floor = -1;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            int currentPrice = prices[i];
            if (floor != -1 && currentPrice > floor) {
                while (i + 1 < prices.length && prices[i + 1] >= currentPrice) {
                    i++;
                    currentPrice = prices[i];
                }
                profit += currentPrice - floor;
                floor = -1;
            } else {
                floor = currentPrice;
            }
        }
        return profit;
    }
}
