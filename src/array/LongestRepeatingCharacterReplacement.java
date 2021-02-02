package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 424. 替换后的最长重复字符
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月02日
 */
public class LongestRepeatingCharacterReplacement {

    /*
        给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
        注意：字符串长度 和 k 不会超过 104。

        示例 1：
            输入：s = "ABAB", k = 2
            输出：4
            解释：用两个'A'替换为两个'B',反之亦然。
        示例 2：
            输入：s = "AABABBA", k = 1
            输出：4
            解释：
            将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
            子串 "BBBB" 有最长重复字母, 答案为 4。
     */

    @Test
    public void test() {
        String s = "ABAB";
        Assert.assertEquals(4, characterReplacement(s, 2));
    }

    @Test
    public void test2() {
        String s = "AABABBA";
        Assert.assertEquals(4, characterReplacement(s, 1));
    }

    public int characterReplacement(String s, int k) {
        char[] chars = s.toCharArray();
        int[] window = new int[26];
        int left = 0;
        int max = 0;
        int curMax = 0;
        for (int right = 0; right < chars.length; right++) {
            window[chars[right] - 'A']++;
            curMax = Math.max(curMax, window[chars[right] - 'A']);
            if (right - left - curMax + 1 > k) {
                window[chars[left] - 'A']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
