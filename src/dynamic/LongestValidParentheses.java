package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 32. 最长有效括号
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月04日
 */
public class LongestValidParentheses {

    /*
    给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
    示例 1:

    输入: "(()"
    输出: 2
    解释: 最长有效括号子串为 "()"
    示例 2:

    输入: ")()())"
    输出: 4
    解释: 最长有效括号子串为 "()()"

     */

    @Test
    public void test() {
        String s = "(()()";
        Assert.assertEquals(4, longestValidParentheses(s));
    }

    @Test
    public void test2() {
        String s = ")()())";
        Assert.assertEquals(4, longestValidParentheses(s));
    }

    @Test
    public void test3() {
        String s = "())(()())";
        Assert.assertEquals(6, longestValidParentheses(s));
    }

    @Test
    public void test4() {
        String s = "(((((((";
        Assert.assertEquals(0, longestValidParentheses(s));
    }

    @Test
    public void test5() {
        String s = "()(()";
        Assert.assertEquals(2, longestValidParentheses(s));
    }

    @Test
    public void test6() {
        String s = "()(())";
        Assert.assertEquals(6, longestValidParentheses(s));
    }

    /**
     * 遍历chars，到i位置时，以此为子串终点的最大长度，就是将i-1位置l-r数量=1的最大长度
     * 使用数组保存 当前位置 左括号比右括号大n的最大长度
     */
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] chars = s.toCharArray();
        if (chars.length < 2) {
            return max;
        }
        int[] temp = new int[chars.length];
        int preMax = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                for (int j = preMax; j > 0 && j < chars.length - 1; j--) {
                    temp[j + 1] = temp[j] + 1;
                }
                temp[1] = temp[0] + 1;
                temp[0] = 0;
                preMax++;
            } else if (preMax > 0) {
                for (int j = 1; j <= preMax; j++) {
                    temp[j - 1] = temp[j] + 1;
                }
                max = Math.max(max, temp[0]);
                preMax--;
            } else {
                temp[0] = 0;
            }
        }
        return max;
    }
}
