package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;

/**
 * 387. 字符串中的第一个唯一字符
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月30日
 */
public class FirstUniqueCharacterInAString {

    /*
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * 案例: s = "leetcode" 返回 0. s = "loveleetcode", 返回 2.
     */

    @Test
    public void test() {
        Assert.assertEquals(0, firstUniqChar("leetcode"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(2, firstUniqChar("loveleetcode"));
    }

    public static int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], -1);
            } else {
                map.put(chars[i], i);
            }
        }
        for (Integer index : map.values()) {
            if (index != -1) {
                return index;
            }
        }
        return -1;
    }
}
