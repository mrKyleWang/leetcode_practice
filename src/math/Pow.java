package math;

/**
 * 50. Pow(x, n)
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/13
 */
public class Pow {

    /*
    实现 pow(x, n) ，即计算 x 的 n 次幂函数。

        -100.0 < x < 100.0
        n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
     */

    public static void main(String[] args) {
        System.out.println(myPow(2.0000, -2));
    }

    static double myPow(double x, int n) {
        // 边界条件
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1 / x;
        }
        if (n % 2 == 0) {
            // 偶数
            double subResult = myPow(x, n / 2);
            return subResult * subResult;
        } else {
            // 奇数
            double subResult = myPow(x, (n - 1) / 2);
            return subResult * subResult * x;
        }
    }


}
