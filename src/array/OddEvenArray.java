package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 奇数、偶数数组重排
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月24日
 */
public class OddEvenArray {

    /*
    给定一个长度位2N的数组，奇数和偶数个数相同，实现一个算法，使得奇数放在奇数位，偶数放在偶数位。
     */

    @Test
    public void test() {
        int[] arr = {1, 3, 12, 5, 18, 2, 9, 4, 8};
        arrange(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void arrange(int[] arr) {
        int n = arr.length;
        int odd = 1, even = 0;

        while (odd < n && even < n) {
            int num = arr[n - 1];
            if (num % 2 != 0) {
                arr[n - 1] = arr[odd];
                arr[odd] = num;
                odd += 2;
            } else {
                arr[n - 1] = arr[even];
                arr[even] = num;
                even += 2;
            }
        }

    }

}
