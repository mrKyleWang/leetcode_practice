package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 415. 字符串相加
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月03日
 */
public class AddStrings {

    /*
        给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
        注意：
            num1 和num2 的长度都小于 5100.
            num1 和num2 都只包含数字 0-9.
            num1 和num2 都不包含任何前导零。
            你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     */

    @Test
    public void test() {
        Assert.assertEquals("10000", addStrings("1234", "8766"));
        Assert.assertEquals("58023", addStrings("1234", "56789"));
        Assert.assertEquals("6984362587", addStrings("6913259244", "71103343"));

    }

    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = 0; i < Math.max(num1.length(), num2.length()); i++) {
            int tempSum = 0;
            if (i < num1.length()) {
                tempSum += num1.charAt(num1.length() - i - 1) - 48;
            }

            if (i < num2.length()) {
                tempSum += num2.charAt(num2.length() - i - 1) - 48;
            }
            tempSum += carry;
            if (tempSum >= 10) {
                tempSum -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            builder.append(tempSum);
        }
        if (carry > 0) {
            builder.append("1");
        }
        return builder.reverse().toString();
    }
}
