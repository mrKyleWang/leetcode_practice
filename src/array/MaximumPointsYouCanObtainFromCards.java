package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1423. 可获得的最大点数
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月06日
 */
public class MaximumPointsYouCanObtainFromCards {

    /*
        几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
        每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
        你的点数就是你拿到手中的所有卡牌的点数之和。
        给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。

        示例 1：
            输入：cardPoints = [1,2,3,4,5,6,1], k = 3
            输出：12
            解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
        示例 2：
            输入：cardPoints = [2,2,2], k = 2
            输出：4
            解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
        示例 3：
            输入：cardPoints = [9,7,7,9,7,7,9], k = 7
            输出：55
            解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
        示例 4：
            输入：cardPoints = [1,1000,1], k = 1
            输出：1
            解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
        示例 5：
            输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
            输出：202
        提示：
            1 <= cardPoints.length <= 10^5
            1 <= cardPoints[i] <= 10^4
            1 <= k <= cardPoints.length
     */

    @Test
    public void test() {
        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        Assert.assertEquals(12, maxScore(cardPoints, 3));
    }

    /**
     * 每次都从最左边或最右边取，那么最后没有取走的一定是中间某一段连续的卡牌
     * 那可以将问题转换：求能获得的最大点数 ——> 最后留下来的最小点数
     * 那么就通过一个长度=n-k的window保存这段未取走的值，移动窗口求最小
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int l = n - k;
        int window = 0;
        for (int i = 0; i < l; i++) {
            window += cardPoints[i];
        }
        int min = window;
        int total = window;

        for (int i = l; i < n; i++) {
            window += cardPoints[i] - cardPoints[i - l];
            total += cardPoints[i];
            min = Math.min(min, window);
        }
        return total - min;
    }
}
