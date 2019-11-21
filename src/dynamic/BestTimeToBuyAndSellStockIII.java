package dynamic;

/**
 * 123. 买卖股票的最佳时机 III
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月15日
 */
public class BestTimeToBuyAndSellStockIII {

    /*
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。

    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII obj = new BestTimeToBuyAndSellStockIII();
        int[] nums = {7, 1, 5, 3, 6, 4};
        int[] nums1 = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] nums2 = {1, 2, 3, 4, 5};
        int[] nums3 = {7, 6, 4, 3, 1};
        System.out.println(obj.maxProfit(nums));
        System.out.println(obj.maxProfit(nums1));
        System.out.println(obj.maxProfit(nums2));
        System.out.println(obj.maxProfit(nums3));
    }

    /**
     * 解法2：动态规划
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices != null && prices.length > 1) {
            // 保存某日在不同交易次数、不同持有股票状态下的最大利润
            int[][][] maxProfit = new int[prices.length][3][2];
            maxProfit[0][0][0] = 0;
            maxProfit[0][0][1] = -prices[0];

            for (int i = 1; i < 3; i++) {
                maxProfit[0][i][0] = Integer.MIN_VALUE;
                maxProfit[0][i][1] = Integer.MIN_VALUE;
            }

            for (int i = 1; i < prices.length; i++) {
                int cur = prices[i];
                // 3种交易状态：未交易、1一次交易、2次交易

                // 没有交易：a.不操作 b.买入
                maxProfit[i][0][0] = maxProfit[i - 1][0][0];
                maxProfit[i][0][1] = max(maxProfit[i - 1][0][1], (long) maxProfit[i - 1][0][0] - cur);

                // 交易[1,k-1]次
                maxProfit[i][1][0] = max(maxProfit[i - 1][1][0], maxProfit[i - 1][0][1] + cur);
                maxProfit[i][1][1] = max(maxProfit[i - 1][1][1], (long) maxProfit[i - 1][1][0] - cur);

                // 交易k次
                maxProfit[i][2][0] = max(maxProfit[i - 1][2][0], maxProfit[i - 1][1][1] + cur);
            }
            return max(0, maxProfit[prices.length - 1][0][0], maxProfit[prices.length - 1][1][0], maxProfit[prices.length - 1][2][0]);
        }
        return 0;
    }

    private int max(long... nums) {
        long max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        return (int) max;
    }
}
