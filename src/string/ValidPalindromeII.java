package string;

import org.junit.Test;

/**
 * 680. 验证回文字符串 Ⅱ
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月19日
 */
public class ValidPalindromeII {


    /*
        给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

        示例 1:
            输入: "aba"
            输出: True
        示例 2:
            输入: "abca"
            输出: True
            解释: 你可以删除c字符。
        注意:
            字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。

     */

    @Test
    public void test() {
        String str = "cxcaac";
        String str2 = "aguokepatgbnvfqmgmlucupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuclmgmqfvnbgtapekouga";
        System.out.println(validPalindrome(str));
        System.out.println(str);
        StringBuilder builder = new StringBuilder(str);
        System.out.println(builder.reverse().toString());

    }

    public boolean validPalindrome(String s) {
        char[] chars = s.toCharArray();
        int breakpoint = -1;
        int count = 0;
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            if (chars[i] != chars[j]) {
                breakpoint = i;
                break;
            }
        }
        if (breakpoint >= 0) {
            for (int i = breakpoint + 1, j = chars.length - breakpoint - 1; i < j; i++, j--) {
                if (chars[i] != chars[j]) {
                    count++;
                    break;
                }
            }
            for (int i = breakpoint, j = chars.length - breakpoint - 2; i < j; i++, j--) {
                if (chars[i] != chars[j]) {
                    count++;
                    break;
                }
            }
        }
        return count <= 1;
    }
}
