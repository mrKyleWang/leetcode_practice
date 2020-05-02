package string;

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
        System.out.println(isPalindrome(s.toCharArray(), 0, s.length() - 1));
        System.out.println(longestPalindrome(s));
    }

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
}
