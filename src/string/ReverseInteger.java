package string;

import org.junit.Assert;
import org.junit.Test;

/**
 * 7. 整数反转
 * @author KyleWang
 * @version 1.0
 * @date 2019年04月25日
 */
public class ReverseInteger {

	/*
	   给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
		如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
		假设环境不允许存储 64 位整数（有符号或无符号）。
	 */

    @Test
    public void test() {
        Assert.assertEquals(321, reverse(123));
        Assert.assertEquals(-321, reverse(-123));
        Assert.assertEquals(21, reverse(120));
        Assert.assertEquals(0, reverse(0));
        Assert.assertEquals(0, reverse(1534236469));
    }

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int newRes = res * 10 + (x % 10);
            if ((newRes - (x % 10)) / 10 != res) {
                return 0;
            }
            res = newRes;
            x /= 10;
        }
        return res;
    }
}
