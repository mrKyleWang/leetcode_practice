package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 718. 最长重复子数组
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月01日
 */
public class MaximumLengthOfRepeatedSubarray {

    /*
        给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。

        示例 1:
        输入:
            A: [1,2,3,2,1]
            B: [3,2,1,4,7]
        输出: 3
        解释:
            长度最长的公共子数组是 [3, 2, 1]。
        说明:
            1 <= len(A), len(B) <= 1000
            0 <= A[i], B[i] < 100
     */

    @Test
    public void test() {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};
        Assert.assertEquals(3, findLength(A, B));
    }

    @Test
    public void test2() {
        int[] A = {0, 0, 0, 1};
        int[] B = {1, 0, 0};
        Assert.assertEquals(2, findLength(A, B));
    }

    @Test
    public void test3() {
        int[] A = new int[1000];
        int[] B = new int[1000];
        Assert.assertEquals(1000, findLength(A, B));
    }

    public int findLength(int[] A, int[] B) {
        int max = 0;
        for (int i = 0; i < A.length && i < A.length - max; i++) {
            for (int j = 0; j < B.length && j < B.length - max; j++) {
                int count = 0;
                while (i + count < A.length && j + count < B.length && A[i + count] == B[j + count]) {
                    count++;
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }

    public int findLength2(int[] A, int[] B) {
        int max = 0;
        for (int size = Math.min(A.length, B.length); size > 0 && size > max; size--) {
            for (int i = 0; i <= A.length - size; i++) {
                for (int j = 0; j <= B.length - size; j++) {
                    boolean flag = false;
                    int temp = 0;
                    for (int k = 0; k <= size && size - k > max; k++) {
                        if (A[i + k] == B[j + k]) {
                            temp = flag ? temp + 1 : 1;
                            max = Math.max(max, temp);
                            if (max == size) {
                                return max;
                            }
                            flag = true;
                        } else {
                            flag = false;
                        }
                    }
                }
            }
        }
        return max;
    }
}
