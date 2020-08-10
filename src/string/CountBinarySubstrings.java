package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 696. 计数二进制子串
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月10日
 */
public class CountBinarySubstrings {

    /*
        给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
        重复出现的子串要计算它们出现的次数。

        示例 1 :
            输入: "00110011"
            输出: 6
            解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。

            请注意，一些重复出现的子串要计算它们出现的次数。
            另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。

        示例 2 :
            输入: "10101"
            输出: 4
            解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。

        注意：
        s.length 在1到50,000之间。
        s 只包含“0”或“1”字符。
     */


    @Test
    public void test() {
        String s = "00110011";
        Assert.assertEquals(6, countBinarySubstrings(s));
    }

    @Test
    public void test2() {
        String s = "";
        Assert.assertEquals(4, countBinarySubstrings(s));
    }

    /**
     * 遍历数组，每次判断当前char是否与前一个相同，如果相同则计数+1
     * 不同的话，判断是否是第一次不同，如果第二次不同，说明此连续子串结束，将之前的当前字符计数置为空后+1
     */
    public int countBinarySubstrings(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        int[] windowCount = new int[2];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i > 0 && c != chars[i - 1] && windowCount[c - '0'] > 0) {
                count += Math.min(windowCount[0], windowCount[1]);
                windowCount[c - '0'] = 1;
            } else {
                windowCount[c - '0']++;
            }
        }
        count += Math.min(windowCount[0], windowCount[1]);
        return count;
    }
}
