package array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月16日
 */
public class SquaresOfASortedArray {

    /*
        给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。

        示例 1：
            输入：[-4,-1,0,3,10]
            输出：[0,1,9,16,100]
        示例 2：
            输入：[-7,-3,2,3,11]
            输出：[4,9,9,49,121]
         
        提示：
            1 <= A.length <= 10000
            -10000 <= A[i] <= 10000
            A 已按非递减顺序排序。
     */

    @Test
    public void test() {
        int[] A = {-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(sortedSquares(A)));
    }

    @Test
    public void test2() {
        int[] A = {-7, -3, 2, 3, 11};
        System.out.println(Arrays.toString(sortedSquares(A)));
    }

    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        int index = 0;
        int left = 0;
        int right = A.length;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                left = i - 1;
                right = i;
                break;
            }
        }
        while (left >= 0 && right < A.length) {
            if (-A[left] < A[right]) {
                result[index++] = A[left] * A[left];
                left--;
            } else {
                result[index++] = A[right] * A[right];
                right++;
            }
        }
        while (left >= 0) {
            result[index++] = A[left] * A[left];
            left--;
        }
        while (right < A.length) {
            result[index++] = A[right] * A[right];
            right++;
        }
        return result;
    }
}
