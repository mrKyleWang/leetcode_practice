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
        for (int i : arr) {
            System.out.println(i + ":" + mySqrt2(i));
        }
    }

    private static int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        double left = 0;
        double right = x;
        double mid = (left + right) / 2;
        double y = mid * mid;
        while (true) {
            if (x > y) {
                left = mid;
            } else {
                right = mid;
            }
            mid = (left + right) / 2;
            y = mid * mid;
            if (x > y && (x - y) < 0.1) {
                return (int) right;
            }
            if (y > x && (y - x) < 0.1) {
                return (int) mid;
            }
        }
    }

    private static int mySqrt2(int x) {
        if (x < 2) {
            return x;
        }
        long left = 0;
        long right = x;
        long mid = (left + right) / 2;
        while (true) {
            if (x == mid * mid) {
                return (int) mid;
            }
            if (x > mid * mid) {
                if ((mid + 1) * (mid + 1) > x) {
                    return (int) mid;
                }
                left = mid;
            } else {

                right = mid;
            }
            mid = (left + right) / 2;
        }
    }
}
