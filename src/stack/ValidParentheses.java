package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月08日
 */
public class ValidParentheses {

    /*
        给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。

        示例 1:
        输入: "()"
        输出: true
        示例 2:
        输入: "()[]{}"
        输出: true
        示例 3:
        输入: "(]"
        输出: false
    */

    public static void main(String[] args) {
        System.out.println(isValid("("));
        System.out.println(isValid2("()()"));
    }

    /**
     * 解法1：使用栈来判断
     */
    public static boolean isValid(String s) {
        Map<Character, Character> contraryMap = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (contraryMap.containsKey(c)) {
                stack.push(c);
            } else if (stack.isEmpty() || c != (contraryMap.get(stack.pop()))) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 解法2：成对括号替换
     */
    public static boolean isValid2(String s) {
        int length;
        do {
            length = s.length();
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        } while (length != s.length());
        return s.length() == 0;
    }
}
