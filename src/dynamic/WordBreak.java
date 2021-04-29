package dynamic;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * @author KyleWang
 * @version 1.0
 * @date 2021年04月29日
 */
public class WordBreak {


    /*
        给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
        说明：
            拆分时可以重复使用字典中的单词。
            你可以假设字典中没有重复的单词。
        示例 1：
            输入: s = "leetcode", wordDict = ["leet", "code"]
            输出: true
            解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
        示例 2：
            输入: s = "applepenapple", wordDict = ["apple", "pen"]
            输出: true
            解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
                 注意你可以重复使用字典中的单词。
        示例 3：
            输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
            输出: false
     */

    @Test
    public void test() {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        Assert.assertEquals(true, wordBreak(s, wordDict));
    }

    @Test
    public void test2() {
        System.out.println("12345".substring(0, 5));
    }

    /**
     * 动态规划，dp[i] 表示 0~i可被拆分为字典中的单词
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (set.contains(s.substring(0, i + 1))) {
                dp[i] = true;
            } else {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && set.contains(s.substring(j + 1, i + 1))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[n - 1];
    }

}
