package greedy;

import org.junit.Assert;
import org.junit.Test;

/**
 * 135. 分发糖果
 * @author KyleWang
 * @version 1.0
 * @date 2020年12月24日
 */
public class Candy {

    /*
        老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
        你需要按照以下要求，帮助老师给这些孩子分发糖果：
        每个孩子至少分配到 1 个糖果。
        相邻的孩子中，评分高的孩子必须获得更多的糖果。
        那么这样下来，老师至少需要准备多少颗糖果呢？

        示例 1:
            输入: [1,0,2]
            输出: 5
            解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
        示例 2:
            输入: [1,2,2]
            输出: 4
            解释: 你可以分别给这三个孩子分发 1、2、1 颗糖果。
                 第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
     */

    /*
        1 0 2 1 2 2 0 3 2


     */

    @Test
    public void test() {
        int[] ratings = {1, 0, 2};
        Assert.assertEquals(5, candy(ratings));
    }

    @Test
    public void test2() {
        int[] ratings = {1, 2, 2};
        Assert.assertEquals(4, candy(ratings));
    }

    @Test
    public void test3() {
        int[] ratings = {1, 3, 2, 2, 1};
        Assert.assertEquals(7, candy(ratings));
    }

    @Test
    public void test4() {
        int[] ratings = {1, 2, 3, 3, 2, 1};
        Assert.assertEquals(12, candy(ratings));
    }

    @Test
    public void test5() {
        int[] ratings = {1, 3, 4, 5, 2};
        Assert.assertEquals(11, candy(ratings));
    }

    @Test
    public void test6() {
        int[] ratings = {1, 2, 4, 4, 4, 3};
        Assert.assertEquals(10, candy(ratings));
    }

    @Test
    public void test7() {
        int[] ratings = {1, 2, 3, 1, 0};
        Assert.assertEquals(9, candy(ratings));
    }

    /**
     * 维持一个递减序列
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (i != n - 1 && ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}
