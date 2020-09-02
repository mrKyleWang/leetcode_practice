package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * @author KyleWang
 * @version 1.0
 * @date 2020年09月02日
 */
public class IsNumber {

    /*
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
     * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
     */


    @Test
    public void test() {
        Assert.assertEquals(true, isNumber("3.1416"));
    }

    @Test
    public void test2() {
        Assert.assertEquals(true, isNumber("-1E-16"));
    }

    @Test
    public void test3() {
        Assert.assertEquals(true, isNumber("5e2"));
    }

    @Test
    public void test4() {
        Assert.assertEquals(false, isNumber("1a3.14"));
    }

    @Test
    public void test5() {
        Assert.assertEquals(false, isNumber("1.2.3"));
    }

    @Test
    public void test6() {
        Assert.assertEquals(false, isNumber("12e+5.4"));
    }

    @Test
    public void test7() {
        Assert.assertEquals(true, isNumber("3."));
    }

    @Test
    public void test8() {
        Assert.assertEquals(true, isNumber("32.e-80123"));
    }

    @Test
    public void test9() {
        Assert.assertEquals(true, isNumber("46.e3"));
    }


    /**
     * 按最复杂的情况一个数字包含： 符号 数字 小数点 e/E 符号 数字
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean signFlag = false;
        boolean dotFlag = false;
        boolean exponentFlag = false;
        boolean numFlag = false;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '+' || c == '-') {
                if (signFlag || dotFlag || numFlag) {
                    return false;
                }
                signFlag = true;
            } else if (c == '.') {
                if (dotFlag || exponentFlag) {
                    return false;
                }
                dotFlag = true;
            } else if (c == 'e' || c == 'E') {
                if (exponentFlag || !numFlag) {
                    return false;
                }
                dotFlag = false;
                signFlag = false;
                numFlag = false;
                exponentFlag = true;
            } else if (c >= '0' && c <= '9') {
                numFlag = true;
            } else {
                return false;
            }
        }
        return numFlag;
    }
}
