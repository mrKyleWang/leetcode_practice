package array;

import org.junit.Test;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形
 * @author KyleWang
 * @version 1.0
 * @date 2020年05月30日
 */
public class LargestRectangleInHistogram {

    /*
        给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
        求在该柱状图中，能够勾勒出来的矩形的最大面积。
        示例:
            输入: [2,1,5,6,2,3]
            输出: 10
     */

    @Test
    public void test() {
        int[] heights = {};
        System.out.println(largestRectangleArea(heights));
    }

    /**
     * 使用单调栈结构（保存index），栈底到栈顶单调递增
     * 出栈时即计算能容纳当前柱的最大面积
     */
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int max = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                // 如果栈顶有比heights[i]大的，则弹出此index
                // 对于index而言，i就是右边小于它且最近的位置，而弹出后的栈顶则是右左边边小于它且最近的位置
                Integer index = stack.pop();
                int leftLimit = -1;
                if (!stack.isEmpty()) {
                    leftLimit = stack.peek();
                }
                int area = (i - leftLimit - 1) * heights[index];
                max = Math.max(max, area);
            }

            stack.push(i);
        }

        // 处理stack剩余的
        while (!stack.isEmpty()) {
            Integer index = stack.pop();
            int leftLimit = -1;
            if (!stack.isEmpty()) {
                leftLimit = stack.peek();
            }
            int area = (heights.length - leftLimit - 1) * heights[index];
            max = Math.max(max, area);
        }
        return max;
    }
}
