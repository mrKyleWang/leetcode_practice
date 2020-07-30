package math;

import org.junit.Assert;
import org.junit.Test;

/**
 * 343. 整数拆分
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月30日
 */
public class IntegerBreak {

    /*
        给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
        示例 1:
            输入: 2
            输出: 1
            解释: 2 = 1 + 1, 1 × 1 = 1。
        示例 2:
            输入: 10
            输出: 36
            解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
        说明: 你可以假设 n 不小于 2 且不大于 58。
     */

    @Test
    public void test() {
        Assert.assertEquals(1, integerBreak(2));
    }

    @Test
    public void test2() {
        Assert.assertEquals(36, integerBreak(10));
    }

    @Test
    public void test3() {
        Assert.assertEquals(81, integerBreak(12));
    }

    @Test
    public void test4() {
        Assert.assertEquals(531441, integerBreak(36));
    }

    /**
     * 正整数n拆分成m个数的和，并使乘积最大化，那拆分一定是平均分（近似），需要求出m的值
     * m最少为2，且最大值一定是n/2
     */
    public int integerBreak(int n) {
        int result = n / 2;
        for (int i = 2; i <= (n + 1) / 2; i++) {
            int tempResult = cal(n, i);
            if (tempResult < result) {
                return result;
            }
            result = tempResult;
        }
        return result;
    }

    private int cal(int n, int m) {
        int result = 1;
        int consult = n / m;
        int i = m;
        int j = 0;
        if (n % m != 0) {
            i = m - n % m;
            j = m - i;
        }
        for (; i > 0; i--) {
            result *= consult;
        }
        consult += 1;
        for (; j > 0; j--) {
            result *= consult;
        }
        return result;
    }

}
