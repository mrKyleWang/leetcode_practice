package string;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 1047. 删除字符串中的所有相邻重复项
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月09日
 */
public class RemoveAllAdjacentDuplicatesInString {

    /*
        给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
        在 S 上反复执行重复项删除操作，直到无法继续删除。
        在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。

        示例：
            输入："abbaca"
            输出："ca"
            解释：
                例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。
                之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
         
        提示：
            1 <= S.length <= 20000
            S 仅由小写英文字母组成。
     */

    @Test
    public void test() {
        Assert.assertEquals("ca", removeDuplicates("abbaca"));
    }

    /**
     * 使用一个栈结构，每次判断栈顶与当前字母是否相等，不相等就推进去
     */
    public String removeDuplicates(String S) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!stack.isEmpty() && chars[i] == stack.peekLast()) {
                stack.pollLast();
            } else {
                stack.offer(chars[i]);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (char c : stack) {
            builder.append(c);
        }
        return builder.toString();
    }
}
