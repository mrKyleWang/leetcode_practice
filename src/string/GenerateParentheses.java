package string;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/28
 */
public class GenerateParentheses {

    /*
        给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
        例如，给出 n = 3，生成结果为：

                [
                "((()))",
                "(()())",
                "(())()",
                "()(())",
                "()()()"
                ]
    */

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }

    public static List<String> generateParenthesis(int n) {
        // 2n个位置，左右括号组合
        List<String> result = new ArrayList<>();
        gen(n, 0, 0, "", result);
        return result;
    }

    private static void gen(int n, int left, int right, String temp, List<String> result) {
        if (left == n && right == n) {
            result.add(temp);
        }
        if (left < n) {
            gen(n, left + 1, right, temp + "(", result);
        }
        if (left > right && right < n) {
            gen(n, left, right + 1, temp + ")", result);
        }
    }
}
