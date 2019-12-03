package string;

import java.util.HashSet;
import java.util.Set;

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

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().lengthOfLongestSubstring("bbbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            int count = 1;
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    break;
                }
                set.add(s.charAt(j));
                count++;
            }
            if (count > max) {
                max = count;
            }
        }
        return max;
    }
}
