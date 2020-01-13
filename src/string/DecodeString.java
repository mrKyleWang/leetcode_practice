package string;

import org.junit.Test;

import java.util.Stack;

/**
 * 394. 字符串解码
 * @author KyleWang
 * @version 1.0
 * @date 2020/01/13
 */
public class DecodeString {

    @Test
    public void test() {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
        System.out.println("aaabcbc".equals(decodeString("3[a]2[bc]")));
        System.out.println("accaccacc".equals(decodeString("3[a2[c]]")));
        System.out.println("abcabccdcdcdef".equals(decodeString("2[abc]3[cd]ef")));
    }

    public String decodeString(String s) {
        Stack<Object> stack = new Stack<>();
        String numStr = "";
        for (char c : s.toCharArray()) {
            switch (c) {
                case '[':
                    stack.push(Integer.parseInt(numStr));
                    numStr = "";
                    break;
                case ']':
                    String tmpStr = "";
                    Object o = stack.pop();
                    while (o instanceof String) {
                        tmpStr = o + tmpStr;
                        o = stack.pop();
                    }
                    Integer num = (Integer) o;
                    stack.push(getStr(num, tmpStr));
                    break;
                default:
                    if (c >= '0' && c <= '9') {
                        numStr += c;
                    } else {
                        stack.push(c + "");
                    }
                    break;
            }
        }
        String result = "";
        while (!stack.empty()) {
            result = stack.pop() + result;
        }
        return result;
    }

    private String getStr(int num, String s) {
        String str = "";
        for (int i = 0; i < num; i++) {
            str += s;
        }
        return str;
    }
}
