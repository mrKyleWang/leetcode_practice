package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 738. 单调递增的数字
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月15日
 */
public class MonotoneIncreasingDigits {

    /*
        给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
        （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

        示例 1:
            输入: N = 10
            输出: 9
        示例 2:
            输入: N = 1234
            输出: 1234
        示例 3:
            输入: N = 332
            输出: 299
            说明: N 是在 [0, 10^9] 范围内的一个整数。
     */

    @Test
    public void test() {
        Assert.assertEquals(99, monotoneIncreasingDigits(100));
    }

    @Test
    public void test2() {
        Assert.assertEquals(19, monotoneIncreasingDigits(21));
    }

    @Test
    public void test3() {
        Assert.assertEquals(1234, monotoneIncreasingDigits(1234));
    }

    @Test
    public void test4() {
        Assert.assertEquals(299, monotoneIncreasingDigits(332));
    }

    @Test
    public void test5() {
        Assert.assertEquals(119, monotoneIncreasingDigits(120));
    }

    public int monotoneIncreasingDigits(int N) {
        int bit = 1;
        int pre = Integer.MAX_VALUE;
        while (N / bit > 0) {
            int num = N / bit % 10;
            if (num > pre) {
                N = N - N % bit - 1;
                num--;
            }
            pre = num;
            bit *= 10;
        }
        return N;
    }
}
