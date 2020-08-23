package math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 201. 数字范围按位与
 * @author KyleWang
 * @version 1.0
 * @date 2020年08月23日
 */
public class BitwiseAndOfNumbersRange {

    /*
        按位与：转成二进制后的每一位，进行与操作

        给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
        示例 1: 
            输入: [5,7]
            输出: 4
            解释： 101
                  110
                  111
                  100


        示例 2:
            输入: [0,1]
            输出: 0
     */

    @Test
    public void test() {
        Assert.assertEquals(4, rangeBitwiseAnd(5, 7));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, rangeBitwiseAnd(0, 1));
    }


    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        for (; m != n; ++offset) {
            m >>= 1;
            n >>= 1;
        }
        return n << offset;
    }


}
