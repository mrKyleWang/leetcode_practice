package array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 42. 接雨水
 * @author KyleWang
 * @version 1.0
 * @date 2021年02月26日
 */
public class TrappingRainWater {

    /*
        给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

        示例 1：
            输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
            输出：6
            解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
        示例 2：
            输入：height = [4,2,0,3,2,5]
            输出：9

        提示：
            n == height.length
            0 <= n <= 3 * 104
            0 <= height[i] <= 105
     */
    @Test
    public void test() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.assertEquals(6, trap(height));
        Assert.assertEquals(6, trap2(height));
        Assert.assertEquals(6, trap3(height));
    }

    @Test
    public void test2() {
        int[] height = {4, 2, 0, 3, 2, 5};
        Assert.assertEquals(9, trap(height));
        Assert.assertEquals(9, trap2(height));
        Assert.assertEquals(9, trap3(height));
    }

    /**
     * 单调栈
     * 维护一个单调递减栈，每次遇到比栈顶更高的柱子，就将栈顶弹出，并根据左右范围计算可装雨水
     */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int cur = height[i];
            while (!stack.isEmpty() && cur > height[stack.peek()]) {
                int last = stack.pop();
                if (!stack.isEmpty()) {
                    int dis = i - stack.peek() - 1;
                    res += (Math.min(height[stack.peek()], cur) - height[last]) * dis;
                }
            }
            stack.push(i);
        }
        return res;
    }

    /**
     * 动态规划，通过leftMax和rightMax，保存在每个i上，左边最大的数和右边最大的数，
     * 而此位置能接的雨水 = Math.min(leftMax[i],rightMax[i]) - height[i]
     */
    public int trap2(int[] height) {
        int n = height.length;
        int res = 0;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        for (int i = 1; i < n - 1; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = n - 2; i > 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        for (int i = 1; i < n - 1; i++) {
            int rain = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (rain > 0) {
                res += rain;
            }
        }
        return res;
    }

    /**
     * 基于上面leftMax和rightMax的思路，通过双指针l,r作为当前判断的位置，lMax和rMax保存左右两端的最大值
     */
    public int trap3(int[] height) {
        int n = height.length;
        int res = 0;
        int l = 0, r = n - 1;
        int lMax = 0, rMax = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            if (lMax < rMax) {
                res += lMax - height[l++];
            } else {
                res += rMax - height[r--];
            }
        }
        return res;
    }
}
