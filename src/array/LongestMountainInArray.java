package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 845. 数组中的最长山脉
 * @author KyleWang
 * @version 1.0
 * @date 2020年10月26日
 */
public class LongestMountainInArray {

    /*
        我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
        B.length >= 3
        存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
        （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
        给出一个整数数组 A，返回最长 “山脉” 的长度。
        如果不含有 “山脉” 则返回 0。
         
        示例 1：
            输入：[2,1,4,7,3,2,5]
            输出：5
            解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
        示例 2：
            输入：[2,2,2]
            输出：0
            解释：不含 “山脉”。
         
        提示：
            0 <= A.length <= 10000
            0 <= A[i] <= 10000
     */

    @Test
    public void test() {
        int[] A = {2, 1, 4, 7, 3, 2, 5};
        Assert.assertEquals(5, longestMountain(A));
    }

    @Test
    public void test2() {
        int[] A = {2, 2, 2};
        Assert.assertEquals(0, longestMountain(A));
    }

    /**
     * 用left和right两个数组，表示i位置上，左侧连续最长递增序列和 右侧连续最长递减序列
     */
    public int longestMountain(int[] A) {
        int max = 0;
        int n = A.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = A[i] > A[i - 1] ? left[i - 1] + 1 : 0;
        }
        for (int i = n - 2; i > 0; i--) {
            right[i] = A[i] > A[i + 1] ? right[i + 1] + 1 : 0;
        }

        for (int i = 1; i < n - 1; i++) {
            if (left[i] > 0 && right[i] > 0) {
                max = Math.max(max, left[i] + right[i] + 1);
            }
        }
        return max;
    }
}
