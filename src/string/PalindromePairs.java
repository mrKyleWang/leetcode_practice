package string;

import org.junit.Test;

import java.util.*;

/**
 * 336. 回文对
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月06日
 */
public class PalindromePairs {


    /*
        给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
        示例 1:
            输入: ["abcd","dcba","lls","s","sssll"]
            输出: [[0,1],[1,0],[3,2],[2,4]]
            解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
        示例 2:
            输入: ["bat","tab","cat"]
            输出: [[0,1],[1,0]]
            解释: 可拼接成的回文串为 ["battab","tabbat"]
     */

    @Test
    public void test() {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> lists = palindromePairs(words);
        System.out.println(lists.toString());
    }

    @Test
    public void test2() {
        String[] words = {"bat", "tab", "cat"};
        List<List<Integer>> lists = palindromePairs(words);
        System.out.println(lists.toString());
    }

    /**
     * 对于两个字符串s1,s2,存在3种情况：
     * 1. s1.length=s2.length，s1=s2.reverse
     * 2. s1.length>s2.length，s1 = t1+t2，t2为回文串,t1 = s2.reverse
     * 3. s1.length<s2.length，s2 = t1+t2，t2为回文串,t1 = s1.reverse
     * 可以合并成1种情况：对于每个s1>=s2，如果s1可以分为t1+t2/t2+t1，t2为回文串，t1存在其翻转，则s1,s2组合为回文串
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<>();

        // 使用map保存每个word的翻转
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(new StringBuilder(words[i]).reverse().toString(), i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() > 0) {
                for (int j = 0; j <= word.length(); j++) {
                    String left = word.substring(0, j);
                    String right = word.substring(j);

                    // 回文在头部
                    if (check(left)) {
                        Integer index = map.get(right);
                        if (index != null && index != i) {
                            list.add(Arrays.asList(index, i));
                        }
                    }

                    // 回文在尾部
                    if (j < word.length() && check(right)) {
                        Integer index = map.get(left);
                        if (index != null) {
                            list.add(Arrays.asList(i, index));
                        }
                    }
                }
            }
        }
        return list;
    }

    private boolean check(String s) {
        int head = 0;
        int tail = s.length() - 1;
        char[] chars = s.toCharArray();
        while (head < tail) {
            if (chars[head] != chars[tail]) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}
