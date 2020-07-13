package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题 17.13. 恢复空格
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月09日
 */
public class ReSpaceLCCI {

    /*
        哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
        像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
        在处理标点符号和大小写之前，你得先把它断成词语。
        当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
        假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
        注意：本题相对原题稍作改动，只需返回未识别的字符数

        示例：
            输入：
                dictionary = ["looked","just","like","her","brother"]
                sentence = "jesslookedjustliketimherbrother"
            输出： 7
        解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
        提示：
            0 <= len(sentence) <= 1000
            dictionary中总字符数不超过 150000。
            你可以认为dictionary和sentence中只包含小写字母。
     */

    @Test
    public void test() {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "jesslookedjustliketimherbrother";
        Assert.assertEquals(7, respace(dictionary, sentence));
    }

    @Test
    public void test2() {
        String[] dictionary = {"looked", "just", "like", "her", "brother"};
        String sentence = "";
        Assert.assertEquals(0, respace(dictionary, sentence));
    }

    @Test
    public void test3() {
        String[] dictionary = {"bt", "vbtbvtvvbbvtbvvbbbvbtbbv", "bvvbbbvvvbvttbtbvtvtvvbttbbbtvvvb", "btttbvbbbtbbtbvvttbvbvtvbtbbttb", "bt", "vvbvbvbvtbvbvvvvtv", "tbvvvtttvtbvbtttvvbtvvvvtvvbvvtvvbbvbbbvb", "v", "bvb", "vvtbvtvbttbttvvbvttbt", "btbtbtttvbbtbttbtvvttbvtbtvtbvvtbbbb", "bttbvbbttvvbtvvvvb", "bvvbvbvttbvtbvvtbttvvvvtvbtvbttbbvvtvtvv", "vbtttt", "btbvbbbvbtvtbvvv", "b", "tbtbtv", "vbvbbvvbvbbvvb", "vbvvtvbvbvbttvbvbtvbtbtvtbvbbtb", "bvvbvvttttttbtvvvttvbvtvvbvtbtvtbvttvvtbt", "bvtbttv", "bbbt", "vtt", "ttvv", "b", "vbb", "vtvvbtttvtbbvvbbtbb", "vvv", "vvvvbbbtbbbvbbbb", "ttvvbtvv", "v", "btvbbvtbbvbvtvttvvbbbtbvvvtbtb", "vv", "btbttbtbbvbvvbvttbttvtbbtbvtttvbbtbbtvtvvvvbbttvtvvbbbv", "ttvbbbbttbtbtb", "tvvbvbvvb", "vv", "ttbttvtvbtbbbbv", "bvvvtbbvvvbtvbvtvtvvvvbb", "vtbvvbvvvvvttvbbttbbvtvt", "tbvbbt", "b", "v", "tvbbtvvtvvtbtbttvvvb", "tbttbttbbbtbtvtvtvtbbbvvtbbbvbbvvvbbttvvt", "bbvttvtvvtbvbbttvbbtbvvttbvbvbtbvvvbbbvbvbvbtvbtvvvtvvtbttbttbbvbbbttvvvbvvtb", "vttvvbvv", "tbttbvvttvbtvvtbbvvv", "bvbbbbbbbb", "vtbvvtbbvtt", "bvttbvvbvb", "tvttttbbvvvbbtttvvv"};
        String sentence = "btbvtttttbvttbvvbbtvvbvbvvbtbtbtvbtttbvbbbtbbtbvvttbvbvtvbtbbttbvvbvbtttbvttbvvbbvvv";
        Assert.assertEquals(5, respace(dictionary, sentence));
    }

    /**
     * 动态规划：使用字典树用来判断某单词是否存在，保存到第i个位置时，所剩最少的未识别字符
     * 遍历到每个i时，选取一个j<=i，判断j~i的子串是否存在，每遇到一个存在的子串时，判断dp[i] = min(dp[i],dp[j-1])
     * 注意j从后往前遍历，才能复用之前已遍历的dp[i]
     */
    public int respace(String[] dictionary, String sentence) {
        if (sentence.length() == 0) {
            return 0;
        }
        Trie root = new Trie();
        for (String s : dictionary) {
            root.insert(s);
        }

        int[] dp = new int[sentence.length()];
        char[] chars = sentence.toCharArray();

        for (int i = 0; i < sentence.length(); i++) {
            Trie cur = root;
            dp[i] = i == 0 ? 1 : dp[i - 1] + 1;
            for (int j = i; j >= 0; j--) {
                char c = chars[j];
                Trie next = cur.next[c - 'a'];
                if (next == null) {
                    break;
                } else if (next.isEnd) {
                    int preMin = j > 0 ? dp[j - 1] : 0;
                    dp[i] = Math.min(dp[i], preMin);
                }
                cur = next;
            }
        }

        return dp[sentence.length() - 1];
    }

    static class Trie {

        boolean isEnd;
        Trie[] next;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie cur = this;

            char[] chars = word.toCharArray();
            for (int i = word.length() - 1; i >= 0; i--) {
                int index = chars[i] - 'a';
                Trie next = cur.next[index];
                if (next == null) {
                    next = new Trie();
                    cur.next[index] = next;
                }
                cur = next;
            }
            cur.isEnd = true;
        }
    }
}
