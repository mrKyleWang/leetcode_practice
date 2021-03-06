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
        Assert.assertEquals(3, lengthOfLongestSubstring2("abcabcbb"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals(3, lengthOfLongestSubstring2("pwwkew"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(5, lengthOfLongestSubstring("tmmzuxt"));
        Assert.assertEquals(5, lengthOfLongestSubstring2("tmmzuxt"));
    }

    /**
     * 方法1：滑动窗口
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

    /**
     * 方法2：动态规划
     * 在i位置时，当前字符为c
     * map保存i位置之前每个字符出现的最后的位置，dp[i]表示以i结尾的最长无重复子串
     * 转移方程为：dp[i] = Math.min(dp[i-1]+1,i-map.get(c))
     * 由于只需要用到dp[i-1]，因此只需要用一个pre即可保存此值
     */
    public int lengthOfLongestSubstring2(String s) {
        int max = 1;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int pre = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            pre = Math.min(pre + 1, i - map.getOrDefault(c, -1));
            map.put(c, i);
            max = Math.max(max, pre);
        }
        return max;
    }
}
