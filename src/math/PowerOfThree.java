package math;

/**
 * 326. 3的幂
 * @author KyleWang
 * @version 1.0
 * @date 2019年08月20日
 */
public class PowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(45));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        int mod = n % 3;
        if (mod == 0) {
            return isPowerOfThree(n / 3);
        } else {
            return false;
        }
    }
}
