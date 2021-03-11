package math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 227. 基本计算器 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月11日
 */
public class BasicCalculatorII {

    /*
        给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
        整数除法仅保留整数部分。

        示例 1：
            输入：s = "3+2*2"
            输出：7
        示例 2：
            输入：s = " 3/2 "
            输出：1
        示例 3：
            输入：s = " 3+5 / 2 "
            输出：5
         
        提示：
            1 <= s.length <= 3 * 105
            s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
            s 表示一个 有效表达式
        表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
        题目数据保证答案是一个 32-bit 整数
     */

    @Test
    public void test() {
        String s = "3 + 2 * 2";
        Assert.assertEquals(7, calculate(s));
    }

    public int calculate(String s) {
        int res = 0;
        Stack<Integer> numStack = new Stack<>();
        char preSign = '+';
        char[] chars = s.toCharArray();
        int i = 0, n = chars.length;
        while (i < n) {
            while (i < n && chars[i] == ' ') {
                i++;
            }
            int num = 0;
            while (i < n && Character.isDigit(chars[i])) {
                num = num * 10 + chars[i++] - '0';
            }
            switch (preSign) {
                case '+':
                    numStack.push(num);
                    break;
                case '-':
                    numStack.push(-num);
                    break;
                case '*':
                    numStack.push(numStack.pop() * num);
                    break;
                case '/':
                    numStack.push(numStack.pop() / num);
                    break;
                default:
                    break;
            }
            if (i < n) {
                preSign = chars[i++];
            }
        }
        while (!numStack.isEmpty()) {
            res += numStack.pop();
        }
        return res;
    }
}
