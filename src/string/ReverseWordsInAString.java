package string;

import org.junit.Test;

import java.util.Stack;

/**
 * 151. 翻转字符串里的单词
 * @author KyleWang
 * @version 1.0
 * @date 2020年04月19日
 */
public class ReverseWordsInAString {

    /*
        给定一个字符串，逐个翻转字符串中的每个单词。

        示例 1：
            输入: "the sky is blue"
            输出: "blue is sky the"
        示例 2：
            输入: "  hello world!  "
            输出: "world! hello"
            解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        示例 3：
            输入: "a good   example"
            输出: "example good a"
            解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
        说明：
            无空格字符构成一个单词。
            输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
            如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     */

    @Test
    public void test() {
        System.out.println("\"" + reverseWords("  hello world!  ") + "\"");
        System.out.println("\"" + reverseWords(" a good   example ") + "\"");
        System.out.println("\"" + reverseWords(" ") + "\"");
    }

    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();

        char pre = ' ';
        StringBuilder wordSb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!isSpace(c)) {
                wordSb.append(c);
            } else if (!isSpace(pre) && wordSb.length() > 0) {
                stack.push(wordSb.toString());
                stack.push(" ");
                wordSb = new StringBuilder();
            }
            pre = c;
        }
        if (isSpace(pre) && !stack.isEmpty()) {
            stack.pop();
        } else {
            stack.push(wordSb.toString());
        }
        wordSb = new StringBuilder();

        while (!stack.isEmpty()) {
            wordSb.append(stack.pop());
        }
        return wordSb.toString();
    }

    private boolean isSpace(char c) {
        return c == 32 || c == 160;
    }

}
