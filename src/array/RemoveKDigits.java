package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/**
 * 402. 移掉K位数字
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月15日
 */
public class RemoveKDigits {

    /*
        给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
        注意:
            num 的长度小于 10002 且 ≥ k。
            num 不会包含任何前导零。
        示例 1 :
            输入: num = "1432219", k = 3
            输出: "1219"
            解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
        示例 2 :
            输入: num = "10200", k = 1
            输出: "200"
            解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
        示例 3 :
            输入: num = "10", k = 2
            输出: "0"
            解释: 从原数字移除所有的数字，剩余为空就是0。
     */

    @Test
    public void test() {
        String num = "1432219";
        Assert.assertEquals("1219", removeKdigits(num, 3));
    }

    @Test
    public void test2() {
        String num = "10200";
        Assert.assertEquals("200", removeKdigits(num, 1));
    }

    @Test
    public void test3() {
        String num = "9";
        Assert.assertEquals("0", removeKdigits(num, 1));
    }

    @Test
    public void test4() {
        String num = "1234567890";
        Assert.assertEquals("0", removeKdigits(num, 9));
    }


    public String removeKdigits2(String num, int k) {
        StringBuilder builder = new StringBuilder(num);
        while (k > 0) {
            for (int i = 0; i < builder.length(); i++) {
                if (i == builder.length() - 1 || builder.charAt(i) > builder.charAt(i + 1)) {
                    builder.deleteCharAt(i);
                    break;
                }
            }
            k--;
        }
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '0') {
                builder.deleteCharAt(i);
                i--;
            } else {
                break;
            }
        }
        return builder.length() > 0 ? builder.toString() : "0";
    }

    /**
     * 双端队列构建单调栈
     */
    public String removeKdigits(String num, int k) {
        LinkedList<Character> deque = new LinkedList<>();
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            while (!deque.isEmpty() && k > 0 && chars[i] < deque.peekLast()) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(chars[i]);
        }
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (Character c : deque) {
            if (c != '0' || flag) {
                builder.append(c);
                flag = true;
            }
        }
        return builder.length() > 0 ? builder.toString() : "0";
    }
}
