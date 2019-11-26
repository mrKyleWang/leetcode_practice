package dynamic;

/**
 * 322. 零钱兑换
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/26
 */
public class CoinChange {

    /*
        给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
        示例 1:
            输入: coins = [1, 2, 5], amount = 11
            输出: 3
            解释: 11 = 5 + 5 + 1
        示例 2:
            输入: coins = [2], amount = 3
            输出: -1
            说明:
        你可以认为每种硬币的数量是无限的。
     */

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        // 存储到每个金额下时的最小硬币数
        int[] tempResult = new int[amount + 1];
        tempResult[0] = 0;
        for (int i = 1; i <= amount; i++) {
            int currentMin = amount + 1;
            for (int coin : coins) {
                if (i >= coin && tempResult[i - coin] + 1 < currentMin) {
                    currentMin = tempResult[i - coin] + 1;
                }
            }
            tempResult[i] = currentMin;
        }
        return tempResult[amount] > amount ? -1 : tempResult[amount];
    }
}
