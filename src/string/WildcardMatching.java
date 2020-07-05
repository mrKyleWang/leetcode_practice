package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 44. 通配符匹配
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月05日
 */
public class WildcardMatching {

    /*
        给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
        '?' 可以匹配任何单个字符。
        '*' 可以匹配任意字符串（包括空字符串）。
        两个字符串完全匹配才算匹配成功。

        说明:
            s 可能为空，且只包含从 a-z 的小写字母。
            p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
        示例 1:

        输入:
        s = "aa"
        p = "a"
        输出: false
        解释: "a" 无法匹配 "aa" 整个字符串。
        示例 2:

        输入:
        s = "aa"
        p = "*"
        输出: true
        解释: '*' 可以匹配任意字符串。
        示例 3:

        输入:
        s = "cb"
        p = "?a"
        输出: false
        解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
        示例 4:

        输入:
        s = "adceb"
        p = "*a*b"
        输出: true
        解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
        示例 5:

        输入:
        s = "acdcb"
        p = "a*c?b"
        输出: false
     */

    @Test
    public void test() {
        String s = "aa";
        String p = "a";
        Assert.assertFalse(isMatch(s, p));
    }

    @Test
    public void test2() {
        String s = "";
        String p = "*";
        Assert.assertTrue(isMatch(s, p));
    }

    @Test
    public void test3() {
        String s = "cb";
        String p = "?a";
        Assert.assertFalse(isMatch(s, p));
    }

    @Test
    public void test4() {
        String s = "adceb";
        String p = "*a*b";
        Assert.assertTrue(isMatch(s, p));
    }

    @Test
    public void test5() {
        String s = "acdcb";
        String p = "a*c?b";
        Assert.assertFalse(isMatch(s, p));
    }

    @Test
    public void test6() {
        String s = "bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab";
        String p = "b*b*ab**ba*b**b***bba";
        Assert.assertFalse(isMatch(s, p));
    }

    /**
     * 动态规划
     * 保存s的前i位置与p的前j位置是否匹配
     * p[j] 为 字母或?时，dp[i][j] = dp[i-1][j-1] && s[i]==p[j]
     * 当p[j] == '*' 时，dp[i][j] = dp[i-1][j] || dp[i][j-1] ，即使用或不使用这个星号
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length() && p.charAt(i) == '*'; i++) {
            dp[0][i + 1] = true;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                char c = p.charAt(j - 1);
                if (c == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (c == '?' || c == s.charAt(i - 1));
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    /**
     * 递归+回溯（会超时）
     */
    public boolean isMatch2(String s, String p) {
        p = p.replaceAll("\\*+", "\\*");
        return isMatch(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    private boolean isMatch(char[] sChars, char[] pChars, int sIndex, int pIndex) {
        for (; pIndex < pChars.length; sIndex++, pIndex++) {
            if (pChars[pIndex] == '*') {
                int wildcardLength = sIndex;
                while (wildcardLength <= sChars.length) {
                    if (isMatch(sChars, pChars, wildcardLength, pIndex + 1)) {
                        return true;
                    }
                    wildcardLength++;
                }
                return false;
            } else if (sIndex < sChars.length && pChars[pIndex] != '?') {
                if (sChars[sIndex] != pChars[pIndex]) {
                    return false;
                }
            }
        }
        return sIndex == sChars.length && pIndex == pChars.length;
    }
}
