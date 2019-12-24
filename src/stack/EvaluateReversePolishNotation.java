package stack;

import org.junit.Test;

import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * @author KyleWang
 * @version 1.0
 * @date 2019/12/24
 */
public class EvaluateReversePolishNotation {

    /*
        根据逆波兰表示法，求表达式的值。
        有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

        说明：
            整数除法只保留整数部分。
            给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
        示例 1：
            输入: ["2", "1", "+", "3", "*"]
            输出: 9
            解释: ((2 + 1) * 3) = 9
        示例 2：
            输入: ["4", "13", "5", "/", "+"]
            输出: 6
            解释: (4 + (13 / 5)) = 6
        示例 3：
            输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
            输出: 22
            解释:
              ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
            = ((10 * (6 / (12 * -11))) + 17) + 5
            = ((10 * (6 / -132)) + 17) + 5
            = ((10 * 0) + 17) + 5
            = (0 + 17) + 5
            = 17 + 5
            = 22
     */


    @Test
    public void test() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens));
    }

    /**
     * 使用栈保存数值，每次遇到操作符就从栈中弹出两个数进行计算再放入
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> numStack = new Stack<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    numStack.push(numStack.pop() + numStack.pop());
                    break;
                case "-":
                    numStack.push(-(numStack.pop() - numStack.pop()));
                    break;
                case "*":
                    numStack.push(numStack.pop() * numStack.pop());
                    break;
                case "/":
                    Integer num1 = numStack.pop();
                    Integer num2 = numStack.pop();
                    numStack.push(num2 / num1);
                    break;
                default:
                    numStack.push(Integer.parseInt(token));
                    break;
            }
        }
        return numStack.pop();
    }
}
