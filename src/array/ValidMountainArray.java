package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 941. 有效的山脉数组
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月03日
 */
public class ValidMountainArray {

    /*
        给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。
        让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：
            A.length >= 3
            在 0 < i < A.length - 1 条件下，存在 i 使得：
            A[0] < A[1] < ... A[i-1] < A[i]
            A[i] > A[i+1] > ... > A[A.length - 1]
        示例 1：
            输入：[2,1]
            输出：false
        示例 2：
            输入：[3,5,5]
            输出：false
        示例 3：
            输入：[0,3,2,1]
            输出：true
        提示：
            0 <= A.length <= 10000
            0 <= A[i] <= 10000 
     */

    @Test
    public void test() {
        int[] A = {2, 1};
        Assert.assertEquals(false, validMountainArray(A));
    }

    @Test
    public void test2() {
        int[] A = {3, 5, 5};
        Assert.assertEquals(false, validMountainArray(A));
    }

    @Test
    public void test3() {
        int[] A = {0, 3, 2, 1};
        Assert.assertEquals(true, validMountainArray(A));
    }


    public boolean validMountainArray(int[] A) {
        boolean up = false;
        boolean down = false;
        for (int i = 1; i < A.length; i++) {
            if (A[i] == A[i - 1]) {
                return false;
            } else if (A[i] > A[i - 1]) {
                if (!up) {
                    up = true;
                }
                if (down) {
                    return false;
                }
            } else {
                if (!down) {
                    down = true;
                }
            }
        }
        return up && down;
    }
}
