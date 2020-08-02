package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月02日
 */
public class MinimumWindowSubstring {

    /*
        给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。

        示例：
            输入: S = "ADOBECODEBANC", T = "ABC"
            输出: "BANC"
        说明：
            如果 S 中不存这样的子串，则返回空字符串 ""。
            如果 S 中存在这样的子串，我们保证它是唯一的答案。
            通过次数67,007提交次数174,567

     */

    @Test
    public void test() {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        Assert.assertEquals("BANC", minWindow(S, T));
    }

    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();

        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int min = Integer.MAX_VALUE;
        int resultLeft = -1;
        int resultRight = -1;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);

            while (check(map, windowMap) && left <= right) {
                if (right - left < min) {
                    resultLeft = left;
                    resultRight = right;
                    min = right - left;
                }

                windowMap.computeIfPresent(s.charAt(left), (k, v) -> --v);
                left++;
            }
            right++;
        }
        return resultLeft < 0 ? "" : s.substring(resultLeft, resultRight + 1);
    }

    private boolean check(HashMap<Character, Integer> map, HashMap<Character, Integer> windowMap) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer count = windowMap.get(entry.getKey());
            if (count == null || count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
