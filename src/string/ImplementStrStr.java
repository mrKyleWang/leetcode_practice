package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 28. 实现 strStr()
 * @author KyleWang
 * @version 1.0
 * @date 2019年05月09日
 */
public class ImplementStrStr {

    /*
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
     * (从0开始)。如果不存在，则返回 -1。
     *
     * 示例 1:
     * 输入: haystack = "hello", needle = "ll" 输出: 2 示例 2:
     * 输入: haystack = "aaaaa", needle = "bba" 输出: -1 说明:
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符
     */

    @Test
    public void test() {
        Assert.assertEquals(2, strStr("hello", "ll"));
        Assert.assertEquals(2, strStr2("hello", "ll"));
    }


    /**
     * 暴力解法，时间复杂度O(M*N)
     */
    public int strStr(String haystack, String needle) {
        char[] chars = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        if (pattern.length == 0) {
            return 0;
        } else if (pattern.length <= chars.length) {
            for (int i = 0; i < chars.length; i++) {
                for (int j = 0; j < pattern.length; j++) {
                    if (pattern[j] != chars[i + j]) {
                        break;
                    } else if (j == pattern.length - 1) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * KMP算法，O(n)
     */
    public int strStr2(String haystack, String needle) {
        char[] chars = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        if (pattern.length == 0) {
            return 0;
        }
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        while (i < chars.length && j < pattern.length) {
            if (chars[i] == pattern[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == pattern.length ? i - j : -1;
    }

    private int[] getNext(char[] pattern) {
        int[] next = new int[pattern.length];
        next[0] = -1;
        int pos = 2;
        int prefix = 0;
        while (pos < pattern.length) {
            if (pattern[prefix] == pattern[pos - 1]) {
                next[pos++] = ++prefix;
            } else if (prefix > 0) {
                prefix = next[prefix];
            } else {
                pos++;
            }
        }
        return next;
    }

    @Test
    public void testGetNext() {
        char[] chars = "adcadde".toCharArray();
        System.out.println(Arrays.toString(getNext(chars)));
    }
}
