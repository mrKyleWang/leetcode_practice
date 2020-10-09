package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * LCP 19. 秋叶收藏集
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月09日
 */
public class MinimumOperations {

    /*
        小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y，其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
        出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。
        每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

        示例 1：
            输入：leaves = "rrryyyrryyyrr"
            输出：2
            解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

        示例 2：
            输入：leaves = "ryr"
            输出：0
            解释：已符合要求，不需要额外操作

        提示：
            3 <= leaves.length <= 10^5
            leaves 中只包含字符 'r' 和字符 'y'
     */

    @Test
    public void test() {
        String leaves = "rrryyyrryyyrr";
        Assert.assertEquals(2, minimumOperations(leaves));
    }

    @Test
    public void test2() {
        String leaves = "ryr";
        Assert.assertEquals(0, minimumOperations(leaves));
    }

    /**
     * 动态规划：
     * dp[i][0]=到当前叶片全红的操作数
     * dp[i][1]=到当前叶片为红-黄的操作数
     * dp[i][2]=到当前叶片为红-黄-红的操作数
     */
    public int minimumOperations(String leaves) {
        char[] chars = leaves.toCharArray();
        int[][] dp = new int[chars.length][3];

        dp[0][0] = chars[0] == 'r' ? 0 : 1;
        dp[0][1] = 1000000;
        dp[0][2] = 1000000;
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            dp[i][0] = (c == 'r' ? 0 : 1) + dp[i - 1][0];
            dp[i][1] = (c == 'y' ? 0 : 1) + Math.min(dp[i - 1][0], dp[i - 1][1]);
            dp[i][2] = (c == 'r' ? 0 : 1) + Math.min(dp[i - 1][1], dp[i - 1][2]);
        }
        return dp[chars.length - 1][2];
    }
}
