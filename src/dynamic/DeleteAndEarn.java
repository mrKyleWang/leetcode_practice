package dynamic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 740. 删除并获得点数
 * @author KyleWang
 * @version 1.0
 * @date 2021年05月05日
 */
public class DeleteAndEarn {

    /*
    给你一个整数数组 nums ，你可以对它进行一些操作。
    每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
    开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。

    示例 1：
        输入：nums = [3,4,2]
        输出：6
        解释：
        删除 4 获得 4 个点数，因此 3 也被删除。
        之后，删除 2 获得 2 个点数。总共获得 6 个点数。
    示例 2：
        输入：nums = [2,2,3,3,3,4]
        输出：9
        解释：
        删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
        之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
        总共获得 9 个点数。

    提示：
        1 <= nums.length <= 2 * 104
        1 <= nums[i] <= 104
     */

    @Test
    public void test() {
        int[] nums = {2, 2, 3, 3, 3, 4};
        Assert.assertEquals(6, deleteAndEarn(nums));
    }

    /**
     * 动态规划：将每个num值按频次累加，形成sum[]，然后按"打家劫舍"问题动态规划求解
     */
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] sum = new int[maxVal + 1];
        for (int num : nums) {
            sum[num] += num;
        }

        int prePre = sum[0];
        int pre = Math.max(sum[0], sum[1]);
        for (int i = 2; i < sum.length; i++) {
            int temp = pre;
            pre = Math.max(prePre + sum[i], pre);
            prePre = temp;
        }
        return pre;
    }
}
