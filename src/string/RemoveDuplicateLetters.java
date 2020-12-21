package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 316. 去除重复字母
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月21日
 */
public class RemoveDuplicateLetters {

    /*
        给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
        需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

        示例 1：
            输入：s = "bcabc"
            输出："abc"
        示例 2：
            输入：s = "cbacdcbc"
            输出："acdb"

        提示：
            1 <= s.length <= 104
            s 由小写英文字母组成
     */

    @Test
    public void test() {
        String s = "bcabc";
        Assert.assertEquals("abc", removeDuplicateLetters(s));
    }

    @Test
    public void test2() {
        String s = "cbacdcbc";
        Assert.assertEquals("acdb", removeDuplicateLetters(s));
    }

    @Test
    public void test3() {
        String s = "ecbacba";
        Assert.assertEquals("eacb", removeDuplicateLetters(s));
    }

    @Test
    public void test4() {
        String s = "abacb";
        Assert.assertEquals("abc", removeDuplicateLetters(s));
    }

    /**
     * stack存储要保留的字符
     * 每次遍历到i，都对比chars[i]与stack栈顶元素，如果栈顶更大则弹出
     */
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (!stack.contains(c)) {
                while (!stack.isEmpty() && c < stack.peekLast() && count[stack.peekLast() - 'a'] >= 1) {
                    stack.pollLast();
                }
                stack.addLast(c);
            }
            count[c - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (Character c : stack) {
            builder.append(c);
        }
        return builder.toString();
    }
}
