package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 459. 重复的子字符串
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月24日
 */
public class RepeatedSubstringPattern {

    /*
        给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
        示例 1:
            输入: "abab"
            输出: True
            解释: 可由子字符串 "ab" 重复两次构成。
        示例 2:
            输入: "aba"
            输出: False
        示例 3:
            输入: "abcabcabcabc"
            输出: True
            解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
     */


    @Test
    public void test() {
        Assert.assertEquals(true, repeatedSubstringPattern("bb"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(false, repeatedSubstringPattern("aba"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(true, repeatedSubstringPattern("abcabcabcabc"));
    }

    /**
     * 能由n个子串重复构成的，一定被n整除，且n>=2，遍历子串的长度，确认每段子串是否与第一个子串相同
     */
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        for (int i = 2; i <= length; i++) {
            if (length % i == 0) {
                int subLength = length / i;
                String pattern = s.substring(0, subLength);
                boolean flag = true;
                for (int j = 1; j < i; j++) {
                    if (!s.substring(subLength * j, subLength * (j + 1)).equals(pattern)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}
