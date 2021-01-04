package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 509. 斐波那契数
 * @author KyleWang
 * @version 1.0
 * @date 2021年01月04日
 */
public class FibonacciNumber {

    /*
        斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
        F(0) = 0，F(1) = 1
        F(n) = F(n - 1) + F(n - 2)，其中 n > 1
        给你 n ，请计算 F(n) 。

        示例 1：
            输入：2
            输出：1
            解释：F(2) = F(1) + F(0) = 1 + 0 = 1
        示例 2：
            输入：3
            输出：2
            解释：F(3) = F(2) + F(1) = 1 + 1 = 2
        示例 3：
            输入：4
            输出：3
            解释：F(4) = F(3) + F(2) = 2 + 1 = 3

        提示：0 <= n <= 30
     */
    @Test
    public void test() {
        Assert.assertEquals(1, fib(2));
    }

    @Test
    public void test2() {
        Assert.assertEquals(2, fib(3));
    }

    @Test
    public void test3() {
        Assert.assertEquals(3, fib(4));
    }

    @Test
    public void test4() {
        Assert.assertEquals(0, fib(1));
    }

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int pre = 0;
        int cur = 1;
        for (int i = 1; i < n; i++) {
            int temp = cur;
            cur += pre;
            pre = temp;
        }
        return cur;
    }
}
