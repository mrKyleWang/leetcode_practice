package math;

/**
 * 69. x 的平方根
 * @author KyleWang
 * @version 1.0
 * @date 2019/10/31
 */
public class Sqrt {

    /*
    实现 int sqrt(int x) 函数。
    计算并返回 x 的平方根，其中 x 是非负整数。
    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

    示例 1:
        输入: 4
        输出: 2
    示例 2:
        输入: 8
        输出: 2
        说明: 8 的平方根是 2.82842...,
                     由于返回类型是整数，小数部分将被舍去。
    */

    public static void main(String[] args) {
        int[] arr = {2147395599};
        for (int i : arr) {
            System.out.println(i + ":" + mySqrt(i));
        }
    }

    private static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int res = 0;
        int l = 0;
        int r = x;
        while (l <= r) {
            int m = (l + r) / 2;
            if (m == x / m) {
                return m;
            } else if (m > x / m) {
                r = m - 1;
            } else {
                l = m + 1;
                res = m;
            }
        }
        return res;
    }
}
