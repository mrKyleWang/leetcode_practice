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
    }


    /**
     * 用map保存char的数量，通过嵌套遍历
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
}
