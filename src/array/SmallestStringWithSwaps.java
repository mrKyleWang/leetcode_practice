package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 1202. 交换字符串中的元素
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月12日
 */
public class SmallestStringWithSwaps {

    /*
        给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
        你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
        返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

        示例 1:
            输入：s = "dcab", pairs = [[0,3],[1,2]]
            输出："bacd"
            解释：
            交换 s[0] 和 s[3], s = "bcad"
            交换 s[1] 和 s[2], s = "bacd"
        示例 2：
            输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
            输出："abcd"
            解释：
            交换 s[0] 和 s[3], s = "bcad"
            交换 s[0] 和 s[2], s = "acbd"
            交换 s[1] 和 s[2], s = "abcd"
        示例 3：
            输入：s = "cba", pairs = [[0,1],[1,2]]
            输出："abc"
            解释：
            交换 s[0] 和 s[1], s = "bca"
            交换 s[1] 和 s[2], s = "bac"
            交换 s[0] 和 s[1], s = "abc"
         
        提示：
            1 <= s.length <= 10^5
            0 <= pairs.length <= 10^5
            0 <= pairs[i][0], pairs[i][1] < s.length
            s 中只含有小写英文字母
     */


    @Test
    public void test() {
        String s = "dcab";
        List<List<Integer>> pairs = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2));
        Assert.assertEquals("bacd", smallestStringWithSwaps(s, pairs));
    }

    @Test
    public void test2() {
        String s = "dcab";
        List<List<Integer>> pairs = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2));
        Assert.assertEquals("abcd", smallestStringWithSwaps(s, pairs));
    }

    @Test
    public void test3() {
        String s = "cba";
        List<List<Integer>> pairs = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2));
        Assert.assertEquals("abc", smallestStringWithSwaps(s, pairs));
    }

    @Test
    public void test4() {
        String s = "vbjjxgdfnru";
        List<List<Integer>> pairs = build(new int[][]{{8, 6}, {3, 4}, {5, 2}, {5, 5}, {3, 5}, {7, 10}, {6, 0}, {10, 0}, {7, 1}, {4, 8}, {6, 2}});
        Assert.assertEquals("bdfgjjnuvrx", smallestStringWithSwaps(s, pairs));
    }

    private List<List<Integer>> build(int[][] pairs) {
        List<List<Integer>> list = new ArrayList<>();
        for (int[] pair : pairs) {
            list.add(Arrays.asList(pair[0], pair[1]));
        }
        return list;
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (List<Integer> pair : pairs) {
            union(parent, pair.get(0), pair.get(1));
        }
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            map.computeIfAbsent(root, k -> new PriorityQueue<>()).offer(chars[i]);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(map.get(find(parent, i)).poll());
        }
        return builder.toString();
    }

    private void union(int[] parent, int i, int j) {
        int pI = find(parent, i);
        int pJ = find(parent, j);
        if (pI != pJ) {
            parent[pJ] = pI;
        }
    }

    private int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
