package string;

import org.junit.Test;

/**
 * 67. 二进制求和
 * @author KyleWang
 * @version 1.0
 * @date 2020/03/15
 */
public class AddBinary {

    /*
        给定两个二进制字符串，返回他们的和（用二进制表示）。
        输入为非空字符串且只包含数字 1 和 0。

        示例 1:
            输入: a = "11", b = "1"
            输出: "100"
        示例 2:
            输入: a = "1010", b = "1011"
            输出: "10101"
     */

    @Test
    public void test() {
        System.out.println(addBinary("110", "1011"));
    }

    public String addBinary(String a, String b) {
        char[] arrLong;
        char[] arrShort;
        if (a.length() > b.length()) {
            arrLong = a.toCharArray();
            arrShort = b.toCharArray();
        } else {
            arrLong = b.toCharArray();
            arrShort = a.toCharArray();
        }

        char[] resultArr = new char[arrLong.length + 1];
        int add = 0;
        for (int i = 0; i < arrLong.length; i++) {
            int cur = arrLong[arrLong.length - i - 1] - 48;
            if (arrShort.length - i > 0) {
                cur += arrShort[arrShort.length - i - 1] - 48;
            }
            cur += add;
            if (cur >= 2) {
                cur = cur - 2;
                add = 1;
            } else {
                add = 0;
            }
            resultArr[resultArr.length - i - 1] = (char) (cur + 48);
        }
        if (add > 0) {
            resultArr[0] = '1';
            return new String(resultArr);
        } else {
            return new String(resultArr, 1, resultArr.length - 1);
        }
    }


}
