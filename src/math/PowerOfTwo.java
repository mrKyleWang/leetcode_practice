package math;

/**
 * 231. 2的幂
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/11
 */
public class PowerOfTwo {

    /*
        给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

        示例 1:
        输入: 1
        输出: true

        示例 2:
        输入: 16
        输出: true

        示例 3:
        输入: 218
        输出: false

     */

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(-2147483648));
    }

    /**
     * 位运算：x&(x-1)相当于消除最后一位1，注意处理负数情况
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        int s = n & (n - 1);
        if (n > 0 && s == 0) {
            return true;
        }
        return false;
    }
}
