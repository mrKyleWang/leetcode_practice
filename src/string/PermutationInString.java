package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/05
 */
public class PermutationInString {


    /*
        给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
        换句话说，第一个字符串的排列之一是第二个字符串的子串。

        示例1:
            输入: s1 = "ab" s2 = "eidbaooo"
            输出: True
            解释: s2 包含 s1 的排列之一 ("ba").

        示例2:
            输入: s1= "ab" s2 = "eidboaoo"
            输出: False

        注意：
            输入的字符串只包含小写字母
            两个字符串的长度都在 [1, 10,000] 之间
     */

    public static void main(String[] args) {
        System.out.println(new PermutationInString().checkInclusion("ab", "eidbaooo"));
        System.out.println(new PermutationInString().checkInclusion("ab", "eidboaoo"));
        System.out.println(new PermutationInString().checkInclusion2("a", "ab"));
        System.out.println(new PermutationInString().checkInclusion2("abc", "bbbca"));
    }


    /**
     * 用map保存char的数量，通过嵌套遍历来判断字符数是否相等
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() <= s2.length()) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s1.toCharArray()) {
                map.put(c, (map.containsKey(c) ? map.get(c) + 1 : 1));
            }
            for (int i = 0; i < s2.length(); i++) {
                int count = 0;
                Map<Character, Integer> tempMap = new HashMap<>(map);
                for (int j = i; j < s2.length(); j++) {
                    char cur = s2.charAt(j);
                    Integer charCount = tempMap.get(cur);
                    if (charCount != null && charCount > 0) {
                        tempMap.put(cur, charCount - 1);
                        count++;
                    } else {
                        break;
                    }
                }
                if (count == s1.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 滑动窗口法，使用一个s1长度的窗口，去扫描s2，每次移动一个位置就将进入的和退出的进行计数，全部计数一致则返回true
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m <= n) {
            // map只保存差异，正数表示窗口比s1多的，负数表示缺少的
            Map<Character, Integer> map = new HashMap<>();
            Map<Character, Integer> differMap = new HashMap<>();
            for (char c : s1.toCharArray()) {
                map.put(c, (map.containsKey(c) ? map.get(c) + 1 : 1));
                differMap.put(c, (differMap.containsKey(c) ? differMap.get(c) - 1 : -1));
            }
            // 移动窗口
            for (int i = 0; i < n; i++) {
                char in = s2.charAt(i);
                Integer inCount = differMap.get(in);
                if (inCount != null && inCount == -1) {
                    differMap.remove(in);
                } else {
                    differMap.put(in, inCount == null ? 1 : inCount + 1);
                }
                if (i >= m) {
                    char out = s2.charAt(i - m);
                    Integer outCount = differMap.get(out);
                    if (outCount != null && outCount == 1) {
                        differMap.remove(out);
                    } else {
                        differMap.put(out, outCount == null ? -1 : outCount - 1);
                    }
                }
                if (i >= m - 1 && differMap.size() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
