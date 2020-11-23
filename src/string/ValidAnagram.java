package string;

/**
 * 242. 有效的字母异位词
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月30日
 */
public class ValidAnagram {

    /*
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
     *
     * 示例 1: 输入: s = "anagram", t = "nagaram" 输出: true
     *
     * 示例 2: 输入: s = "rat", t = "car" 输出: false
     */

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    /**
     * 使用map保存每个字符及其出现的次数，然后对比次数是否一致
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] map = new int[26];
        for (char sChar : s.toCharArray()) {
            map[sChar - 'a']++;
        }

        int count = 0;
        for (char tChar : t.toCharArray()) {
            if (map[tChar - 'a'] == 0) {
                return false;
            }
            map[tChar - 'a']--;
            count++;
        }
        return count == t.length();
    }
}
