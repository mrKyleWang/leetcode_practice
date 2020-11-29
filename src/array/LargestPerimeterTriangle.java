package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 976. 三角形的最大周长
 * @author KyleWang
 * @version 1.0
 * @date 2020年11月29日
 */
public class LargestPerimeterTriangle {

    /*
        给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
        如果不能形成任何面积不为零的三角形，返回 0。

        示例 1：
            输入：[2,1,2]
            输出：5
        示例 2：
            输入：[1,2,1]
            输出：0
        示例 3：
            输入：[3,2,3,4]
            输出：10
        示例 4：
            输入：[3,6,2,3]
            输出：8
         
        提示：
            3 <= A.length <= 10000
            1 <= A[i] <= 10^6
     */

    @Test
    public void test() {
        int[] A = {2, 1, 2};
        Assert.assertEquals(5, largestPerimeter(A));
    }

    @Test
    public void test2() {
        int[] A = {1, 2, 1};
        Assert.assertEquals(0, largestPerimeter(A));
    }

    @Test
    public void test3() {
        int[] A = {3, 2, 3, 4};
        Assert.assertEquals(10, largestPerimeter(A));
    }

    @Test
    public void test4() {
        int[] A = {3, 6, 2, 3};
        Assert.assertEquals(8, largestPerimeter(A));
    }

    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; i--) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }
}
