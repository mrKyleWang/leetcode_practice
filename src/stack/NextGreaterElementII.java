package stack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * 503. 下一个更大元素 II
 * @author KyleWang
 * @version 1.0
 * @date 2021年03月06日
 */
public class NextGreaterElementII {

    /*
        给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。
        数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
        如果不存在，则输出 -1。

        示例 1:
            输入: [1,2,1]
            输出: [2,-1,2]
            解释: 第一个 1 的下一个更大的数是 2；
            数字 2 找不到下一个更大的数；
            第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     */

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

    /**
     * 单调栈+循环数组
     * 首先由于需要循环搜索，因此将数组遍历两次，从逻辑上首尾相连
     * 维护一个单调递减的栈，每次当nums[i]小于栈顶，表示对于栈顶这个元素来说，nums[i]就是它后面隔得最近且比他大的值
     * 将其弹出，并设置结果
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n * 2 - 1; i++) {
            int num = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < num) {
                res[stack.pop()] = num;
            }
            stack.push(i % n);
        }
        return res;
    }
}
