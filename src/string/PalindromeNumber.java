package string;

import org.junit.Test;

/**
 * 9. 回文数
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月10日
 */
public class PalindromeNumber {

    /*
        判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

        示例 1:
            输入: 121
            输出: true
        示例 2:
            输入: -121
            输出: false
            解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
        示例 3:
            输入: 10
            输出: false
            解释: 从右向左读, 为 01 。因此它不是一个回文数。
        进阶:
            你能不将整数转为字符串来解决这个问题吗？
     */

    @Test
    public void test() {
        System.out.println(isPalindrome(0));
    }

    public boolean isPalindrome(int x) {
        String s = x + "";
        int head = 0;
        int tail = s.length() - 1;
        char[] chars = s.toCharArray();
        while (head < tail) {
            if (chars[head] != chars[tail]) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}
