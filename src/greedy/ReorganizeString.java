package greedy;

import org.junit.Test;

/**
 * 767. 重构字符串
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月09日
 */
public class ReorganizeString {

    /*
        给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
        若可行，输出任意可行的结果。若不可行，返回空字符串。

        示例 1:
            输入: S = "aab"
            输出: "aba"
        示例 2:
            输入: S = "aaab"
            输出: ""
        注意:
            S 只包含小写字母并且长度在[1, 500]区间内。
     */

    @Test
    public void test() {
        String S = "aab";
        System.out.println(reorganizeString(S));
    }

    @Test
    public void test2() {
        String S = "aaabb";
        System.out.println(reorganizeString(S));
    }

    public String reorganizeString(String S) {
        int n = S.length();
        char[] chars = S.toCharArray();
        int[] countMap = new int[26];
        int maxCount = 0;
        char maxCountChar = 0;
        // 计数
        for (char c : chars) {
            int charIndex = c - 'a';
            countMap[charIndex]++;
            if (countMap[charIndex] > maxCount) {
                maxCount = countMap[charIndex];
                maxCountChar = c;
            }
        }
        if (maxCount > (n + 1) / 2) {
            return "";
        }

        char[] builder = new char[n];
        // 先把数量最多的字母，放在偶数位
        int index = 0;
        for (; maxCount > 0; index += 2, maxCount--) {
            builder[index] = maxCountChar;
        }
        countMap[maxCountChar - 'a'] = 0;

        // 再填充剩余
        for (int i = 0; i < countMap.length; i++) {
            while (countMap[i] > 0) {
                if (index >= n) {
                    index = 1;
                }
                builder[index] = (char) (i + 'a');
                countMap[i]--;
                index += 2;
            }
        }
        return new String(builder);
    }
}
