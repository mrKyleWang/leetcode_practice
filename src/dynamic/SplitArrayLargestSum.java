package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 410. 分割数组的最大值
 * @author KyleWang
 * @version 1.0
 * @date 2020年07月25日
 */
public class SplitArrayLargestSum {

    /*
        给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
        注意:
            数组长度 n 满足以下条件:
            1 ≤ n ≤ 1000
            1 ≤ m ≤ min(50, n)
        示例:
            输入:
                nums = [7,2,5,10,8]
                m = 2
            输出:
                18
            解释:
                一共有四种方法将nums分割为2个子数组。
                其中最好的方式是将其分为[7,2,5] 和 [10,8]，
                因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     */

    @Test
    public void test() {
        int[] nums = {7, 2, 5, 10, 8};
        Assert.assertEquals(18, splitArray(nums, 2));
    }

    @Test
    public void test2() {
        int[] nums = {1, 2147483646};
        Assert.assertEquals(2147483647, splitArray(nums, 1));
    }

    /**
     * 二分法
     * 需要找到的最大值splitMax，在[max(nums),sum(nums)]区间内
     * 可以先计算出中间值，假设这个中间值就是最优解，然后遍历nums累加，当sum>mid时，说明需要再分一个子数组，并统计子数组个数n
     * 当n>m时，说明子数组分多了，那么mid肯定比最优解小，则取[mid+1,right]区间，再次计算mid
     * 当n<m时，说明子数组分少了，mid比最优解大，则取[left,mid]区间，再次遍历
     * 直到最终left=right，可以确定唯一值，即为最优解
     */
    public int splitArray(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int num : nums) {
            right += num;
            left = Math.max(left, num);
        }
        int mid = 0;
        while (left < right) {
            mid = (left / 2 + right / 2);
            int n = 1;
            int sum = 0;
            for (int num : nums) {
                if (sum + num > mid) {
                    sum = 0;
                    n++;
                }
                sum += num;
            }
            if (n > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
