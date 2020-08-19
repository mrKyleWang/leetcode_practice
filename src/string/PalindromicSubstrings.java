package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 647. 回文子串
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月19日
 */
public class PalindromicSubstrings {

    /*
        给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
        具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

        示例 1：
            输入："abc"
            输出：3
            解释：三个回文子串: "a", "b", "c"
        示例 2：
            输入："aaa"
            输出：6
            解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
         

        提示：
            输入的字符串长度不会超过 1000 。
     */

    @Test
    public void test() {
        String s = "abc";
        Assert.assertEquals(3, countSubstrings(s));
    }

    @Test
    public void test2() {
        String s = "aaa";
        Assert.assertEquals(6, countSubstrings(s));
    }

    /**
     * 参照普通回文串的判断方式，以i为起始点，从后向前遍历，找到相同字符，再开始判断
     */
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= i; j--) {
                int head = i;
                int tail = j;
                while (head <= tail) {
                    if (chars[head] != chars[tail]) {
                        break;
                    }
                    head++;
                    tail--;
                }
                if (head > tail) {
                    count++;
                }
            }
        }
        return count;
    }

}
