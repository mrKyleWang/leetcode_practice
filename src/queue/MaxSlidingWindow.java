package queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 239. 滑动窗口最大值
 * @author KyleWang
 * @version 1.0
 * @date 2019年09月09日
 */
public class MaxSlidingWindow {

    /*
        给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
        你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
        返回滑动窗口中的最大值。
        示例:
        输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
        输出: [3,3,5,5,6,7]
    */


    public static void main(String[] args) {
        int[] nums = {6, -3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
        System.out.println(Arrays.toString(maxSlidingWindow2(nums, 3)));
    }

    /**
     * 解法1：使用大顶堆存储
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                queue.offer(nums[i]);
            } else {
                if (queue.size() < k) {
                    queue.offer(nums[i]);
                } else {
                    queue.remove(nums[i - k]);
                    queue.offer(nums[i]);
                }
                result[i - k + 1] = queue.peek();
            }
        }
        return result;
    }

    /**
     * 解法2：双端队列
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (deque.size() > 0 && nums[deque.peekLast()] <= num) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= k - 1) {
                while (deque.peek() <= i - k) {
                    deque.poll();
                }
                result[i - k + 1] = nums[deque.peek()];
            }
        }
        return result;
    }

}
