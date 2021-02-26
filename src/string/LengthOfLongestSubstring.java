package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/03
 */
public class LengthOfLongestSubstring {

    /*
        给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
        示例 1:
            输入: "abcabcbb"
            输出: 3
            解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        示例 2:
            输入: "bbbbb"
            输出: 1
            解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:
            输入: "pwwkew"
            输出: 3
            解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
                 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

     */

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(5, lengthOfLongestSubstring("tmmzuxt"));
    }

    /**
     * 使用map保存字符对应的位置（最后出现的位置）
     * 当l~i不包含重复字符时，计算长度，而如果map中有，则将l右移至重复字符的后一位，此时l左侧的索引都可以作为无效（在containsKey时用于判断）
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 1;
        int l = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!map.containsKey(c) || map.get(c) < l) {
                max = Math.max(max, i - l + 1);
            } else {
                l = map.get(c) + 1;
            }
            map.put(c, i);
        }
        return max;
    }
}
