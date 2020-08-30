package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 214. 最短回文串
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月30日
 */
public class ShortestPalindrome {

    /*
        给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
        示例 1:
            输入: "aacecaaa"
            输出: "aaacecaaa"
        示例 2:
            输入: "abcd"
            输出: "dcbabcd"
     */

    @Test
    public void test() {
        String s = "aacecaaa";
        Assert.assertEquals("aaacecaaa", shortestPalindrome(s));
    }

    @Test
    public void test2() {
        String s = "abcd";
        Assert.assertEquals("dcbabcd", shortestPalindrome(s));
    }

    /**
     * 如果是在前面添加字符，相当于回文串的中点一定在字符串s前半段，从中点开始判断，如果不满足回文则向前移
     */
    public String shortestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int mid = (s.length() - 1) / 2;
        String add;
        while ((add = isPalindrome(chars, mid - 1, mid + 1)) == null && (add = isPalindrome(chars, mid - 1, mid)) == null) {
            mid--;
        }
        return add + s;
    }

    private String isPalindrome(char[] chars, int head, int tail) {
        while (head >= 0) {
            if (chars[head] == chars[tail]) {
                head--;
                tail++;
            } else {
                return null;
            }
        }
        StringBuilder builder = new StringBuilder();
        for (int i = chars.length - 1; i >= tail; i--) {
            builder.append(chars[i]);
        }
        return builder.toString();
    }
}
