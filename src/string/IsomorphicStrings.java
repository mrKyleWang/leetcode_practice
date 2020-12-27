package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205. 同构字符串
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月27日
 */
public class IsomorphicStrings {

    /*
        给定两个字符串 s 和 t，判断它们是否是同构的。
        如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
        所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。

        示例 1:
            输入: s = "egg", t = "add"
            输出: true
        示例 2:
            输入: s = "foo", t = "bar"
            输出: false
        示例 3:
            输入: s = "paper", t = "title"
            输出: true
        说明:
            你可以假设 s 和 t 具有相同的长度。
     */

    @Test
    public void test() {
        Assert.assertTrue(isIsomorphic("egg", "add"));
    }

    @Test
    public void test2() {
        Assert.assertFalse(isIsomorphic("foo", "bar"));
    }

    @Test
    public void test3() {
        Assert.assertTrue(isIsomorphic("paper", "title"));
    }

    @Test
    public void test4() {
        Assert.assertFalse(isIsomorphic("bar", "foo"));
    }

    @Test
    public void test5() {
        Assert.assertTrue(isIsomorphic("egg", "add"));
    }

    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        for (int i = 0; i < charS.length; i++) {
            Character c = map.get(charS[i]);
            if (c == null) {
                if (set.contains(charT[i])) {
                    return false;
                }
                set.add(charT[i]);
                map.put(charS[i], charT[i]);
            } else if (c != charT[i]) {
                return false;
            }
        }
        return true;
    }
}
