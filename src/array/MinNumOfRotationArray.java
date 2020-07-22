package array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月22日
 */
public class MinNumOfRotationArray {

    /*
        把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
        例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

        示例 1：
            输入：[3,4,5,1,2]
            输出：1
        示例 2：
            输入：[2,2,2,0,1]
            输出：0
     */

    @Test
    public void test() {
        int[] arr = {3, 4, 5, 1, 2};
        Assert.assertEquals(1, minArray(arr));
    }

    @Test
    public void test2() {
        int[] arr = {2, 2, 2, 0, 1};
        Assert.assertEquals(0, minArray(arr));
    }

    @Test
    public void test3() {
        int[] arr = {3, 1, 3};
        Assert.assertEquals(1, minArray(arr));
    }

    public int minArray(int[] numbers) {
        int n = numbers.length;
        if (numbers[n - 1] > numbers[0]) {
            return numbers[0];
        }
        for (int i = 1; i < n; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }
}
