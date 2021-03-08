package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 132. 分割回文串 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月08日
 */
public class PalindromePartitioningII {

    @Test
    public void test() {
        Assert.assertEquals(1, minCut("aab"));
    }


    @Test
    public void test2() {
        Assert.assertEquals(1, minCut("cdd"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(1, minCut("aab"));
    }

    /**
     * temp[i][j]保存 i~j是否为回文串
     * dp[i] 为0~i最少的分割次数，状态转移：从0~i中取某个点j ,且temp[j+1][i] = true，则dp[i] = dp[j]+1
     */
    public int minCut(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        boolean[][] temp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    temp[i][j] = true;
                } else {
                    temp[i][j] = chars[i] == chars[j] && (j - i == 1 || temp[i + 1][j - 1]);
                }
            }
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (!temp[0][i]) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (temp[j + 1][i]) {
                        min = Math.min(min, dp[j] + 1);
                    }
                }
                dp[i] = min;
            }
        }
        return dp[n - 1];
    }
}
