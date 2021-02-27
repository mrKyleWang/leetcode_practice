package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5. 最长回文子串
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月02日
 */
public class LongestPalindrome {

    /*
        给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

        示例 1：
            输入: "babad"
            输出: "bab"
            注意: "aba" 也是一个有效答案。
        示例 2：
            输入: "cbbd"
            输出: "bb"
     */

    @Test
    public void test() {
        String s = "abadd";
        Assert.assertEquals("aba", longestPalindrome(s));
        Assert.assertEquals("aba", longestPalindrome2(s));
        Assert.assertEquals("aba", longestPalindrome3(s));
    }

    /**
     * 1.暴力法
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int max = 0;
        String maxStr = null;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; i - j + 1 > max; j++) {
                if (isPalindrome(chars, j, i)) {
                    max = i - j + 1;
                    maxStr = s.substring(j, i + 1);
                    break;
                }
            }
        }
        return maxStr == null ? String.valueOf(chars[0]) : maxStr;
    }

    private boolean isPalindrome(char[] chars, int start, int end) {
        int head = start;
        int tail = end;
        while (head <= tail) {
            if (chars[head] != chars[tail]) {
                return false;
            }
            head++;
            tail--;
        }

        return true;
    }

    /**
     * 2.动态规划
     */
    public String longestPalindrome2(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int maxStart = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (chars[i] == chars[j]) {
                    if (j - i <= 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    maxStart = i;
                }
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    /**
     * 3.中心扩散
     */
    public String longestPalindrome3(String s) {
        int n = s.length();
        if (n < 2) {
            return s;
        }
        int start = 0, end = 0;
        for (int i = 0; i < n - 1; i++) {
            int maxOdd = centerSpread(s, i, i);
            int maxEven = centerSpread(s, i, i + 1);
            int curMax = Math.max(maxOdd, maxEven);
            if (curMax > end - start + 1) {
                start = i - (curMax - 1) / 2;
                end = i + curMax / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int centerSpread(String s, int left, int right) {
        char[] chars = s.toCharArray();
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
