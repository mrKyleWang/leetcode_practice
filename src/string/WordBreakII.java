package string;

import org.junit.Test;

import java.util.*;

/**
 * 140. 单词拆分 II
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月18日
 */
public class WordBreakII {

    /*
        给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
        说明：
            分隔时可以重复使用字典中的单词。
            你可以假设字典中没有重复的单词。

        示例 1：
            输入:
                s = "catsanddog"
                wordDict = ["cat", "cats", "and", "sand", "dog"]
            输出:
                [
                  "cats and dog",
                  "cat sand dog"
                ]

        示例 2：
            输入:
                s = "pineapplepenapple"
                wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
            输出:
                [
                  "pine apple pen apple",
                  "pineapple pen apple",
                  "pine applepen apple"
                ]
            解释: 注意你可以重复使用字典中的单词。

        示例 3：
            输入:
                s = "catsandog"
                wordDict = ["cats", "dog", "sand", "and", "cat"]
            输出:
                []
     */

    @Test
    public void test() {
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreak(s, wordDict));
    }

    @Test
    public void test2() {
        String s = "pineapplepenapple";
        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak(s, wordDict));
    }

    @Test
    public void test3() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak(s, wordDict));
    }

    @Test
    public void test4() {
        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa", "aa", "a");
        System.out.println(wordBreak(s, wordDict));
    }

    @Test
    public void test5() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");
        System.out.println(wordBreak(s, wordDict));
    }

    /**
     * 递归回溯+剪枝
     * 用map保存每一个start位置起始能得到的结果集
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return wordBreak(s.toCharArray(), dict, new HashMap<>(s.length()), 0);
    }

    private List<String> wordBreak(char[] chars, Set<String> dict, Map<Integer, List<String>> map, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < chars.length; i++) {
            builder.append(chars[i]);
            String word = builder.toString();
            if (dict.contains(word)) {
                if (i == chars.length - 1) {
                    list.add(word);
                } else {
                    List<String> back = wordBreak(chars, dict, map, i + 1);
                    back.forEach(s -> list.add(word + " " + s));
                }
            }
        }
        map.put(start, list);
        return list;
    }
}
