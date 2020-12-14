package string;

import org.junit.Test;

import java.util.*;

/**
 * 49. 字母异位词分组
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月14日
 */
public class GroupAnagrams {

    /*
        给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
        示例:

        输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出:
        [
          ["ate","eat","tea"],
          ["nat","tan"],
          ["bat"]
        ]
        说明：

        所有输入均为小写字母。
        不考虑答案输出的顺序。
     */

    @Test
    public void test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

}
