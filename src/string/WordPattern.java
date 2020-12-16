package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 290. 单词规律
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月16日
 */
public class WordPattern {

    /*
        给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
        这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

        示例1:
            输入: pattern = "abba", str = "dog cat cat dog"
            输出: true
        示例 2:
            输入:pattern = "abba", str = "dog cat cat fish"
            输出: false
        示例 3:
            输入: pattern = "aaaa", str = "dog cat cat dog"
            输出: false
        示例 4:
            输入: pattern = "abba", str = "dog dog dog dog"
            输出: false
        说明:
        你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
     */

    @Test
    public void test() {
        String pattern = "abba";
        String str = "dog cat cat dog";
        Assert.assertEquals(true, wordPattern(pattern, str));
    }

    @Test
    public void test2() {
        String pattern = "abba";
        String str = "dog cat cat fish";
        Assert.assertEquals(false, wordPattern(pattern, str));
    }

    @Test
    public void test3() {
        String pattern = "aaaa";
        String str = "dog cat cat dog";
        Assert.assertEquals(false, wordPattern(pattern, str));
    }

    @Test
    public void test4() {
        String pattern = "abba";
        String str = "dog dog dog dog";
        Assert.assertEquals(false, wordPattern(pattern, str));
    }

    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        char[] patterns = pattern.toCharArray();
        if (strings.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> strSet = new HashSet<>();
        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            Character p = patterns[i];
            if (map.containsKey(p)) {
                if (!map.get(p).equals(str)) {
                    return false;
                }
            } else {
                if (strSet.contains(str)) {
                    return false;
                }
                map.put(p, str);
                strSet.add(str);
            }
        }
        return true;
    }
}
