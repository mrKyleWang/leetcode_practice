package math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 224. 基本计算器
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月10日
 */
public class BasicCalculator {

    @Test
    public void test() {
        Assert.assertEquals(2, calculate("1 + 1"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(23, calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(2, calculate("5-(10-7)"));
    }

    /**
     * 由于只有加减法运算，因此只要关注括号对于符号的影响即可
     * 这里使用stack保存多层括号内，当前括号内计算结果是需要加还是减
     */
    public int calculate(String s) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        stack.push(sign);

        char[] chars = s.toCharArray();
        int i = 0, n = chars.length;
        while (i < n) {
            if (chars[i] == ' ') {
                i++;
            } else if (chars[i] == '+') {
                sign = stack.peek();
                i++;
            } else if (chars[i] == '-') {
                sign = -stack.peek();
                i++;
            } else if (chars[i] == '(') {
                stack.push(sign);
                i++;
            } else if (chars[i] == ')') {
                stack.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(chars[i])) {
                    num = num * 10 + chars[i++] - '0';
                }
                res += sign * num;
            }
        }
        return res;
    }

}
