package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 10. 正则表达式匹配
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月24日
 */
public class RegularExpressionMatching {

    /*
        给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
            '.' 匹配任意单个字符
            '*' 匹配零个或多个前面的那一个元素
        所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

        说明:
            s 可能为空，且只包含从 a-z 的小写字母。
            p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     */


    @Test
    public void test() {
        String s = "aa";
        String p = "a";
        boolean match = isMatch(s, p);
        Assert.assertFalse(match);
    }

    @Test
    public void test2() {
        String s = "aa";
        String p = "a*";
        boolean match = isMatch(s, p);
        Assert.assertTrue(match);
    }

    @Test
    public void test3() {
        String s = "ab";
        String p = ".*";
        boolean match = isMatch(s, p);
        Assert.assertTrue(match);
    }

    @Test
    public void test4() {
        String s = "aab";
        String p = "c*a*b";
        boolean match = isMatch(s, p);
        Assert.assertTrue(match);
    }

    @Test
    public void test5() {
        String s = "mississippi";
        String p = "mis*is*p*.";
        boolean match = isMatch(s, p);
        Assert.assertFalse(match);
    }

    @Test
    public void test6() {
        String s = "mississippi";
        String p = "mis*is*ip*.";
        boolean match = isMatch(s, p);
        Assert.assertTrue(match);
    }

    @Test
    public void test7() {
        String s = "ab";
        String p = ".*c";
        boolean match = isMatch(s, p);
        Assert.assertFalse(match);
    }

    @Test
    public void test8() {
        String s = "aaa";
        String p = "ab*a";
        boolean match = isMatch(s, p);
        Assert.assertFalse(match);
    }

    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        return isMatch(0, 0, sChars, pChars);
    }

    /**
     * 主要复杂逻辑在于对*的匹配，通过递归，尝试*所匹配字符的个数 0~?
     */
    public boolean isMatch(int i, int j, char[] sChars, char[] pChars) {
        for (; j < pChars.length; i++, j++) {
            if (j + 1 < pChars.length && pChars[j + 1] == '*') {
                if (isMatch(i, j + 2, sChars, pChars)) {
                    return true;
                }
                int offset = 0;
                while (i + offset < sChars.length && (pChars[j] == '.' || sChars[i + offset] == pChars[j])) {
                    if (isMatch(i + offset + 1, j + 2, sChars, pChars)) {
                        return true;
                    }
                    offset++;
                }
            } else if (i < sChars.length && pChars[j] != '.' && sChars[i] != pChars[j]) {
                return false;
            }
        }
        return i == sChars.length;
    }
}
