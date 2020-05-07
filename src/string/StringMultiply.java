package string;

import org.junit.Test;

/**
 * 43. 字符串相乘
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月07日
 */
public class StringMultiply {

    /*
        给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

        示例 1:
            输入: num1 = "2", num2 = "3"
            输出: "6"
        示例 2:
            输入: num1 = "123", num2 = "456"
            输出: "56088"
        说明：
            num1 和 num2 的长度小于110。
            num1 和 num2 只包含数字 0-9。
            num1 和 num2 均不以零开头，除非是数字 0 本身。
            不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */

    @Test
    public void test() {
        System.out.println(multiply("999", "999"));


        System.out.println('3' + 0);

        System.out.println((char) (1 + 48));
    }

    /**
     * 按每位相乘，大于10则进位的思路，使用int[]保存每一位的值
     */
    public String multiply(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int[] result = new int[chars1.length + chars2.length];
        for (int i = 0; i < chars1.length; i++) {
            for (int j = 0; j < chars2.length; j++) {
                int index = result.length - ((chars1.length - i) + (chars2.length - j) - 1);
                int temp = (chars1[i] - 48) * (chars2[j] - 48) + result[index];
                if (temp >= 10) {
                    int higherIndex = index - 1;
                    int higherBit = result[higherIndex] + temp / 10;
                    result[higherIndex] = higherBit % 10;
                    while (higherBit >= 10) {
                        result[higherIndex] = higherBit % 10;
                        result[higherIndex - 1] += 1;
                        higherBit = result[higherIndex - 1];
                        higherIndex -= 1;
                    }
                    temp = temp % 10;
                }
                result[index] = temp;
            }
        }

        boolean start = false;
        StringBuilder builder = new StringBuilder();
        for (int i : result) {
            if (i != 0) {
                start = true;
                builder.append(i);
            } else if (start) {
                builder.append(i);
            }
        }
        return start ? builder.toString() : "0";
    }

}
