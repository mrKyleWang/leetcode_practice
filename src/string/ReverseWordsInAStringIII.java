package string;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 557. 反转字符串中的单词 III
 * @author KyleWang
 * @version 1.0
 * @date 2020年04月25日
 */
public class ReverseWordsInAStringIII {

    /*
        给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

        示例 1:

        输入: "Let's take LeetCode contest"
        输出: "s'teL ekat edoCteeL tsetnoc" 
        注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

     */

    @Test
    public void test() {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }

    public String reverseWords(String s) {
        Queue<String> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                queue.offer(stringBuilder.reverse().toString());
                queue.offer(" ");
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(c);
            }
        }
        queue.offer(stringBuilder.reverse().toString());

        stringBuilder = new StringBuilder();
        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }
}
