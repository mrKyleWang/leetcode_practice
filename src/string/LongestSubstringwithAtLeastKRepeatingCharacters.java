package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 395. 至少有 K 个重复字符的最长子串
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月27日
 */
public class LongestSubstringwithAtLeastKRepeatingCharacters {

    /*
        给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
        示例 1：
            输入：s = "aaabb", k = 3
            输出：3
            解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
        示例 2：
            输入：s = "ababbc", k = 2
            输出：5
            解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
        提示：
            1 <= s.length <= 104
            s 仅由小写英文字母组成
            1 <= k <= 105
     */

    @Test
    public void test() {
        Assert.assertEquals(3, longestSubstring("aaabb", 3));
    }

    @Test
    public void test2() {
        Assert.assertEquals(5, longestSubstring("ababbc", 2));
    }

    /**
     * 分治+递归
     * 通过count < k的字符，将s切分成多个子串，分别在每个子串中继续查找，因为最长的结果一定不会跨越count小于k的字符
     */
    public int longestSubstring(String s, int k) {
        if (s.length() < k) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.compute(c, (key, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() < k) {
                int max = 0;
                for (String split : s.split(String.valueOf(entry.getKey()))) {
                    max = Math.max(max, (longestSubstring(split, k)));
                }
                return max;
            }
        }
        return s.length();
    }
}
