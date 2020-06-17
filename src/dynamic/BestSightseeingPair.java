package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1014. 最佳观光组合
 * @author KyleWang
 * @version 1.0
 * @date 2020年06月17日
 */
public class BestSightseeingPair {

    /*
        给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
        一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
        返回一对观光景点能取得的最高分。

        示例：
            输入：[8,1,5,2,6]
            输出：11
            解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11

        提示：
            2 <= A.length <= 50000
            1 <= A[i] <= 1000
     */

    @Test
    public void test() {
        int[] arr = {3, 7, 2, 3};
        Assert.assertEquals(9, maxScoreSightseeingPair(arr));


    }

    @Test
    public void test2() {
        int[] arr = {8, 1, 5, 2, 6};
        Assert.assertEquals(11, maxScoreSightseeingPair(arr));
    }

    @Test
    public void test3() {
        int[] arr = {6, 1, 3, 9, 5, 8, 6};
        Assert.assertEquals(15, maxScoreSightseeingPair(arr));
    }

    @Test
    public void test4() {
        int[] arr = {7, 8, 8, 10};
        Assert.assertEquals(17, maxScoreSightseeingPair(arr));

    }

    /**
     * 思路：先以0为 i（起点）,从i+1向后遍历j，当 A[j] - A[i] >= i - j时，说明 j 后面的数到j的评分 都比到i 大，因此直接将j作为新的起点
     */
    public int maxScoreSightseeingPair(int[] A) {
        int max = 0;
        int i = 0;
        for (int j = 1; j < A.length; j++) {
            max = Math.max(max, A[i] + A[j] + i - j);
            if (A[j] - A[i] >= i - j) {
                i = j;
            }
        }
        return max;
    }
}
